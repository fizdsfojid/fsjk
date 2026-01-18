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
    private final PasswordEncoder passwordEncoder;
    private final BossDAO bossDAO;

    @Override
    public void run(String... args) throws Exception {
        String workerPass = passwordEncoder.encode("321");
        String bossPass = passwordEncoder.encode("123");

        // 2. Create Workers
        Workers Ivan = new Workers("IvanEmail", "Shemshur", "Ivan", workerPass);
        Workers Dima = new Workers("DimaEmail", "Lahovych", "Dima", workerPass);
        Workers Stas = new Workers("StasEmail", "Petrenko", "Stas", workerPass);

        workerDAO.save(Ivan);
        workerDAO.save(Dima);
        workerDAO.save(Stas);

        // 3. Create Bosses
        Boss Ania = new Boss("Ania", "Bochkai", "AnianEmail", bossPass);
        Boss Kiril = new Boss("Kiril", "ornets", "KirilEmail", bossPass);

        Ania.addWorker(Ivan);
        Kiril.addWorker(Dima);
        Kiril.addWorker(Stas);

        bossDAO.save(Ania);
        bossDAO.save(Kiril);
    }
}
