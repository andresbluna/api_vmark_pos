package com.vmark.pos.service;


import com.vmark.pos.dto.LoginResult;
import com.vmark.pos.model.Empleado;
import com.vmark.pos.repository.EmpleadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.vmark.pos.repository.VentaRepository.entityManager;

@Service
public class EmpleadoService {

    @PersistenceContext
    private EntityManager entityManager;

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }


    public LoginResult validarLogin(String nombreEmpleado, String contrasena) {
        try {
            StoredProcedureQuery query = entityManager
                    .createStoredProcedureQuery("sp_validar_login")
                    .registerStoredProcedureParameter("p_nombre_empleado", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("p_empleado_id", Long.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("p_rol_id", Long.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("p_is_valid", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("p_mensaje", String.class, ParameterMode.OUT);

            // Establecer parámetros de entrada
            query.setParameter("p_nombre_empleado", nombreEmpleado);
            query.setParameter("p_contrasena", contrasena);

            // Ejecutar el procedimiento
            query.execute();

            // Obtener resultados
            LoginResult result = new LoginResult();
            result.setEmpleadoId((Long) query.getOutputParameterValue("p_empleado_id"));
            result.setRolId((Long) query.getOutputParameterValue("p_rol_id"));
            result.setIsValid((Integer) query.getOutputParameterValue("p_is_valid"));
            result.setMensaje((String) query.getOutputParameterValue("p_mensaje"));

            return result;
        } catch (Exception e) {
            return new LoginResult(null, null, 0, "Error al validar credenciales: " + e.getMessage());
        }
    }

}
