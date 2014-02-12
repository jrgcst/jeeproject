//package es.microforum.servicefrontend.soap;
//
//import javax.jws.WebMethod;
//import javax.jws.WebService;
//
//import com.techiekernel.ws.jaxws.document.Sumador;
//import com.techiekernel.ws.jaxws.document.Server;
//
//@WebService
//public class ModificadorSalarioWebService implements IModificadorSalarioWebService{
//	private Sumador sumador;
//
//	@WebMethod(exclude=true)
//	public void setSumador(Sumador sumador) {
//		this.sumador = sumador;
//	}
//	
//	@WebMethod(operationName="callSumador")
//	public double callSumador(int sumando1, int sumando2){
//		return sumador.suma(sumando1, sumando2);
//	}
//	
//
//}
