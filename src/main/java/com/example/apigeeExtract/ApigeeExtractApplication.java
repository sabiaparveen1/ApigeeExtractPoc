package com.example.apigeeExtract;

import com.google.auth.oauth2.GoogleCredentials;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


public class ApigeeExtractApplication {


    public static void main(String[] args) throws IOException {


        // Replace with the actual path to your service account key file
        String serviceAccountKeyPath = "/home/knoldus/Downloads/Sample.json";

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(serviceAccountKeyPath))
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

        //  GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream(serviceAccountKeyPath));// giving bad request 400

        // Refresh access token
        credentials.refreshIfExpired();

        String accessToken = credentials.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + accessToken);


        // Replace with the actual URL of the Apigee API you want to access
        String apiUrl = "https://apigee.googleapis.com/v1/organizations/amw-dna-apigee-preprod/apis/APACTS_MAGIC_Services/revisions/6";

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrl);

        // Set the authorization header with the access token
        httpGet.setHeader("Authorization", "Bearer " + accessToken);

        HttpResponse response = httpClient.execute(httpGet);
        String responseContent = EntityUtils.toString(response.getEntity());

        System.out.println("API Response: " + responseContent);
    }


}





