package org.example.restClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.MyApp;
import org.example.restClient.dto.TokenRequestDto;
import org.example.restClient.dto.TokenResponseDto;
import org.example.restClient.dto.TerminTreningaListDto;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserServiceClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static final String URL = "";
    private final HttpClient httpClient;

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public UserServiceClient() {
        httpClient = HttpClient.newHttpClient();
    }


    public TerminTreningaListDto getTreninzi(){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/termin-treninga/izlistaj-Termine-Korisnika"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if (response.statusCode() == 200) {
                //System.out.println(response.body());
            }
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
