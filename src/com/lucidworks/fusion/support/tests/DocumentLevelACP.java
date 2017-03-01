/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucidworks.fusion.support.tests;

import java.util.ArrayList;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

/**
 *
 * @author kevin
 */
public class DocumentLevelACP {
     public static String ZOOKEEPER_URL = new String("localhost:9983");
    public static String DEFAULT_COLLECTION =  new String("fbo_test");
   
    public ArrayList<SolrDocument> run(){
        ArrayList<SolrDocument> list = new SolrDocumentList();
        try{
            SolrClient client = getSolrClient();
       
            SolrQuery query = new SolrQuery();
            query.setQuery("*");
            query.setRows(10);
            
            
            QueryResponse resp = client.query(DEFAULT_COLLECTION, query);
            SolrDocumentList results = resp.getResults();
            SolrDocument doc;
            for (int i = 0; i < results.size(); ++i) {
               System.out.println(results.get(i));
               doc = results.get(i);
               if(hasACP(doc)){
                   list.add(doc);
               }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return list;
    }
    
    private boolean hasACP(SolrDocument doc){
        // YOUR ACP DOCUMENT LEVEL CHECK CODE GOES HERE.
        return true;
    }
    
        private CloudSolrClient getSolrClient(){
        CloudSolrClient cloudServer = null;

        String server = ZOOKEEPER_URL;
        String collection = DEFAULT_COLLECTION;
        try{
             PoolingClientConnectionManager     cm = new PoolingClientConnectionManager();
           HttpClient client = new DefaultHttpClient(cm);
            cloudServer = new CloudSolrClient(server, client);
            cloudServer.setDefaultCollection(collection);
           System.out.println("CLOUD SERVER INIT OK...");
 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return cloudServer;
    }
    
    public static void main(String[] args){
        DocumentLevelACP acp = new DocumentLevelACP();
        acp.run();
    }
}
