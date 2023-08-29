package com.example.apigeeExtract;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExtractDetails {
    public static void main(String[] args) {

        String response = null;
        try (FileWriter writer = new FileWriter(System.getProperty("user.home") + "/Downloads/apigeeExtract.csv")) {
            String accessToken = APIServices.GetAccessToken();


            // Write header
            writer.append("ProxyName,Basepaths,TargetEndpoints,Policies,TargetUrl");
            writer.append('\n');

            response = APIServices.CallApigeeAPI(accessToken);
            System.out.println("response====" + response);

            Service services = getServices(response);
            writer.append(services.getName()).append(",");

            List<String> basepaths = services.getBasepaths();
            List<String> targetEndpoints = services.getTargetEndpoints();
            List<String> policies = services.getPolicies();


            writer.append("\"");
            try {
                for (int i = 0; i < basepaths.size(); i++) {

                    writer.append(basepaths.get(i));
                }
                writer.append("\",");
            } catch (NullPointerException e) {
                writer.append("\",");
            }

            writer.append("\"");
            try {
                for (int i = 0; i < targetEndpoints.size(); i++) {

                    writer.append(targetEndpoints.get(i));
                }
                writer.append("\",");
            } catch (NullPointerException e) {
                writer.append("\",");
            }

            // You can continue appending more rows

            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the CSV file.");
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Service getServices(String jsonResponse) {
        ObjectMapper o = new ObjectMapper();
        try {
            Service j = o.readValue(jsonResponse, Service.class);
            return j;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
