package com.hacktx.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hacktx.model.Sentiment;
import com.hacktx.model.User;
import com.hacktx.repository.SentimentRepository;
import com.hacktx.repository.UserDataRepository;

@RestController
@RequestMapping("/User")
public class UserController {
	final static String accessKey = "7541be8b1aa04c419fa4b4b0c10ea29f";
    final static String host = "https://westcentralus.api.cognitive.microsoft.com";
    final static String languages = "/text/analytics/v2.0/languages";
    final static String sentiment = "/text/analytics/v2.0/sentiment";
    final static String keyphrases = "/text/analytics/v2.0/keyPhrases";
	
	@Autowired
	UserDataRepository userDataRepository;
	
	@Autowired
	SentimentRepository sentimentRepository;
	
	@RequestMapping(path = "/getAllUsers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userDataRepository.findAllUsers();
	}
	
	@RequestMapping(path = "/processConversations", method = RequestMethod.GET)
	public Sentiment processConversations(@RequestParam(value = "text", defaultValue = "", required = true) String text) throws Exception {
		Documents documents = new Documents ();
		documents.add("0", "en", text);
		
		JsonObject sent = parse(GetSentiment(documents));
		Double score = sent.getAsJsonArray("documents").get(0).getAsJsonObject().get("score").getAsDouble();
		
		JsonObject lang = parse(GetLanguage(documents));
		String lan = lang.getAsJsonArray("documents").get(0).getAsJsonObject().getAsJsonArray("detectedLanguages").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
		
		List<String> keywords = new ArrayList<>();
		JsonObject phrase = parse(GetKeyPhrases(documents));
		JsonArray phrases = phrase.getAsJsonArray("documents").get(0).getAsJsonObject().get("keyPhrases").getAsJsonArray();
		for(JsonElement elem : phrases) {
			keywords.add(elem.getAsString());
		}

		Sentiment result = new Sentiment(lan, keywords, score, text);
		sentimentRepository.addNewSentiment(result);
		return result;

	}

    

    public static String GetSentiment (Documents documents) throws Exception {
        String text = new Gson().toJson(documents);
        byte[] encoded_text = text.getBytes("UTF-8");

        URL url = new URL(host+sentiment);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/json");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", accessKey);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(encoded_text, 0, encoded_text.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }
    
    public static String GetKeyPhrases (Documents documents) throws Exception {
        String text = new Gson().toJson(documents);
        byte[] encoded_text = text.getBytes("UTF-8");

        URL url = new URL(host+keyphrases);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/json");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", accessKey);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(encoded_text, 0, encoded_text.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }
	
	public static String GetLanguage (Documents documents) throws Exception {
        String text = new Gson().toJson(documents);
        byte[] encoded_text = text.getBytes("UTF-8");

        URL url = new URL(host+languages);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/json");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", accessKey);
        connection.setDoOutput(true);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.write(encoded_text, 0, encoded_text.length);
        wr.flush();
        wr.close();

        StringBuilder response = new StringBuilder ();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }

    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }
    
    public static JsonObject parse(String json_text) {
    	JsonParser parser = new JsonParser();
    	return parser.parse(json_text).getAsJsonObject();
    }
	
}

class Document {
    public String id, language, text;

    public Document(String id, String language, String text){
        this.id = id;
        this.language = language;
        this.text = text;
    }
}

class Documents {
    public List<Document> documents;

    public Documents() {
        this.documents = new ArrayList<Document>();
    }
    public void add(String id, String language, String text) {
        this.documents.add (new Document (id, language, text));
    }
}