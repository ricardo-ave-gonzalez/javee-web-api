package ar.com.eduit.curso.java.web.repositories.rest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    public static String getResponse(String url){
        try{
            HttpClient client=HttpClient.newHttpClient();
            HttpRequest request=HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch(Exception e){
            return "error";
        }
    }
}