package org.example.restClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.restClient.dto.TokenRequestDto;
import org.example.restClient.dto.TokenResponseDto;
import org.example.restClient.dto.TreningListDto;

import java.io.IOException;

public class UserServiceClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static final String URL = "";

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();

    public String login(String username, String password) throws IOException {

        TokenRequestDto tokenRequestDto = new TokenRequestDto(username, password);

        RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(tokenRequestDto));

        Request request = new Request.Builder()
                .url(URL + "/user/login")
                .post(body)
                .build();

        Call call = client.newCall(request);

        Response response = call.execute();

        if (response.code() == 200) {
            String json = response.body().string();
            TokenResponseDto dto = objectMapper.readValue(json, TokenResponseDto.class);

            return dto.getToken();
        }

        throw new RuntimeException("Invalid username or password");
    }

    public TreningListDto getTreninzi(){
        TreningListDto treningListDto = null;
        return treningListDto;
    }
}
