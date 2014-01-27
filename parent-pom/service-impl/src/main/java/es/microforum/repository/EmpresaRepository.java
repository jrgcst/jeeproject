package es.microforum.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import es.microforum.model.Empresa;


public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, String> {

	//public Empresa findByNombre(String nombre);
	
		
}
