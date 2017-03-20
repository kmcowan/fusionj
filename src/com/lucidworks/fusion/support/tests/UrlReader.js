/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var urlReader = function (doc) {
    var BufferedReader = java.io.BufferedReader;
    var InputStreamReader = java.io.InputStreamReader;
    var URL = java.net.URL;
    var String = java.lang.String;
    var X509Certificate = java.security.cert.X509Certificate;
    var HostnameVerifier = javax.net.ssl.HostnameVerifier;
    var HttpsURLConnection = javax.net.ssl.HttpsURLConnection;
    var SSLContext = javax.net.ssl.SSLContext;
    var SSLSession = javax.net.ssl.SSLSession;
    var TrustManager = javax.net.ssl.TrustManager;
    var X509TrustManager = javax.net.ssl.X509TrustManager;

    var e = java.langException;
    var stdout = "";
    var trustAllCerts = Java.type("TrustManager[]");
    var certs = Java.type("X509Certificate[] ");
    

    try {
        
     var x509 =   Java.extend(X509TrustManager, {
                getAcceptedIssuers: function() {
                    return null;
                },
                checkClientTrusted:  function( certs, authType) {
                },
                checkServerTrusted: function(certs, authType) {
                }
            });
         trustAllCerts = new TrustManager[1]; 
         trustAllCerts[0] = x509;
         
        
                // Install the all-trusting trust manager
        var sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        var allHostsValid = new HostnameVerifier {
            verify: function( hostname,  session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        
        var oracle = new URL("http://www.google.com/");
        var isr = new InputStreamReader(oracle.openStream());
        var ins = new BufferedReader();

        var inputLine = "";
        while ((inputLine = ins.readLine()) !== null) {
            logger.info(inputLine);
            stdout += inputLine;
        }
        ins.close();
        doc.addField("body", stdout);
        doc.addField("_raw_content_", stdout);
    } catch (e) {
        logger.error(e);
    }
    return doc;
}
