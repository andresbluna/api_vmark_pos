package com.vmark.pos.repository;

import com.vmark.pos.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.ResultSet;
import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {


}
