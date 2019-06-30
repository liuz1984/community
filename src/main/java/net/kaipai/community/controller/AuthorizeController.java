package net.kaipai.community.controller;

import net.kaipai.community.dto.AccessTokenDTO;
import net.kaipai.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name ="state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("5c191d3e1a5a9eb25536");
        accessTokenDTO.setClient_secret("7f1e14dbe95180c0057149a251a4f61e991e5175");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        githubProvider.getAccessToken(accessTokenDTO);
        return "index";

    }
}
