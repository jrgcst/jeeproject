/**
 * Created on Oct 18, 2011
 */
package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empresa;
import es.microforum.repository.EmpresaRepository;
import es.microforum.serviceapi.EmpresaService;

import com.google.common.collect.Lists;



/**
 * @author Clarence
 *
 */
@Service("empresaService")
@Repository
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Transactional(readOnly=true)
	public List<Empresa> findAll() {
		return Lists.newArrayList(empresaRepository.findAll());
	}	
	
	@Transactional(readOnly=true)	
	public Empresa findByNombre(String nombre) {
		return empresaRepository.findByNombre(nombre);
	}

//	public Empresa findByNif(String nif) {
//		return empresaRepository.findByNif(nif);
//	}
	
	public Empresa findOne(String nif) {
		return empresaRepository.findOne(nif);
	}

	public Empresa save(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	public void delete(Empresa empresa) {
		empresaRepository.delete(empresa);
		
	}


	
}
