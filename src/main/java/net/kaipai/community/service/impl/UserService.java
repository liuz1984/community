package net.kaipai.community.service.impl;

import net.kaipai.community.dto.GithubUser;
import net.kaipai.community.mapper.UserMapper;
import net.kaipai.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;


@Service
public class UserService implements net.kaipai.community.service.UserService {
    @Autowired
    private UserMapper userMapper;

    public  String creatUser(GithubUser githubuser) {

        if (githubuser != null) {
            User user = new User();
            String token = (UUID.randomUUID().toString());
            user.setToken(token);
            user.setAccountId(githubuser.getId());
            user.setName(githubuser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            return token;
        } else {
            //登录失败
            return null;
        }
    }
}