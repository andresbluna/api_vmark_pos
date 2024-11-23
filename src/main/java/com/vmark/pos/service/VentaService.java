    package com.vmark.pos.service;

    import com.vmark.pos.model.Empleado;
    import com.vmark.pos.model.Venta;
    import com.vmark.pos.repository.EmpleadoRepository;
    import com.vmark.pos.repository.VentaRepository;
    import jakarta.transaction.Transactional;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.math.RoundingMode;
    import java.time.LocalDate;
    import java.time.temporal.TemporalAdjusters;
    import java.util.Calendar;
    import java.util.Date;
    import java.util.List;
    import java.util.Optional;


    @Service
    @Slf4j
    @Transactional
    public class VentaService {

        private final JdbcTemplate jdbcTemplate;
        @Autowired
        private final EmpleadoRepository empleadoRepository;
        @Autowired
        private final VentaRepository ventaRepository;

        public VentaService(JdbcTemplate jdbcTemplate, EmpleadoRepository empleadoRepository, VentaRepository ventaRepository) {
            this.jdbcTemplate = jdbcTemplate;
            this.empleadoRepository = empleadoRepository;
            this.ventaRepository = ventaRepository;
        }


        public Double obtenerPromedioVentaDiaria() {
            String sql = "SELECT promedio_venta_diaria FROM dual";
            return jdbcTemplate.queryForObject(sql, Double.class);
        }

        public BigDecimal crearVenta(Date fechaVenta, BigDecimal montoTotal, String metodoPago, Long empleadoId) {
            // Validar datos de entrada
            if (fechaVenta == null || montoTotal == null || metodoPago == null || empleadoId == null) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }
            if (montoTotal.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El monto total debe ser mayor a 0.");
            }

            // Validar que el empleado existe
            Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);
            if (empleadoOpt.isEmpty()) {
                throw new IllegalArgumentException("El empleado con ID " + empleadoId + " no existe.");
            }

            // Crear la venta
            Empleado empleado = empleadoOpt.get();
            Venta nuevaVenta = new Venta();
            nuevaVenta.setFechaVenta(fechaVenta);
            nuevaVenta.setMontoTotal(montoTotal);
            nuevaVenta.setMetodoPago(metodoPago);
            nuevaVenta.setEmpleado(empleado);

            Venta ventaGuardada = ventaRepository.save(nuevaVenta);
            return ventaGuardada.getMontoTotal();
        }

        public BigDecimal calcularPromedioSemanal() {
            try {
                return ventaRepository.obtenerPromedioSemanal();
            } catch (Exception e) {
                log.error("Error al calcular el promedio semanal: ", e);
                return BigDecimal.ZERO;
            }
        }
    }
