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
import java.time.LocalDate;

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

    public KorisniciListaDto getKorisnici() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/korisnici"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray jsonArray = jsonResponse.getJSONArray("content");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            KorisniciListaDto korisniciListaDto = new KorisniciListaDto();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object1 = jsonArray.getJSONObject(i);
                int id = object1.getInt("id");
                String username = object1.getString("username");
                String ime = object1.getString("ime");
                String prezime = object1.getString("prezime");
                String email = object1.getString("email");
                String datum = object1.getString("datumRodjenja").substring(0,10);

                KorisniciDto korisniciDto = new KorisniciDto();
                korisniciDto.setId(id);
                korisniciDto.setUsername(username);
                korisniciDto.setIme(ime);
                korisniciDto.setPrezime(prezime);
                korisniciDto.setEmail(email);

                request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/api/admin/pristupi/" + id))
                        .header("Content-Type", "application/json")
                        .build();
                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                korisniciDto.setZabranjenPristup(Boolean.parseBoolean(response.body()));

                korisniciDto.setDatumRodjenja(LocalDate.parse(datum));
                korisniciListaDto.getContent().add(korisniciDto);
            }
            return korisniciListaDto;
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void zabraniPistup(KorisniciDto korisniciDto){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/admin/zabrani-pristup/" + korisniciDto.getId()))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                JOptionPane.showMessageDialog(null, "Uspesno ste zabranili pristup korisniku");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void odobriPristup(KorisniciDto korisniciDto){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/admin/odobri-pristup/" + korisniciDto.getId()))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200){
                JOptionPane.showMessageDialog(null, "Uspesno ste odobrili pristup korisniku");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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
