/**
 * Created on Oct 17, 2011
 */
package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empresa;

/**
 * @author Clarence
 *
 */
public interface EmpresaService {

	// Find all contacts
	public List<Empresa> findAll();
	
	// Find a contact with details by id
	//public Empresa findByNif(String nif);
	public Empresa findOne(String nif);
	
	// Find a contact with details by nombre
	public Empresa findByNombre(String nombre);
	
	// Insert or update a contact	
	public Empresa save(Empresa empresa);
	
	// Delete a contact	
	public void delete(Empresa empresa);
	
		
}
