package com.tutiempolibro.producingwebservice.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "planes")
@Entity
@Data
public class Planes implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idplan;

	private String descripcion;

	private String tipo;

	private Double costo;

	private String estado;

	private Integer promoalquiler;

	private Integer promoventa;

	private String deliverygratis;
}
