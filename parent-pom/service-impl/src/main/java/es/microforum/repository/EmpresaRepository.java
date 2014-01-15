/**
 * Created on Oct 18, 2011
 */
package es.microforum.repository;

import org.springframework.data.repository.CrudRepository;

import es.microforum.model.Empresa;

/**
 * @author Clarence
 *
 */
public interface EmpresaRepository extends CrudRepository<Empresa, String> {

	//public Empresa findByNif(String nif);
	public Empresa findByNombre(String nombre);
	
		
}
