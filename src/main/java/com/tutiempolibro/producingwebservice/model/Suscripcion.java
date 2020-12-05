package com.tutiempolibro.producingwebservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="suscripcion")
@Entity
@Data
public class Suscripcion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idsuscripcion;
	private Integer idcliente;
	private Date fechainicio;
	private String estado;
	private Integer idplan;
	private Date fechafin;

}
