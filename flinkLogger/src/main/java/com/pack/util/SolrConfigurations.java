package com.pack.util;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrConfigurations {
	
	
	
	
	
	
	
	
	
	
	public static HttpSolrClient getSolrClient() {

		final String solrUrl = "localhost:8983/solr?";
		
		HttpSolrClient solrClient = new HttpSolrClient
										.Builder(solrUrl)
										.withConnectionTimeout(10000)
										.withSocketTimeout(60000)
										.build();
 
		return solrClient;

	}
	
	

}
