package com.tutiempolibro.producingwebservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutiempolibro.producingwebservice.model.Suscripcion;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Integer> {
	
	public List<Suscripcion> findByIdclienteAndEstado(Integer idcliente, String estado);
	
}
