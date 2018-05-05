package com.amazon;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Properties;

/**
 * This class verifies that an image is what it should be. AWS Rekognition is used in order to verify the
 * image.
 *
 * @author mchoinoski
 */

public class ImageVerifier {

    private Properties        properties;
    private String            bucket;
    private AmazonRekognition rekognitionClient;
    private List<Label>       labels;

    public ImageVerifier(Properties properties) {
        this.properties = properties;
    }

    /**
     * This method gets the users credentials from their profile. The default profile is used for AWS. A
     * amazon rekognition client is built with region and credential information.
     */
    public void setup() {

        bucket = properties.getProperty("aws.bucket");

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

    /**
     * This method searches though the list of labels and returns if the label specified is found.
     */
    public boolean searchForLabel(String labelRequired) {
        for (Label label: labels) {
            if (label.getName().equals(labelRequired)) {
                return true;
            }
        }
        return false;
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
