import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokeAPI {

    private static String line;
    static JsonValue jv;

    public static JsonValue getRequest(int id) {

        try {
            // Create the URL for the HTTP GET request
            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + id);

            // Opens connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode(); //save connection response code to int
            if (responseCode == HttpURLConnection.HTTP_OK) { // if responecode and url connection match
                // reader reads in the respons from input strem
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));//reads in data
                StringBuilder response = new StringBuilder(); //save to stringbuilder

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                //get values out from the url data json
                jv = Json.parse(String.valueOf(response));

            } else { // Handle the error response
                System.out.println("Error response code: " + responseCode);
            }

            // Closing connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jv; //return json
    }
}

