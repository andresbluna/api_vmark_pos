    package com.vmark.pos.service;

    import com.vmark.pos.model.Empleado;
    import com.vmark.pos.model.Venta;
    import com.vmark.pos.repository.EmpleadoRepository;
    import com.vmark.pos.repository.VentaRepository;
    import jakarta.transaction.Transactional;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;
    import java.time.ZoneId;
    import java.util.*;


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

        public BigDecimal crearVenta(Date fechaVenta, BigDecimal montoTotal, String metodoPago, Long empleadoId) {
            // Validaciones de parámetros
            if (montoTotal == null || metodoPago == null || empleadoId == null) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            if (montoTotal.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El monto total debe ser mayor a 0.");
            }

            // Generar fecha actual
            LocalDateTime fechaActual = LocalDateTime.now();
            fechaVenta = Date.from(fechaActual.atZone(ZoneId.systemDefault()).toInstant());

            // Buscar empleado
            Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);
            if (empleadoOpt.isEmpty()) {
                throw new IllegalArgumentException("El empleado con ID " + empleadoId + " no existe.");
            }

            // Crear y guardar la venta
            Empleado empleado = empleadoOpt.get();
            Venta nuevaVenta = new Venta();
            nuevaVenta.setFechaVenta(fechaVenta);
            nuevaVenta.setMontoTotal(montoTotal);
            nuevaVenta.setMetodoPago(metodoPago);
            nuevaVenta.setEmpleado(empleado);

            // Guardar y retornar
            Venta ventaGuardada = ventaRepository.save(nuevaVenta);
            return ventaGuardada.getMontoTotal();
        }


        public BigDecimal calcularTotalPorRango(Date fechaInicio, Date fechaFin) {
            try {
                if (fechaInicio == null || fechaFin == null) {
                    log.error("Las fechas no pueden ser nulas");
                    return BigDecimal.ZERO;
                }
                return ventaRepository.calcularTotalPorRango(fechaInicio, fechaFin);
            } catch (Exception e) {
                log.error("Error al calcular el total por rango: ", e);
                return BigDecimal.ZERO;
            }
        }

        public BigDecimal calcularPromedioPorRango(Date fechaInicio, Date fechaFin) {
            try {
                if (fechaInicio == null || fechaFin == null) {
                    log.error("Las fechas no pueden ser nulas");
                    return BigDecimal.ZERO;
                }

                return ventaRepository.calcularPromedioPorRango(fechaInicio, fechaFin);
            } catch (Exception e) {
                log.error("Error al calcular el promedio por rango: ", e);
                return BigDecimal.ZERO;
            }
        }

        public ResponseEntity<?> obtenerVentasHoy() {
            try {
                Integer cantidadVentas = ventaRepository.contarVentasHoy();

                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("message", "Consulta exitosa");
                response.put("cantidadVentas", cantidadVentas);

                return ResponseEntity.ok(response);
            } catch (Exception e) {
                log.error("Error al obtener cantidad de ventas del día: " + e.getMessage());

                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Error al obtener cantidad de ventas");
                response.put("error", e.getMessage());

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }


    }
