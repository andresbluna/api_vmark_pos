package com.vmark.pos.repository;

import com.vmark.pos.dto.LoginResult;
import com.vmark.pos.model.Empleado;
import org.hibernate.jdbc.Expectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    @Procedure(name = "Empleado.validarLogin")
    LoginResult validarLogin(
            @Param("p_nombre_empleado") String nombreEmpleado,
            @Param("p_contrasena") String contrasena
    );
}
