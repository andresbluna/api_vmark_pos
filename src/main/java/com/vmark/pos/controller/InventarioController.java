package com.vmark.pos.controller;

import com.vmark.pos.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {



    private final InventarioService inventarioService;

    @Autowired
    public InventarioController(InventarioService service, InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

}
