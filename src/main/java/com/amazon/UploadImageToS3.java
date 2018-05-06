package com.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import javassist.bytecode.ByteArray;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

public class UploadImageToS3 {

    private String bucket = "puppyplaytimebucket";

    private final Logger logger = LogManager.getLogger(this.getClass());
    private AmazonS3 s3Client;

    public void setup() {

        AWSCredentials credentials = null;

        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch(Exception e) {
            logger.error("Error occurred when retrieving AWS credentials:" + e);
        }

        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    public void transferFile(String filename, InputStream stream) throws Exception{

        setup();
        //FileInputStream newStream = (FileInputStream) stream;

        try {

            // Upload a file as a new object with ContentType and title specified.
            //PutObjectRequest request = new PutObjectRequest(bucket, filename, stream, null);
            byte[] byteArray = IOUtils.toByteArray(stream);
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            PutObjectRequest request = new PutObjectRequest( bucket, filename, inputStream, null);
            Long contentLength = Long.valueOf(byteArray.length);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            metadata.setContentType("image/jpg");
            metadata.addUserMetadata("x-amz-meta-title", "someTitle");
            //ContentType: file.mimetype, ACL: 'public-read'
            request.setMetadata(metadata);
            s3Client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
        }
        catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        }
        catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }

}
