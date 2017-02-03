/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var solrStreamClient = function (doc) {
    var HashMap = java.util.HashMap;
    var Map = java.util.Map;
    var Tuple = org.apache.solr.client.solrj.io.Tuple;
    var CloudSolrStream = org.apache.solr.client.solrj.io.stream.CloudSolrStream;

    var e = java.lang.Exception;
    var cstream = org.apache.solr.client.solrj.io.stream.CloudSolrStream;
    var props = java.util.Map;
    var zkHost = "localhost:9983";//args[0];
    var collection = "fbo_test";//args[1];
    var cstream = null;

    var props = new HashMap();
    try {
      props.put("q", "*:*");
      props.put("qt", "/export");
      props.put("sort", "id asc");
      props.put("fl", "id");
      props.put("rows", "20");
      
      cstream = new CloudSolrStream(zkHost, collection, props);
       cstream.open();
        logger.info("children: "+ cstream.children().size());
        while(true) {
          
          var tuple = cstream.read();
          if(tuple.EOF) {
              logger.info("BREAK");
             break;
          }

          var fieldA =  tuple.getString("id");
          var fieldB =  " ";//tuple.getString("body");
          var fieldC = " ";//tuple.getString("resourceName");
          logger.info(fieldA + ", " + fieldB + ", " + fieldC);
        }


    } catch (e) {
       logger.error(e);
    }
    return doc;
}