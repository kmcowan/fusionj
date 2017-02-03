/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucidworks.fusion.support.tests;

import java.util.HashMap;
import java.util.Map;
import org.apache.solr.client.solrj.io.Tuple;
import org.apache.solr.client.solrj.io.stream.CloudSolrStream;

  
/**
 *
 * @author kevin
 */
public class SolrjStreamingTest {
    
    public static void main(String[] args){
        /*
        try{
            StreamFactory streamFactory = new StreamFactory().withCollectionZkHost("collection1", zkServer.getZkAddress())
    .withStreamFunction("search", CloudSolrStream.class)
    .withStreamFunction("unique", UniqueStream.class)
    .withStreamFunction("top", RankStream.class)
    .withStreamFunction("group", ReducerStream.class)
    .withStreamFunction("parallel", ParallelStream.class);
 
ParallelStream pstream = (ParallelStream)streamFactory.constructStream("parallel(collection1, group(search(collection1, q=\"*:*\", fl=\"id,a_s,a_i,a_f\", sort=\"a_s asc,a_f asc\", partitionKeys=\"a_s\"), by=\"a_s asc\"), workers=\"2\", zkHost=\""+zkHost+"\", sort=\"a_s asc\")");
        }catch(Exception e){
            e.printStackTrace();
        }
*/
        String zkHost = "localhost:9983";//args[0];
      String collection = "fbo_test";//args[1];
      CloudSolrStream cstream = null;

      Map props = new HashMap();
      try {
      props.put("q", "*:*");
      props.put("qt", "/export");
      props.put("sort", "id asc");
      props.put("fl", "id");
      props.put("rows", "20");
      
      cstream = new CloudSolrStream(zkHost, collection, props);
      
       
        cstream.open();
        System.out.println("children: "+ cstream.children().size());
        while(true) {
          
          Tuple tuple = cstream.read();
          if(tuple.EOF) {
              System.out.println("BREAK");
             break;
          }

          String fieldA =  tuple.getString("id");
          String fieldB =  " ";//tuple.getString("body");
          String fieldC = " ";//tuple.getString("resourceName");
          System.out.println(fieldA + ", " + fieldB + ", " + fieldC);
        }
      }catch(Exception e){
          e.printStackTrace();
      } finally {
          try{
       cstream.close();
          }catch(Exception e){}
      }
      
    }
}
