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
	public List<Empresa> consultaListado();
	
	// Find a contact with details by id
	public Empresa consultaPorNif(String nif);
	
	// Find a contact with details by nombre
	public Empresa consultaPorNombre(String nombre);
	
	// Insert or update a contact	
	public Empresa altaModificacion(Empresa empresa);
	
	// Delete a contact	
	public void baja(Empresa empresa);
	
		
}
