package com.vmark.pos.repository;

import com.vmark.pos.model.DetalleCompraProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraProveedorRepository extends JpaRepository<DetalleCompraProveedor, Long> {
}
