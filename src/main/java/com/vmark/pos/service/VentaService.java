    package com.vmark.pos.service;

    import com.vmark.pos.dto.VentaRequestDTO;
    import com.vmark.pos.model.Empleado;
    import com.vmark.pos.model.Venta;
    import com.vmark.pos.repository.EmpleadoRepository;
    import com.vmark.pos.repository.VentaRepository;
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.ParameterMode;
    import jakarta.persistence.PersistenceContext;
    import jakarta.persistence.StoredProcedureQuery;
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
    import java.util.function.Function;
    import java.util.stream.Collectors;

    import static com.vmark.pos.repository.VentaRepository.entityManager;


    @Service
    @Slf4j
    @Transactional
    public class VentaService {

        private final JdbcTemplate jdbcTemplate;
        @Autowired
        private final EmpleadoRepository empleadoRepository;
        @Autowired
        private final VentaRepository ventaRepository;

        @PersistenceContext
        private EntityManager entityManager;

        public VentaService(JdbcTemplate jdbcTemplate, EmpleadoRepository empleadoRepository, VentaRepository ventaRepository) {
            this.jdbcTemplate = jdbcTemplate;
            this.empleadoRepository = empleadoRepository;
            this.ventaRepository = ventaRepository;
        }

        public String crearVenta(VentaRequestDTO request) {
            try {
                // Conversión de listas a cadenas asegurando formato numérico
                String cantidades = request.getCantidades().stream()
                        .map(cantidad -> String.valueOf(cantidad))
                        .collect(Collectors.joining(","));

                String precios = request.getPrecios().stream()
                        .map(precio -> String.format("%.2f", precio))
                        .collect(Collectors.joining(","));

                String productosId = request.getProductosId().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","));

                // Debug para verificar valores
                System.out.println("EmpleadoID: " + request.getEmpleadoId());
                System.out.println("Método Pago: " + request.getMetodoPago());
                System.out.println("Cantidades: " + cantidades);
                System.out.println("Precios: " + precios);
                System.out.println("ProductosID: " + productosId);

                // Llamar al procedimiento almacenado usando EntityManager
                StoredProcedureQuery query = entityManager
                        .createStoredProcedureQuery("SP_CREAR_VENTA")
                        .registerStoredProcedureParameter("p_empleado_id", Long.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("p_metodo_pago", String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("p_cantidades", String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("p_precios", String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("p_productos_id", String.class, ParameterMode.IN)
                        .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT)
                        .setParameter("p_empleado_id", request.getEmpleadoId())
                        .setParameter("p_metodo_pago", request.getMetodoPago())
                        .setParameter("p_cantidades", cantidades)
                        .setParameter("p_precios", precios)
                        .setParameter("p_productos_id", productosId);

                query.execute();
                String resultado = (String) query.getOutputParameterValue("p_resultado");
                return resultado != null ? resultado : "Venta creada exitosamente";

            } catch (Exception e) {
                throw new RuntimeException("Error al crear venta: " + e.getMessage(), e);
            }
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
