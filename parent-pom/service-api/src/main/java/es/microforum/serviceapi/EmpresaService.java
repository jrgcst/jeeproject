package es.microforum.serviceapi;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.microforum.model.Empresa;

public interface EmpresaService {


	public List<Empresa> consultaListado();
	

	public Empresa consultaPorNif(String nif);
	

	//public Empresa consultaPorNombre(String nombre);
	public Page<Empresa> consultaPaginable(Pageable pageable);
	public Page<Empresa> consultaPorNombrePaginable(String nombre, Pageable pageable);
	
	
	public Empresa altaModificacion(Empresa empresa);
	

	public void baja(Empresa empresa);
	
		
}
