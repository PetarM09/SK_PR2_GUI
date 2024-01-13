package org.example.restClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.MyApp;
import org.example.restClient.dto.TerminTreningaListDto;

import java.io.IOException;

public class GymServiceClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    public static final String URL = "";
    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public TerminTreningaListDto getTermini() throws IOException{
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Request request = new Request.Builder()
                .url(URL + "/termini")
                .header("Authorization", "Bearer " + MyApp.getInstance().getToken())
                .get()
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();

            return objectMapper.readValue(json, TerminTreningaListDto.class);
        }

        throw new RuntimeException();
    }
}
