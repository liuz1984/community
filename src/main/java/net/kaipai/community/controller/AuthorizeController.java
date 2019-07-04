package net.kaipai.community.controller;


import net.kaipai.community.dto.AccessTokenDTO;
import net.kaipai.community.dto.GithubUser;

import net.kaipai.community.provider.GithubProvider;
import net.kaipai.community.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class AuthorizeController {
    @Autowired
    private UserService userService;

    @Autowired
    private GithubProvider githubProvider;




    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state")String state,

                           HttpServletResponse response
    ) {

        AccessTokenDTO accessTokenDTO=githubProvider.initAccessTokenDTO(code,state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubuser = githubProvider.getUser(accessToken);
        String token = userService.creatUser(githubuser);

                response.addCookie(new Cookie("token", token));

                return "redirect:/";


        }
    }








