package org.example.restClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import okhttp3.*;
import org.example.MyApp;
import org.example.restClient.dto.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserServiceClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static final String URL = "";
    private final HttpClient httpClient;

    OkHttpClient client = new OkHttpClient();

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
            JSONArray jsonArray = new JSONArray(response.body());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            TerminTreningaListDto terminTreningaListDto = new TerminTreningaListDto();
            for (int i = 0 ; i < jsonArray.length(); i++){
                JSONObject object1 = jsonArray.getJSONObject(i);
                int id = object1.getInt("id");
                int salaId = object1.getJSONObject("sala").getInt("id");
                String salaIme = object1.getJSONObject("sala").getString("ime");
                int tipTreningaId = object1.getJSONObject("tipTreninga").getInt("id");
                String tipTreningaNaziv = object1.getJSONObject("tipTreninga").getString("naziv");
                String datum = object1.getString("datum").substring(0,10);
                String vremePocetka = object1.getString("vremePocetka");
                int maksimalanBrojUcesnika = object1.getInt("maksimalanBrojUcesnika");

                TerminTreningaDto terminTreningaDto = new TerminTreningaDto();
                terminTreningaDto.setIdSale((long) salaId);
                terminTreningaDto.setId((long) id);
                terminTreningaDto.setIdTreninga((long) tipTreningaId);
                terminTreningaDto.setDatum(dateFormat.parse(datum));
                terminTreningaDto.setVremePocetka(Time.valueOf(vremePocetka));
                terminTreningaDto.setMaksimalanBrojUcesnika(maksimalanBrojUcesnika);
                terminTreningaDto.setNazivTreninga(tipTreningaNaziv);
                terminTreningaDto.setNazivSale(salaIme);

                terminTreningaListDto.getContent().add(terminTreningaDto);
            }
                return terminTreningaListDto;
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public KorisnikKlijentDTO getPodaci(){
        Long id = getKorisnikId();
        KorisnikKlijentDTO korisnikKlijentDTO = new KorisnikKlijentDTO();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/korisnici/getUser/" + id))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                .build();
        HttpResponse<String> response = null;
//{"username":"MarkoMaric","email":"maric@gmail.com","datumRodjenja":"2001-10-25",
// "ime":"Marko","prezime":"Maric","clanskaKarta":"12","zakazaniTreninzi":3}
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject data = new JSONObject(response.body());
            korisnikKlijentDTO.setEmail(data.getString("email"));
            korisnikKlijentDTO.setIme(data.getString("ime"));
            korisnikKlijentDTO.setUsername(data.getString("username"));
            korisnikKlijentDTO.setPrezime(data.getString("prezime"));
            korisnikKlijentDTO.setClanskaKarta(data.getString("clanskaKarta"));
            korisnikKlijentDTO.setZakazaniTreninzi(data.getInt("zakazaniTreninzi"));
            korisnikKlijentDTO.setDatumRodjenja(dateFormat.parse(data.getString("datumRodjenja")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return korisnikKlijentDTO;
    }

    private Long getKorisnikId(){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/korisnici/getUserID"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Long.parseLong(response.body());
    }
}
