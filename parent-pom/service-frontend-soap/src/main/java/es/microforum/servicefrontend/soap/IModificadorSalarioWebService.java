package es.microforum.servicefrontend.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface IModificadorSalarioWebService {

	@WebMethod(operationName = "callModificadorSalario")
	public void callModificadorSalario(Float porcentaje);


}