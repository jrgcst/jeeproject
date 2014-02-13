package es.microforum.servicefrontend.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import es.microforum.serviceapi.EmpleadoService;




@WebService
public class ModificadorSalarioWebService implements IModificadorSalarioWebService{
	private EmpleadoService empleadoService;

	@WebMethod(exclude=true)
	public void setModificadorSalario(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}
	
	@WebMethod(operationName="callModificadorSalario")
	public void callModificadorSalario(Float porcentaje){
		empleadoService.modificacionSalario(porcentaje);
	}
	

}
