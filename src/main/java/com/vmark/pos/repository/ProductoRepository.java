package com.vmark.pos.repository;

import com.vmark.pos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query(value = "SELECT fn_contar_stock_bajo FROM DUAL", nativeQuery = true)
    Integer contarProductosStockBajo();

    @Procedure(procedureName = "sp_obtener_stock_bajo")
    void obtenerProductosStockBajo(CallableStatement statement) throws SQLException;


}
