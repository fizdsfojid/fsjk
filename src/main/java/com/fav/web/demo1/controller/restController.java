package com.fav.web.demo1.controller;

import com.fav.web.demo1.entity.Boss;
import com.fav.web.demo1.entity.Workers;
import com.fav.web.demo1.repository.BossDAO;
import com.fav.web.demo1.repository.WorkerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class restController {

    private final BossDAO bossDAO;
    private final WorkerDAO workerDAO;

    @GetMapping("/apiPubic")
    public List<Workers> listWorkers(WorkerDAO workerDAO){
        return workerDAO.findAll();
    }
    @GetMapping("/apiBoss")
    public List<Boss> listBoss(BossDAO bossDAO){
        return bossDAO.findAll();
    }
}
