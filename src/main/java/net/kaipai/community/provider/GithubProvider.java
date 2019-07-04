package net.kaipai.community.provider;

import com.alibaba.fastjson.JSON;
import net.kaipai.community.dto.AccessTokenDTO;
import net.kaipai.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;


@Component
public class GithubProvider {

    @Value("${github.Client.id}")
    private String clientID;
    @Value("${github.Client.secret}")
    private String clientSecret;
    @Value("${github.Redirect.uri}")
    private String redirectUrl;
    public AccessTokenDTO initAccessTokenDTO( String code,
                                              String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUrl);
        accessTokenDTO.setState(state);
     return accessTokenDTO;
    }


    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];

            return token;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;

    }

    public GithubUser getUser(String access_token){
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+access_token)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                System.out.println(string);

                GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
                    return  githubUser;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

}
