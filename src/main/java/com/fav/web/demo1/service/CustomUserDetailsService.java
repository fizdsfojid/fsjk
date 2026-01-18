package com.fav.web.demo1.service;

import com.fav.web.demo1.entity.Boss;
import com.fav.web.demo1.entity.Workers;
import com.fav.web.demo1.repository.BossDAO;
import com.fav.web.demo1.repository.WorkerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final WorkerDAO workerDAO;
    private final BossDAO bossDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Search in Workers
        Optional<Workers> worker = workerDAO.findByFirstName(username);
        if (worker.isPresent()) {
            return User.builder()
                    .username(worker.get().getFirstName())
                    .password(worker.get().getPassword())
                    .roles("WORKER")
                    .build();
        }

        // 2. Search in Bosses
        Optional<Boss> boss = bossDAO.findByFirstName(username);
        if (boss.isPresent()) {
            return User.builder()
                    .username(boss.get().getFirstName())
                    .password(boss.get().getPassword())
                    .roles("BOSS")
                    .build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}