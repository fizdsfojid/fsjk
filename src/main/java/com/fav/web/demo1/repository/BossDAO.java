package com.fav.web.demo1.repository;

import com.fav.web.demo1.entity.Boss;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BossDAO extends JpaRepository<Boss, Long> {
}
