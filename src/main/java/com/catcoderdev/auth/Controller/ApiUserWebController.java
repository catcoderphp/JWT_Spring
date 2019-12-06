package com.catcoderdev.auth.Controller;

import com.catcoderdev.auth.Entity.ApiUser;
import com.catcoderdev.auth.Repository.ApiUserRepository;
import com.catcoderdev.auth.Request.ApiUserRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-users")
public class ApiUserWebController {
    private ApiUser apiUser;
    private ApiUserRepository apiUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApiUserWebController(ApiUserRepository apiUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.apiUserRepository = apiUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.apiUser = new ApiUser();
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApiUserRequest apiUserRequest) {
        this.apiUser.setUsername(apiUserRequest.getUsername());
        String passwd = this.bCryptPasswordEncoder.encode(apiUserRequest.getPassword());
        this.apiUser.setPassword(passwd);
        this.apiUserRepository.save(this.apiUser);
    }
}
