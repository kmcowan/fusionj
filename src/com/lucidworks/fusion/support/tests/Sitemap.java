/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucidworks.fusion.support.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author kevin
 */
public class Sitemap {

    public static void main(String[] args) {
        String url = "http://localhost:8983/solr/recollect_test_city_of_sacramento_shard1_replica1/select?q=*%3A*&wt=json";
        StringBuffer buf = new StringBuffer();
        try {
            URL solrSite = new URL(url);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(solrSite.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                buf.append(inputLine);
            }
            in.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(buf.toString());
            JSONObject resp = (JSONObject) jsonObject.get("response");
            JSONArray docs = (JSONArray) resp.get("docs");
            Iterator<JSONObject> iter = docs.iterator();
            JSONObject doc;
            buf = new StringBuffer();
            buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

            buf.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");

            while (iter.hasNext()) {
                doc = iter.next();
                // System.out.println("URL: "+doc.get("id"));
                buf.append("<url><loc>" + doc.get("id") + "</loc></url>");
            }

            buf.append("</urlset>");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(buf.toString());
        }

    }
}
