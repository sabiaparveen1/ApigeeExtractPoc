package com.example.apigeeExtract;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class APIServices {

    public static String CallApigeeAPI(String accessToken) throws IOException {

        URL url = new URL("https://apigee.googleapis.com/v1/organizations/amw-dna-apigee-preprod/apis/GLDV_MAGIC_Services/revisions/31");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);


        InputStream is = connection.getInputStream();


        if (is != null) {
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            return response.toString();
        }

        return null;
    }

    public static String GetAccessToken() throws Exception {

        String serviceAccountKeyPath = "/home/knoldus/Downloads/Sample.json";

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(serviceAccountKeyPath))
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

        //  GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream(serviceAccountKeyPath));// giving bad request 400

        // Refresh access token
        credentials.refreshIfExpired();

        String accessToken = credentials.getAccessToken().getTokenValue();
        System.out.println("Access Token: " + accessToken);
        return accessToken;
    }
}
