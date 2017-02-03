/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucidworks.fusion.support.tests;

import java.io.File;
import java.io.FileInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;

/**
 *
 * @author kevin
 */
public class XlsxParse {
    
    public static void main(String[] args){
        try{
            
            //detecting the file type
      BodyContentHandler handler = new BodyContentHandler(-1);
     
      Metadata metadata = new Metadata();
      FileInputStream inputstream = new FileInputStream(new File("/Users/kevin/Dropbox/PBS_SF424494_1.xlsx"));
      ParseContext pcontext = new ParseContext();
      
      
      //OOXml parser
      OOXMLParser  msofficeparser = new OOXMLParser (); 
      
      msofficeparser.parse(inputstream, handler, metadata,pcontext);
     // System.out.println("Contents of the document:" + handler.toString());
      System.out.println("Metadata of the document:");
      String[] metadataNames = metadata.names();
      
      for(String name : metadataNames) {
         System.out.println(name + ": " + metadata.get(name));
      }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
