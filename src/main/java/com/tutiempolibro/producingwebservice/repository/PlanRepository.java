package com.tutiempolibro.producingwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutiempolibro.producingwebservice.model.Planes;

public interface PlanRepository extends JpaRepository<Planes, Integer>{

}
