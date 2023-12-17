package com.example.scaha;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AdapterGPT {
    public ArrayList<String[]> parseToStringArray(JsonNode json){
        ArrayList<String[]> arr = new ArrayList<>();
        for (int i = 0; i < json.get("books").size(); i++) {
            String[] book = new String[4];
            if(json.get("books").get(i).get("book").get("book_name") != null)
                book[0] = json.get("books").get(i).get("book").get("book_name").asText();
            if(json.get("books").get(i).get("book").get("book_name") != null)
                book[1] = json.get("books").get(i).get("book").get("book_author").asText();
            if(json.get("books").get(i).get("book").get("book_name") != null)
                book[2] = json.get("books").get(i).get("book").get("date_of_publication").asText();
            if(json.get("books").get(i).get("book").get("book_name") != null)
                book[3] = json.get("books").get(i).get("book").get("number_of_pages").asText();
            arr.add(book);
        }
        return arr;
    }
    public JsonNode chatGPT(String genre) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "";
        String model = "gpt-3.5-turbo";
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" +
                    "I will give you a genre of books, you must return to me the 5 books that are included in this genre. " +
                    "I will also give you instructions on how you should return the response to me. " +
                    "genre: " + genre + ". Your response should be in the JSON format like this: " +
                    "\\\"books\\\":[ {\\\"book\\\":{ \\\"book_name\\\":\\\"(book name)\\\", \\\"book_author\\\":\\\"(book author)\\\", " +
                    "\\\"date_of_publication\\\":\\\"(date of publication)\\\", " + "\\\"number_of_pages\\\":\\\"(number of pages)\\\" }}, " +
                    "{\\\"book\\\":{ \\\"book_name\\\":\\\"(book name)\\\", \\\"book_author\\\":\\\"(book author)\\\", " + "\\\"date_of_publication\\\":\\\"(date of publication)\\\", " +
                    "\\\"number_of_pages\\\":\\\"(number of pages)\\\" }}]\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            ObjectMapper objectMapperr = new ObjectMapper();
            return objectMapperr.readTree(objectMapperr.readTree(String.valueOf(response)).get("choices").get(0).get("message").get("content").asText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
