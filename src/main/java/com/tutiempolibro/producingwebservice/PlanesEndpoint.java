package com.tutiempolibro.producingwebservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tutiempolibro.producingwebservice.model.Planes;
import com.tutiempolibro.producingwebservice.model.Suscripcion;
import com.tutiempolibro.producingwebservice.repository.PlanRepository;
import com.tutiempolibro.producingwebservice.repository.SuscripcionRepository;

import io.spring.guides.gs_producing_web_service.GetPlanesRequest;
import io.spring.guides.gs_producing_web_service.GetPlanesResponse;
import io.spring.guides.gs_producing_web_service.GetUpdatePlanRequest;
import io.spring.guides.gs_producing_web_service.GetUpdatePlanResponse;
import io.spring.guides.gs_producing_web_service.Plan;

@Endpoint
public class PlanesEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private PlanRepository planRepo;
	
	@Autowired
	private SuscripcionRepository suscripcionRepo;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPlanesRequest")
	@ResponsePayload
	public GetPlanesResponse getPlanes(@RequestPayload GetPlanesRequest request) {

		GetPlanesResponse response = new GetPlanesResponse();
		List<Planes> planes = planRepo.findAll();

		planes.stream().filter(p -> p.getEstado().equals(request.getEstado()));

		List<Plan> planesResp = new ArrayList<Plan>();
		for (Planes p : planes) {
			Plan pl = new Plan();
			pl.setIdplan(p.getIdplan());
			pl.setDescripcion(p.getDescripcion());
			pl.setTipo(p.getTipo());
			pl.setCosto(p.getCosto());
			pl.setEstado(p.getEstado());
			pl.setPromoventa(p.getPromoventa());
			pl.setPromoalquiler(p.getPromoalquiler());
			pl.setDeliverygratis(p.getDescripcion());
			planesResp.add(pl);
		}
		response.setPlan(planesResp);
		return response;

	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUpdatePlanRequest")
	@ResponsePayload
	public GetUpdatePlanResponse updatePlanes(@RequestPayload GetUpdatePlanRequest request) {

		GetUpdatePlanResponse response = new GetUpdatePlanResponse();
		
		//Obtienes la suscripcion actual
		
		List<Suscripcion> suscripciones = suscripcionRepo.findByIdclienteAndEstado(request.getIdpersona(),"1"); //1 = Habilitado
		
		if(suscripciones != null && suscripciones.size() > 0) {
			
			Suscripcion s = suscripciones.get(0);
			s.setEstado("0");
			suscripcionRepo.save(s);
			
			//Creo un nuevo registro
			Suscripcion newSuscripcion = new Suscripcion();
			newSuscripcion.setIdcliente(request.getIdpersona());
			newSuscripcion.setIdplan(request.getIdplan());
			newSuscripcion.setEstado("1");
			newSuscripcion.setFechainicio(new Date());
			newSuscripcion.setFechafin(retornarFechaFinSus(newSuscripcion.getFechainicio()));
			suscripcionRepo.save(newSuscripcion);
			
			response.setIdpersona(request.getIdpersona());
			response.setIdplan(request.getIdplan());
			response.setResultado(true);
			
		}else {
			response.setResultado(false);
		}
		
		
		return response;

	}
	
	private Date retornarFechaFinSus(Date fechainicio) {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		c.setTime(fechainicio);
		c.add(Calendar.YEAR, 1);
		return c.getTime();

	}
}
