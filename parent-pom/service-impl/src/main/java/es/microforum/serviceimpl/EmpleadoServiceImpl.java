/**
 * Created on Oct 18, 2011
 */
package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import es.microforum.model.Empleado;
import es.microforum.repository.EmpleadoRepository;
import es.microforum.serviceapi.EmpleadoService;






/**
 * @author Clarence
 *
 */
@Service("empleadoService")
@Repository
@Transactional
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Transactional(readOnly=true)
	public List<Empleado> consultaListado() {
		return Lists.newArrayList(empleadoRepository.findAll());
	}	
	
	@Transactional(readOnly=true)	
	public Empleado consultaPorNombre(String nombre) {
		return empleadoRepository.findByNombre(nombre);
	}

	public Empleado consultaPorDni(String dni) {
		return empleadoRepository.findOne(dni);
	}

	public Empleado altaModificacion(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	public void baja(Empleado empleado) {
		empleadoRepository.delete(empleado);
		
	}

	
}
