package com.fav.web.demo1.repository;

import com.fav.web.demo1.entity.Workers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerDAO extends JpaRepository<Workers, Long> {
}
