package com.fav.web.demo1;

import com.fav.web.demo1.entity.Boss;
import com.fav.web.demo1.entity.Workers;
import com.fav.web.demo1.repository.BossDAO;
import com.fav.web.demo1.repository.WorkerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InputHandler implements CommandLineRunner {

    private final WorkerDAO workerDAO;
    private final BossDAO bossDAO;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Create Encrypted Passwords (MUST BE STRINGS)
        String workerPass = passwordEncoder.encode("321");
        String bossPass = passwordEncoder.encode("123");

        // 2. Pass the String password to the constructor
        Workers ivan = new Workers("IvanEmail", "Shemshur", "Ivan", workerPass);
        Workers dima = new Workers("DimaEmail", "Lahovych", "Dima", workerPass);
        Workers stas = new Workers("StasEmail", "Petrenko", "Stas", workerPass);

        workerDAO.save(ivan);
        workerDAO.save(dima);
        workerDAO.save(stas);

        Boss ania = new Boss("Ania", "Bochkai", "AnianEmail", bossPass);
        bossDAO.save(ania);

        ania.addWorker(ivan);
        bossDAO.save(ania);

        System.out.println("Data Saved Successfully!");
    }
}