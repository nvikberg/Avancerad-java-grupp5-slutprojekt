import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class PokeAPI extends GUI{

   private static String line;

    public static Random getRandom() {
        return random;
    }

    private static Random random = new Random();

   // static String databaseUrl = "https://pokeapi.co/api/v2/berry/1/";


    PokeAPI(){
    }

    public static void getRequest() {
        //String databaseURl = "https://pokeapi.co/api/v2/pokemon/ditto";

        try {
            // Create the URL for the HTTP GET request
            //URL url = new URL(databaseUrl + databasePath);
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/ditto");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET"); //POST , PATCH , DELETE

            // Get the response code t.ex 400, 404, 200 är ok
            int responseCode = connection.getResponseCode();
            //  System.out.println("response code:" +responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // ok är bra
                // Read the response from the InputStream
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                    textArea.append(line);
                }
                reader.close();

                // Handle the response data
                System.out.println("Response from Firebase Realtime Database:");
                System.out.println(response);


                //here we can get values out from the url data (detta visade Alrik efter du gick)
                JsonValue jv= Json.parse(String.valueOf(response));
                JsonObject jo = jv.asObject().get("species").asObject();
                String s = jo.get("name").asString();
                System.out.println(s);

            } else { //404 403 402 etc error koder
                // Handle the error response
                System.out.println("Error response code: " + responseCode);
            }

            GUI.textField.setText(line);
            GUI.textArea.append("hej");

            // Close the connection
            connection.disconnect();
            randomPokemon();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void randomPokemon(){


            int randId = getRandom().nextInt(1292);
            randId++;


    }

    public static void putRequest(String content) {


        try {
            //URL url = new URL(databaseUrl + databasePath);
            URL url = new URL("https://test-9d5b8-default-rtdb.europe-west1.firebasedatabase.app/.json");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("PUT");

            connection.setDoOutput(true);

            // connection.setRequestProperty("Content-Type", "application/json"); //typen
            connection.setRequestProperty("Content-Type", "application/json"); //typen

            HashMap<String, Object> dataMap = new HashMap<>();
            dataMap.put("key", content);


            String jsonInputString = new Gson().toJson(dataMap);
            //String jsonInputString = "{\"name\": \"Alrik\"}";

            // Write the data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
                System.out.println("PUT request successful");
            else
                System.out.println("Error response code: " + responseCode);


            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

