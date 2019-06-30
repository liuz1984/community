package net.kaipai.community.provider;

import net.kaipai.community.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        public static final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        String post(String url, String json) throws IOException {
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }
    }
}
