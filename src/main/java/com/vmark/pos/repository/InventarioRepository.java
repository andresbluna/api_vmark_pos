package com.vmark.pos.repository;

import com.vmark.pos.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}