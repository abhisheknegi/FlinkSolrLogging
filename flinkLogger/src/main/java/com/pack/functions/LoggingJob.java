package com.pack.functions;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.pack.models.AvailabilityJsonModel;
import com.pack.models.OfferJsonDataModel;
import com.pack.models.PriceJsonDataModel;
import com.pack.models.RankJsonDataModel;
import com.pack.models.SellerJsonDataModel;

public class LoggingJob {

	public static void method() throws Exception {

		String topic = "wmx-mg-pna-offer-logging";
		String bootstrap = "localhost:9092";
		String groupId = "mx-logger-messages-data";
		//String solrCollection = "mxgm-pna-audit";
		String solrApiUrl = "http://localhost:8983/solr/<collection-name>/update/json/docs";
		
		// Set Kafka parameters
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", bootstrap);
		properties.setProperty("group.id", groupId);

		// Set Streaming environment
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		//	Set offset commit every 10 secs
		env.enableCheckpointing(10000);
		
		//	Set 2 parallel executors
		env.setParallelism(2);
		
		//	Get a Flink Kafka stream
		FlinkKafkaConsumer010<String> consumer = new FlinkKafkaConsumer010<>(topic, new SimpleStringSchema(), properties);
		
		//	Start polling from last committed offset
		consumer.setStartFromLatest();
		consumer.setStartFromEarliest();
		
		//	Map used as Stream sink 
		DataStream<String> stream = env.addSource(consumer);
		stream.map(value -> mapper(value, solrApiUrl));

		//	Trigger stream execution
		env.execute();

	}

	public static String mapper(String value, String solrApiUrl) {
		
		
		Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
		DocumentContext doc = JsonPath.using(conf).parse(value);
		
		String job = doc.read("$.jobname", String.class);
		String status = doc.read("$.status", String.class);
		String message = doc.read("$.message", String.class);
		String json = doc.read("$.json", String.class);
		
		
		System.out.println(job + "," + status + "," + message);
		if("ERROR".equals(status)) {
			System.out.println(json);
		}
		
		String SolrDoc = null;
		
		switch(job) {
			case "OFFER":
						try {
								OfferJsonDataModel msg = OfferJsonDataModel.fromJson(json);
								System.out.println("Offer Log: " + msg.getSkuId() + "," + msg.getOfferId() + "," + msg.getSellerId());
								ObjectToSolrDocMapper ob1 = new ObjectToSolrDocMapper();
								SolrDoc = ob1.getSolrJson(job, status, message, json, msg);
							}catch(Exception e) {
								e.printStackTrace();
							}
						break;
			case "PRICE":
						try {
								PriceJsonDataModel msg = PriceJsonDataModel.fromJson(json);
								System.out.println("Price Log: " + msg.getSkuId() + "," + msg.getOfferId());
								ObjectToSolrDocMapper ob1 = new ObjectToSolrDocMapper();
								SolrDoc = ob1.getSolrJson(job, status, message, json, msg);
							}catch(Exception e) {
								e.printStackTrace();
							}
						break;
			case "RANK":
						try {
								RankJsonDataModel msg = RankJsonDataModel.fromJson(json);
								System.out.println("Rank Log: " + msg.getSkuId() + "," + msg.getOfferId());
								ObjectToSolrDocMapper ob1 = new ObjectToSolrDocMapper();
								SolrDoc = ob1.getSolrJson(job, status, message, json, msg);
							}catch(Exception e) {
								e.printStackTrace();
							}
						break;
			case "AVAILABILITY":
						try {
								AvailabilityJsonModel msg = AvailabilityJsonModel.fromJson(json);
								System.out.println("Avail Log: " + msg.getSkuId() + "," + msg.getOfferId());
								ObjectToSolrDocMapper ob1 = new ObjectToSolrDocMapper();
								SolrDoc = ob1.getSolrJson(job, status, message, json, msg);
							}catch(Exception e) {
								e.printStackTrace();
							}
						break;
			case "SELLER":
						try {
								SellerJsonDataModel msg = new SellerJsonDataModel();
								msg.setSellerId(json, conf);
								System.out.println("Seller Log: " + msg.getSellerId());
								ObjectToSolrDocMapper ob1 = new ObjectToSolrDocMapper();
								SolrDoc = ob1.getSolrJson(job, status, message, json, msg);
							}catch(Exception e) {
								e.printStackTrace();
							}
						break;
			default:
						System.out.println("Unrelated log message...CHECK !!");
						return null;
		}
		
		postSolrMsg(SolrDoc, solrApiUrl);
		
		
		//addDocToSolr(value, solrCollection);
		
		return null;
	}
		
	@SuppressWarnings("unused")
	private static void addDocToSolr(String value, String solrCollection) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		HttpSolrClient solrClient = CommonFunctions.getSolrClient();
		
		Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
		DocumentContext doc = JsonPath.using(conf).parse(value);
		
		String job = doc.read("$.jobname", String.class);
		String status = doc.read("$.status", String.class);
		String message = doc.read("$.message", String.class);
		String json = doc.read("$.json", String.class);
		
		System.out.println("Message in Input: " + message);
		
		SolrInputDocument solrDoc = new SolrInputDocument();
		solrDoc.addField("id", UUID.randomUUID().toString());
		solrDoc.addField("ingestion_name", job);
		solrDoc.addField("ingestion_id", UUID.randomUUID().toString());
		solrDoc.addField("record_id", UUID.randomUUID().toString());
		solrDoc.addField("ingestion_status", status);
		solrDoc.addField("ingestion_status_details", message);
		solrDoc.addField("doc_date", dateFormat.format(new Date()));
		solrDoc.addField("seller_id", "-");
		solrDoc.addField("sku_id", "-");
		solrDoc.addField("offer_id", "-");
		solrDoc.addField("store_number", "-");
		solrDoc.addField("offer_status", "-");
		solrDoc.addField("availability", "-");
		solrDoc.addField("data1", json);
		solrDoc.addField("data2", "-");
		
		System.out.println("Message in solrDoc: " + solrDoc.get("id"));
		
		try {
			long time = System.currentTimeMillis();
			UpdateResponse updateResponse = solrClient.add(solrCollection, solrDoc);
			System.out.println("QTime: " + updateResponse.getQTime() + " , \nStatus: "+ updateResponse.getStatus());
			//solrClient.commit(solrCollection);
			System.out.println("Time: " + (System.currentTimeMillis() - time));
		} catch (Exception e) {
			System.out.println("\nSolr Exception ====> \n");
			e.printStackTrace();
		}
		
	}

	public static void postSolrMsg(String SolrDoc, String solrApiUrl) {	
		
		// Set Http config for Solr insert
		long time = System.currentTimeMillis();
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost postRequest = new HttpPost(solrApiUrl);
		System.out.println("Time1: " + (System.currentTimeMillis() - time));

		try {
			StringEntity input = new StringEntity(SolrDoc);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			long time2 = System.currentTimeMillis();
			HttpResponse response = httpClient.execute(postRequest);
			System.out.println("Time2: " + (System.currentTimeMillis() - time2));
			InputStream in = response.getEntity().getContent();
			System.out.println("Solr Response: " + IOUtils.toString(in, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
