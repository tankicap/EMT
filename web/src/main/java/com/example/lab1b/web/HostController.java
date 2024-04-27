package com.example.lab1b.web;

import com.example.lab1b.model.Host;
import com.example.lab1b.model.Housing;
import com.example.lab1b.service.HostService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/host")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }
    @GetMapping
    public List<Host> findAll(){
        return hostService.listAll();
    }
}
