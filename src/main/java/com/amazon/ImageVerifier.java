package com.amazon;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Properties        properties;
    private AmazonRekognition rekognitionClient;

    public ImageVerifier(Properties properties) {
        this.properties = properties;
    }

    /**
     * This method gets the users credentials from their profile. The default profile is used for AWS. A
     * amazon rekognition client is built with region and credential information.
     */
    public void setup() {

        AWSCredentials credentials = null;

        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch(Exception e) {
            logger.error("Error occurred when retrieving AWS credentials:" + e);
        }

        rekognitionClient = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

    }

    /**
     * This method retrieves the list of labels for a local file. The image is converted into bytes and
     * then sent to AWS Rekognition to retrieve the labels. A Detect Labels Request is created in order to
     * do this. The max number of labels is specified as well as the confidence percentage needed.
     * @param imageBytes an image that has been converted into bytes
     * @param labelToCheck the label that the should be checked for within the image
     */
    public boolean retrieveLabelsLocal(ByteBuffer imageBytes, String labelToCheck) {

        boolean labelFound = false;
        List<Label> labels = null;

        setup();

        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image()
                        .withBytes(imageBytes))
                .withMaxLabels(Integer.parseInt(properties.getProperty("aws.labels.returned")))
                .withMinConfidence(Float.parseFloat(properties.getProperty("aws.percentage")));

        try {

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            labels = result.getLabels();

            labelFound = searchForLabel(labels, labelToCheck);

        } catch(AmazonRekognitionException e) {
            logger.error("Error occurred when retrieving labels from AWS Rekognition: " + e);
        }

        return labelFound;

    }

    /**
     * This method searches though the list of labels and returns if the label specified is found.
     * @param allLabels the complete list of labels retrieved from AWS Rekognition
     * @param labelRequired the label that should be searched for in the list of labels
     * @return if the label is found or not found
     */
    public boolean searchForLabel(List<Label> allLabels, String labelRequired) {
        for (Label label: allLabels) {
            if (label.getName().equals(labelRequired)) {
                return true;
            }
        }
        return false;
    }

}
