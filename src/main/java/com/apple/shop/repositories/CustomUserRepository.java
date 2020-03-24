package com.apple.shop.repositories;

import com.apple.shop.entity.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser,Long> {


    CustomUser findByEmail(String email);


    boolean existsByEmail(String email);

    CustomUser findByUuid(String uuid);
}
