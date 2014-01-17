/**
 * Created on Oct 17, 2011
 */
package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empleado;



/**
 * @author Clarence
 *
 */
public interface EmpleadoService {

	// Find all contacts
	public List<Empleado> consultaListado();
	
	// Find a contact with details by id
	public Empleado consultaPorDni(String dni);
	
	// Find a contact with details by nombre
	public Empleado consultaPorNombre(String nombre);
	
	// Insert or update a contact	
	public Empleado altaModificacion(Empleado empleado);
	
	// Delete a contact	
	public void baja(Empleado empleado);
	
		
}
