package com.amazon;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.util.IOUtils;
import com.amazonaws.services.rekognition.model.BoundingBox;
import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesRequest;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import java.util.List;



public class TestDetect {

    private String bucket = "puppyplaytimebucket";
    private AmazonRekognition rekognitionClient;

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



    public void comparePictures(String sourceImage, String targetImage, float similarityThreshold) throws Exception {

        Image source = new Image().withS3Object(new S3Object().withName(sourceImage).withBucket(bucket));

        Image target = new Image().withS3Object(new S3Object().withName(targetImage).withBucket(bucket));

        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(similarityThreshold);

        // Call operation
        CompareFacesResult compareFacesResult=rekognitionClient.compareFaces(request);

        // Display results
        List <CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
        for (CompareFacesMatch match: faceDetails){
            ComparedFace face= match.getFace();
            BoundingBox position = face.getBoundingBox();
            System.out.println("Face at " + position.getLeft().toString()
                    + " " + position.getTop()
                    + " matches with " + face.getConfidence().toString()
                    + "% confidence.");

        }
        List<ComparedFace> uncompared = compareFacesResult.getUnmatchedFaces();

        System.out.println("------------------------------------------");
        System.out.println("There were " + uncompared.size()
                + " that did not match");
        System.out.println("Source image rotation: " + compareFacesResult.getSourceImageOrientationCorrection());
        System.out.println("target image rotation: " + compareFacesResult.getTargetImageOrientationCorrection());
    }

    public void displayPictureLabels(String photo, float percent) throws Exception {

        setup();

        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image().withS3Object(new S3Object().withName(photo).withBucket(bucket)))
                .withMaxLabels(10)
                .withMinConfidence(percent);

        try {

            DetectLabelsResult result = rekognitionClient.detectLabels(request);
            List <Label> labels = result.getLabels();
            System.out.println("------------------------------------------");
            System.out.println("Detected labels for " + photo);
            for (Label label: labels) {
                System.out.println(label.getName() + ": " + label.getConfidence().toString());
            }
        } catch(AmazonRekognitionException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {

        TestDetect currentDetect = new TestDetect();

        currentDetect.setup();
        currentDetect.comparePictures("aaron1.jpg","aaron2.jpg",70);
        currentDetect.displayPictureLabels("OdoandBone.jpg",25);

        currentDetect.displayPictureLabels("angrycostumedog.jpg",75);

        currentDetect.displayPictureLabels("3ddog.jpg",50);
        currentDetect.displayPictureLabels("ScoobyDoo.jpg",50);

        }

    }