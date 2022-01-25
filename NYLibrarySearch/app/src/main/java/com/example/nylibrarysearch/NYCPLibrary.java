package com.example.nylibrarysearch;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

public class NYCPLibrary {

    private static final String authenticationToken = "bqmxd55g91ws2iqs";
    private static final String endpoint = "http://api.repo.nypl.org/api/v1/items//search";
    private static final Integer requestLimit = 10000;
    private static Integer numberOfRequests = 0;
    private static String data = "";
    Map mapData = new HashMap<String, String>();


    public Map<String, String> search(String word) throws IOException, ParserConfigurationException, SAXException {
        System.out.println("Called: com.adv.java.networking.apis.NYCPLibrary.query(String, boolean)");

        StringBuilder result = new StringBuilder();

        // Don't make an API call if we've reached the API limit
        if (numberOfRequests < requestLimit) {

            String queryString;


            System.out.println("Only querying for items in the public domain...");
            queryString = endpoint + "?q=" + word + "&publicDomainOnly=true&per_page=200";

            URL url = new URL(queryString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Authorization", "Token token=" + authenticationToken);
            conn.setRequestMethod("GET");
            InputStream stream = conn.getInputStream();

            System.out.print("Getting XML data from the API: ");
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader rd = new BufferedReader(reader);

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line + "\n");
                data = result.toString();
            }
            rd.close();
            System.out.println("done.");
            ArrayList<String> dataList = new ArrayList<>();

            try {
                JSONObject JA = new JSONObject(data);
                JSONObject catsObject = JA.getJSONObject("nyplAPI").getJSONObject("response");
                JSONArray JO = catsObject.getJSONArray("result");

                for (int i = 0; i < JO.length(); i++) {
                    dataList.add("\n" + "Title :" + " " + JO.getJSONObject(i).getString("title") + " " + "\n" + "Type of Resource :" + " " + JO.getJSONObject(i).getString("typeOfResource") + " " + "\n" + "Image Id :" + " " + JO.getJSONObject(i).getString("imageID") + " " + "\n" + "Date Digitized :" + " " + JO.getJSONObject(i).getString("dateDigitized") + "\n" + "__________________________");
                }
                mapData.put("data", dataList.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Keep track of the API calls for the API limit
            numberOfRequests++;
        }
        return mapData;
    }

}


