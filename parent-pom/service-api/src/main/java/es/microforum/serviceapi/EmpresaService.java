package es.microforum.serviceapi;

import java.util.List;

import es.microforum.model.Empresa;

public interface EmpresaService {


	public List<Empresa> consultaListado();
	

	public Empresa consultaPorNif(String nif);
	

	public Empresa consultaPorNombre(String nombre);
	
	
	public Empresa altaModificacion(Empresa empresa);
	

	public void baja(Empresa empresa);
	
		
}
