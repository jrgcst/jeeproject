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
	public List<Empleado> findAll();
	
	// Find a contact with details by id
	public Empleado findByDni(String dni);
	
	// Find a contact with details by nombre
	public Empleado findByNombre(String nombre);
	
	// Insert or update a contact	
	public Empleado save(Empleado empleado);
	
	// Delete a contact	
	public void delete(Empleado empleado);
	
		
}
