package es.microforum.serviceapi;

import java.util.List;






import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.microforum.model.Empleado;



public interface EmpleadoService {


	public List<Empleado> consultaListado();
	

	public Empleado consultaPorDni(String dni);
	public Empleado consultaPorDniEmpresa(String dni);
	

	//public Empleado consultaPorNombre(String nombre);
	public Page<Empleado> consultaPaginable(Pageable pageable);
	public Page<Empleado> consultaPorNombrePaginable(String nombre, Pageable pageable);
	
	
	public Empleado altaModificacion(Empleado empleado);
	

	public void baja(Empleado empleado);
	
	public void modificacionSalario(Float porcentaje);
	
		
}
