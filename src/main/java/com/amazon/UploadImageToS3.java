package com.amazon;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.model.CannedAccessControlList;
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
import java.util.Properties;

/**
 * This class uplods an image to Amazon S3 storage.
 *
 * @author mchoinoski
 */
public class UploadImageToS3 {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private AmazonS3 s3Client;

    private Properties properties;

    private String bucket;

    /**
     * Instantiates a new Upload image to s3 storage.
     *
     * @param properties the properties
     */
    public UploadImageToS3(Properties properties) {
        this.properties = properties;
    }

    /**
     * Get the s3 bucket and credentials and create an s3 client.
     */
    private void setup() {

        bucket = properties.getProperty("aws.bucket.name");

        BasicAWSCredentials credentials = new BasicAWSCredentials (properties.getProperty("aws.key"),
                properties.getProperty("aws.secret.key"));

        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    /**
     * Transfer file to s3 storage.
     *
     * @param filename the filename for the image.
     * @param stream   the input stream which contains picture data.
     */
    public void transferFile(String filename, InputStream stream) {

        setup();

        try {

            byte[] byteArray = IOUtils.toByteArray(stream);
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            PutObjectRequest request = new PutObjectRequest( bucket, filename, inputStream, null);
            Long contentLength = Long.valueOf(byteArray.length);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            metadata.setContentType(properties.getProperty("aws.file.type"));
            metadata.addUserMetadata("x-amz-meta-title", "PictureData");

            request.setMetadata(metadata);
            s3Client.putObject(request.withCannedAcl(CannedAccessControlList.PublicRead));
        }
        catch(AmazonServiceException e) {
            logger.error("The call was transmitted successfully, but Amazon S3 couldn't process it: " + e);
        }
        catch(SdkClientException e) {
            logger.error("Amazon S3 couldn't be contacted for a response, or the client couldn't parse the response from Amazon S3: " + e);
        }
        catch (IOException e) {
            logger.error("An IO Exception occurred when opening an input stream: " + e);
        }
    }

}
