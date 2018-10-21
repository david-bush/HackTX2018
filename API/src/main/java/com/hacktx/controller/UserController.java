package com.hacktx.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.hacktx.model.Airport;
import com.hacktx.model.AirportSentiment;
import com.hacktx.model.Flight;
import com.hacktx.model.FlightSentiment;
import com.hacktx.model.Sentiment;
import com.hacktx.model.SentimentalDataRequest;
import com.hacktx.model.User;
import com.hacktx.repository.AirportRepository;
import com.hacktx.repository.FlightRepository;
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
	
	@Autowired
	AirportRepository airportRepository;
	
	@Autowired
	FlightRepository flightRepository;
	
	@RequestMapping(path = "/getAllUsers", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userDataRepository.findAllUsers();
	}
	
	@RequestMapping(path = "/getAllAirportCodes", method = RequestMethod.GET)
	public List<String> getAllAirportCodes() {
		return airportRepository.getAllAirportCodes();
	}
	
	@RequestMapping(path = "/getAllAirports", method = RequestMethod.GET)
	public List<Airport> getAllAirports() {
		return airportRepository.getAllAirports();
	}
	
	@RequestMapping(path = "/getAllFlightNumbers", method = RequestMethod.GET)
	public Set<String> getAllFlightNumbers() {
		return flightRepository.getAllFlightNumbers();
	}
	
	@RequestMapping(path = "/getAllFlights", method = RequestMethod.GET)
	public List<Flight> getAllFlights() {
		return flightRepository.getAllFlights();
	}
	
	@RequestMapping(path = "/processAirportConversation", method = RequestMethod.GET)
	public AirportSentiment processAirportConversation(@RequestParam(value = "text", defaultValue = "", required = true) String text, 
			@RequestParam(value = "code", defaultValue = "", required = true) String code) throws Exception {
		Documents documents = new Documents ();
		documents.add("0", "en", text);
		
		JsonObject sent = parse(GetSentiment(documents));
		System.out.println(sent);
		Double score = sent.getAsJsonArray("documents").get(0).getAsJsonObject().get("score").getAsDouble();
		
		JsonObject lang = parse(GetLanguage(documents));
		String lan = lang.getAsJsonArray("documents").get(0).getAsJsonObject().getAsJsonArray("detectedLanguages").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
		
		List<String> keywords = new ArrayList<>();
		JsonObject phrase = parse(GetKeyPhrases(documents));
		JsonArray phrases = phrase.getAsJsonArray("documents").get(0).getAsJsonObject().get("keyPhrases").getAsJsonArray();
		for(JsonElement elem : phrases) {
			keywords.add(elem.getAsString());
		}

		AirportSentiment result = new AirportSentiment(lan, keywords, score, text, code);

		sentimentRepository.addNewSentiment(result);
		return result;

	}
	
	@RequestMapping(path = "/processFlightConversation", method = RequestMethod.GET)
	public FlightSentiment processFlightConversation(@RequestParam(value = "text", defaultValue = "", required = true) String text, 
			@RequestParam(value = "code", defaultValue = "", required = true) String code) throws Exception {
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

		FlightSentiment result = new FlightSentiment(lan, keywords, score, text, code);

		sentimentRepository.addNewSentiment(result);
		return result;

	}
	
	@RequestMapping(path = "/updateSentimentalData", method = RequestMethod.POST)
	public void updateSentimentalData(@RequestBody SentimentalDataRequest request) {
		System.out.println(request);
		
		//return new ResponseEntity<UsersWithChurnScoreAndAttributesResponse>(response, responseHeaders, HttpStatus.OK);

	}

	
	
	@RequestMapping(path = "/generateData", method = RequestMethod.GET)
	public void generateData() throws Exception {
		List<String> airportCodes = airportRepository.getAllAirportCodes();
		List<String> flightNumbers =  new ArrayList<>(flightRepository.getAllFlightNumbers());
		List<String> airportFeedback = new ArrayList<>();
		List<AirportSentiment> airportSentiment = new ArrayList<>();
		List<String> flightFeedback = new ArrayList<>();
		List<FlightSentiment> flightSentiment = new ArrayList<>();
		File airportText = new File("/Users/davidbush/Documents/UT/HackTX2018/API/src/main/resources/AirportLines.txt");
		File flightText = new File("/Users/davidbush/Documents/UT/HackTX2018/API/src/main/resources/AirportLines.txt");
		
		BufferedReader airportBr = new BufferedReader(new FileReader(airportText));
		BufferedReader flightBr = new BufferedReader(new FileReader(flightText));
		
		String line = "";
		while((line = airportBr.readLine()) != null) {
			airportFeedback.add(line);
		}
		
		for(String s: airportFeedback) {
			int airport = (int)(Math.random() * airportCodes.size());
			airportSentiment.add(processAirportConversation(s, airportCodes.get(airport)));
		}
		
		
		line = "";
		while((line = flightBr.readLine()) != null) {
			flightFeedback.add(line);
		}
		
		for(String s: flightFeedback) {
			int flight = (int)(Math.random() * flightNumbers.size());
			flightSentiment.add(processFlightConversation(s, flightNumbers.get(flight)));
		}
		
		System.out.println(airportSentiment);
		
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