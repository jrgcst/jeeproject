/**
 * Created on Oct 18, 2011
 */
package es.microforum.repository;

import org.springframework.data.repository.CrudRepository;

import es.microforum.model.Empleado;

/**
 * @author Clarence
 *
 */
public interface EmpleadoRepository extends CrudRepository<Empleado, String> {

	public Empleado findByNombre(String nombre);
	
		
}
