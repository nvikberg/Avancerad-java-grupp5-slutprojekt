import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class PokeAPI {

   private static String line;



    static JsonValue jv;

   // static String databaseUrl = "https://pokeapi.co/api/v2/berry/1/";



    public static JsonValue getRequest(int id) {
        //String databaseURl = "https://pokeapi.co/api/v2/pokemon/ditto";

        try {
            // Create the URL for the HTTP GET request
            //URL url = new URL(databaseUrl + databasePath);
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + id);

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
                }
                reader.close();

                // Handle the response data
                System.out.println("Response from Firebase Realtime Database:");
                System.out.println(response);


                //here we can get values out from the url data (detta visade Alrik efter du gick)
                jv= Json.parse(String.valueOf(response));
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

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(id);
        return jv;
    }



}

