package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empleado;



public interface EmpleadoService {


	public List<Empleado> consultaListado();
	

	public Empleado consultaPorDni(String dni);
	

	public Empleado consultaPorNombre(String nombre);
	
	
	public Empleado altaModificacion(Empleado empleado);
	

	public void baja(Empleado empleado);
	
		
}
