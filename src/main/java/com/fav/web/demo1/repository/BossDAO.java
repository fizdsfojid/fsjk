package com.fav.web.demo1.repository;

import com.fav.web.demo1.entity.Boss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BossDAO extends JpaRepository<Boss, Long> {
    Optional<Boss> findByFirstName(String firstName);
}
