/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var base64encode = function (doc) {
      logger.info("****** STARTING BASE 64 DOC CHECK *******");
    if (doc.getId() !== null) {
        logger.info("steve5" + doc.getId());
        
        var String = java.lang.String;
        var myId = new String(doc.getId());
        var binData = Java.type("byte[]");
        var base64 = java.util.Base64;
        var decoder = base64.getDecoder();

        logger.info("steve6" + myId);
        if (myId.contains(".csv#") || myId.endsWith(".csv")) {
            logger.info("steve7 " + doc.getFirstFieldValue("_raw_content_"));
            var rawData = String.valueOf(doc.getFirstFieldValue("_raw_content_")).getBytes();
            binData = rawData;
            var data = "" + new String(decoder.decode(binData));
            doc.addField("csvdata_txt", data);
            logger.info("steve8 " + doc.csvdata);
            doc.addField("_raw_content_", null);
        } else {
             logger.info("NOT LOOKING AT DOC: "+myId);
        }
    } else {
        logger.info("CSV FILE WAS NULL...");
    }

    return(doc);
}