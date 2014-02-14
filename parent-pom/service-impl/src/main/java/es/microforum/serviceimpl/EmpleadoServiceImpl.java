package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;
import es.microforum.repository.EmpleadoRepository;
import es.microforum.serviceapi.EmpleadoService;



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
	
//	@Transactional(readOnly=true)	
//	public Empleado consultaPorNombre(String nombre) {
//		return empleadoRepository.findByNombre(nombre);
//	}
	
	@Transactional(readOnly=true)	
	public Page<Empleado> consultaPaginable(Pageable pageable) {
		return empleadoRepository.findAll(pageable);
	}
	@Transactional(readOnly=true)	
	public Page<Empleado> consultaPorNombrePaginable(String nombre, Pageable pageable) {
		return empleadoRepository.findByNombre(nombre, pageable);
	}

	public Empleado consultaPorDni(String dni) {
		return empleadoRepository.findOne(dni);
	}
	public Empleado consultaPorDniEmpresa(String dni) {
		Empleado empleado = empleadoRepository.findOne(dni);
		Empresa empresa = empleado.getEmpresa();
		empresa.getEmpleados().size();
		return empleado;
	}

	public Empleado altaModificacion(Empleado empleado) {
		return empleadoRepository.save(empleado);
	}

	public void baja(Empleado empleado) {
		empleadoRepository.delete(empleado);
		
	}
	
	public void modificacionSalario(Float porcentaje){
		List<Empleado> empleados = Lists.newArrayList(empleadoRepository.findAll());
		for(Empleado e : empleados){
			Double salarioAnual = e.getSalarioAnual();
			e.setSalarioAnual(salarioAnual+((salarioAnual*porcentaje)/100));
			empleadoRepository.save(e);
		}
	}

	
}
