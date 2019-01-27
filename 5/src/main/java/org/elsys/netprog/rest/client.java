package org.elsys.netprog.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;


public class client {
	
	public static void main(String [] args) throws ProtocolException, IOException, UnirestException, NoSuchAlgorithmException
    {
				HttpResponse<JsonNode> jsonResponse = Unirest.get("http://localhost:8080/jersey-rest-homework/guess/get").asJson();
				JSONObject json = jsonResponse.getBody().getObject();
				byte[] b = new byte[(Integer) json.get("lenght")];
				String originalHash = (String) json.get("hash");
				String hash2;
				for(;;){
					new Random().nextBytes(b);
			        MessageDigest md = MessageDigest.getInstance("MD5");
			        md.update(b);
			        hash2 = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
			        if(originalHash.equals(hash2)){
			        	HttpResponse<JsonNode> send = Unirest.post("http://localhost:8080/jersey-rest-homework/guess/post/" + hash2)
								.field("byte", b)
								.asJson();
		        		System.out.println("guessed the input:" + b);
		        		break;
			        }
		        }

    }
}