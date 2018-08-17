package com.pack.functions;

import java.io.Serializable;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class CommonFunctions implements Serializable{

	private static final long serialVersionUID = 1L;

	public static HttpSolrClient getSolrClient() {

		final String solrUrl = "http://localhost:8983/solr";
		
		HttpSolrClient solrClient = new HttpSolrClient
										.Builder(solrUrl)
										.withConnectionTimeout(10000)
										.withSocketTimeout(60000)
										.build();
 
		return solrClient;

	}

}
