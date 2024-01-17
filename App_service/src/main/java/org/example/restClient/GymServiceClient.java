package org.example.restClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.MyApp;
import org.example.restClient.dto.FiskulturnaSalaDTO;
import org.example.restClient.dto.TerminTreningaDto;
import org.example.restClient.dto.TerminTreningaListDto;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GymServiceClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient;

    public GymServiceClient() {
        httpClient = HttpClient.newHttpClient();
    }

    public FiskulturnaSalaDTO getPodaciFiskulturnaSala() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/api/fiskulturne-sale/vrati-salu"))
                .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), FiskulturnaSalaDTO.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void izmeniPodatkeOSali(FiskulturnaSalaDTO fiskulturnaSalaDTO) {
        try {
            String requestBody = objectMapper.writeValueAsString(fiskulturnaSalaDTO);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/api/fiskulturne-sale/sacuvaj-ili-azuriraj"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(fiskulturnaSalaDTO)))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            if(response.statusCode() == 200){
                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili podatke o sali");
            }
        } catch (IOException | InterruptedException e) {
        }
    }

    public void zakaziTrening(TerminTreningaDto terminTreningaDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/api/termin-treninga/zakazi-termin/"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(terminTreningaDto)))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            if(response.statusCode() == 200){
                JOptionPane.showMessageDialog(null, "Uspesno ste zakazali trening");
            }
        } catch (IOException | InterruptedException e) {
        }
    }

    public void otkaziTrening(TerminTreningaDto terminTreningaDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/api/termin-treninga/otkazi-termin/"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(terminTreningaDto)))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
            if(response.statusCode() == 200){
                JOptionPane.showMessageDialog(null, "Uspesno ste otkazali trening");
            }
        } catch (IOException | InterruptedException e) {
        }
    }
}
