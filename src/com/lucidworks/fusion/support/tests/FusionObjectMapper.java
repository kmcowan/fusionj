/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucidworks.fusion.support.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
 
 
import org.apache.tika.io.IOUtils;


/**
 *
 * @author kevin
 */
public class FusionObjectMapper {

    public static void main(String[] args) {
        try {
            InputStream in = new FileInputStream(new File("objmap_test.json"));
            if (in != null) {
                String content = IOUtils.toString(in, "UTF-8");
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> mapData = mapper.readValue(content, Map.class);

                if (mapData != null) {
                    System.out.println("Read data OKY");
                    Map result = (Map)mapData.get("result");
                    Iterator iter = result.keySet().iterator();
                    Object obj;
                    String key;
                    while(iter.hasNext()){
                        key = (String)iter.next();
                        obj = result.get(key);
                       
                        if(obj instanceof String){
                             System.out.println("Key: "+key+" object: "+obj.getClass().getSimpleName()+ " value: "+obj);
                        } else if(obj instanceof ArrayList){
                             System.out.println("Key: "+key+" object: "+obj.getClass().getSimpleName()+ " value: "+StringUtils.join((ArrayList)obj, ","));
                            // StringUtils.join((ArrayList)obj, ",");
                        }
                    }
                    
                }
            } else {
                System.out.println("IN was null");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
