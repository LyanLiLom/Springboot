package com.cybersoft.demoapi04.Service;

import com.cybersoft.demoapi04.Service.Imp.LoginServiceImp;
import com.cybersoft.demoapi04.entity.UserEntity;
import com.cybersoft.demoapi04.payload.respond.RoleRespond;
import com.cybersoft.demoapi04.repository.UserRepository;
import com.cybersoft.demoapi04.utils.JWTUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    private Gson gson = new Gson();

    @Override
    public String checkLogin(String username,String password) {
        String token = "";
        UserEntity userEntity = userRepository.findByEmail(username);
        if(passwordEncoder.matches(password,userEntity.getPassword())){

            RoleRespond roleRespond = new RoleRespond();
            roleRespond.setName(userEntity.getRole().getName());

            String roles = gson.toJson(roleRespond);
            token = jwtUtils.createToken(roles);
        }

        return token;
    }
}
