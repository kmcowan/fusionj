/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucidworks.fusion.support.tests;

/**
 *
 * @author kevin
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;


public class PdfParser {

   public static void main(final String[] args) throws IOException,TikaException {
      try{
      BodyContentHandler handler = new BodyContentHandler();
      Metadata metadata = new Metadata();
      URL url = new URL("http://www.cityofsacramento.org/-/media/Corporate/Files/HR/Volunteer/Volunteer_Interest_Form.pdf?la=en");
    //  FileInputStream inputstream = new FileInputStream(new File("Example.pdf"));
      ParseContext pcontext = new ParseContext();
      
      //parsing the document using PDF parser
      PDFParser pdfparser = new PDFParser(); 
      pdfparser.parse(url.openConnection().getInputStream(), handler, metadata,pcontext);
      
      //getting the content of the document
      System.out.println("Contents of the PDF :" + handler.toString());
      
      //getting metadata of the document
      System.out.println("Metadata of the PDF:");
      String[] metadataNames = metadata.names();
      
      for(String name : metadataNames) {
         System.out.println(name+ " : " + metadata.get(name));
      }
      
      }catch(Exception e){
          e.printStackTrace();
      }
   }
}
