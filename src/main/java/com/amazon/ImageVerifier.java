package com.amazon;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import javax.servlet.http.Part;
import java.nio.ByteBuffer;
import java.util.List;

public class ImageVerifier {

    private String bucket = "puppyplaytimebucket";
    private AmazonRekognition rekognitionClient;

    List<Label> labels = null;

    public void setup() {

        AWSCredentials credentials;

        try {

            credentials = new ProfileCredentialsProvider().getCredentials();

        } catch(Exception e) {

            throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
                    + "Please make sure that your credentials file is at the correct "
                    + "location (/Users/userid/.aws/credentials), and is in a valid format.", e);

        }

        rekognitionClient = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

    }

    //label.getConfidence()
    public boolean searchForLabel(String labelRequired) {
        for (Label label: labels) {
            if (label.getName().equals(labelRequired)) {
                return true;
            }
        }
        return false;
    }

    public void retrieveLabelsS3(String photo, float percent) throws Exception {

        setup();

        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)))
                .withMaxLabels(20)
                .withMinConfidence(percent);

        try {

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            labels = result.getLabels();

            for (Label label: labels) {
                System.out.println(label.getName() + ": " + label.getConfidence().toString());
            }
        } catch(AmazonRekognitionException e) {
            e.printStackTrace();
        }

    }



    public boolean retrieveLabelsLocal(ByteBuffer imageBytes, float percent, String labelToCheck) {

        boolean labelFound = false;

        setup();

        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image()
                        .withBytes(imageBytes))
                .withMaxLabels(10)
                .withMinConfidence(percent);

        try {

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            labels = result.getLabels();

            labelFound = searchForLabel(labelToCheck);

        } catch(AmazonRekognitionException e) {
            e.printStackTrace();
        }

        return labelFound;

    }


}
