package com.amazonaws.s3883080;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class ProcessImage {

    public static Long getContentLength(String urlStr) {
        Long contentLength = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.62 Safari/537.36");
            httpURLConnection.setRequestMethod("HEAD");
            contentLength = httpURLConnection.getContentLengthLong();

        } catch (Exception e) {

        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return contentLength;
    }

    private static InputStream getImageInputStream(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        return urlConnection.getInputStream();

    }

    public boolean AddImageToBucket(String imageAddress, String itemKey, InputStream inputStream, Long length) throws MalformedURLException {
        boolean imageSuccess = false;
        AmazonCredentials amazonCredentials = new AmazonCredentials();

        Properties properties;
        properties = System.getProperties();
        properties.setProperty("aws.accessKeyId", amazonCredentials.getAccessKey());
        properties.setProperty("aws.secretKey", amazonCredentials.getSecretAccessKey());
        properties.setProperty("aws.sessionToken", amazonCredentials.getSessionToken());
        SystemPropertiesCredentialsProvider systemPropertiesCredentialsProvider = new SystemPropertiesCredentialsProvider();

        AmazonS3 client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(systemPropertiesCredentialsProvider)
                .build();

        String bucketName = "instrument-photos-a3";

//        URL url = new URL(imageAddress);

        try {

//            InputStream inputStream = getImageInputStream(url);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("image/jpeg");
            objectMetadata.setContentLength(length);
//            objectMetadata.setContentLength(getContentLength(imageAddress));
            client.putObject(new PutObjectRequest(bucketName, itemKey,inputStream,objectMetadata));
            inputStream.close();
            imageSuccess = true;
        } catch(AmazonServiceException e){
            e.printStackTrace();
        } catch(SdkClientException | IOException e){
            e.printStackTrace();
        }
        return imageSuccess;
    }


}
