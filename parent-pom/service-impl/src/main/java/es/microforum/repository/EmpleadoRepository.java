package es.microforum.repository;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.microforum.model.Empleado;

//public interface EmpleadoRepository extends CrudRepository<Empleado, String> {
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, String> {

	//public Empleado findByNombre(String nombre);
	//public Page<Empleado> findAll(Pageable pageable);

}
