package com.catcoderdev.auth.Repository;

import com.catcoderdev.auth.Entity.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserRepository extends JpaRepository<ApiUser, Long> {
    ApiUser findByUsername(String username);
}
