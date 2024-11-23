package com.vmark.pos.repository;

import com.vmark.pos.model.Venta;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;


public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Autowired
    EntityManager entityManager = null;

    @Procedure(name = "crear_venta")
    BigDecimal crearVenta(
            @Param("p_fecha_venta") Date fechaVenta,
            @Param("p_monto_total") BigDecimal montoTotal,
            @Param("p_metodo_pago") String metodoPago,
            @Param("p_empleado_id") Long empleadoId
    );

    @Query(value = "SELECT calcular_promedio_por_rango(:fechaInicio, :fechaFin) FROM DUAL", nativeQuery = true)
    BigDecimal calcularPromedioPorRango(
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin
    );

    @Query(value = "SELECT calcular_total_por_rango(:fechaInicio, :fechaFin) FROM DUAL", nativeQuery = true)
    BigDecimal calcularTotalPorRango(
            @Param("fechaInicio") Date fechaInicio,
            @Param("fechaFin") Date fechaFin
    );

    @Query(value = "SELECT COUNT(*) FROM VMARK_VENTAS_2 WHERE TRUNC(FECHA_VENTA) = TRUNC(SYSDATE)", nativeQuery = true)
    Integer contarVentasHoy();




}


