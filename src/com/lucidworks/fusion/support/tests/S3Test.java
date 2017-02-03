/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lucidworks.fusion.support.tests;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

/**
 *
 * @author kevin
 */
public class S3Test {
    
    public static void main(String[] args){
        try{
         /*   BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ46GYE22IBBHTSYQ", "j+dj/hw+DsZOTqNxdlQowGJecShTVL7NniYiio4D");
AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .build();
*/
       //  BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJ46GYE22IBBHTSYQ", "j+dj/hw+DsZOTqNxdlQowGJecShTVL7NniYiio4D");
         // AKIAIENZZR2HM5IUL37A
         //BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAIENZZR2HM5IUL37A", "5Y4h7nJ7npD98hElojdmY2ydHDjgA+khY4j5sJp+");
        //AKIAJCFMB3JNRAUQ4N4A
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJCFMB3JNRAUQ4N4A", "K2FLlrvDoUEbZMXSee3d+53FkvCrli6Tp4iwCJbJ");
        
         AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(awsCreds);
        
         //AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(provider).build();
                 AmazonS3 s3Client = new AmazonS3Client(awsCreds);
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
                s3Client.setRegion(usWest2);

        // System.out.println("Region" + s3Client.getRegionName());
         for (Bucket bucket : s3Client.listBuckets()) {
                System.out.println(" - " + bucket.getName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
