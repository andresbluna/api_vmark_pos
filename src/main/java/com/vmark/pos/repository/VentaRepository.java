package com.vmark.pos.repository;

import com.vmark.pos.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query(value = "SELECT calcular_promedio_semanal() FROM DUAL", nativeQuery = true)
    BigDecimal obtenerPromedioSemanal();

    @Procedure(name = "crear_venta")
    BigDecimal crearVenta(
            @Param("p_fecha_venta") Date fechaVenta,
            @Param("p_monto_total") BigDecimal montoTotal,
            @Param("p_metodo_pago") String metodoPago,
            @Param("p_empleado_id") Long empleadoId
    );

    

}
