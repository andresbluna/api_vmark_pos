package com.vmark.pos.repository;

import com.vmark.pos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT * FROM VMARK_PRODUCTOS WHERE STOCK < 10", nativeQuery = true)
    List<Object[]> findProductosStockBajo();

}
