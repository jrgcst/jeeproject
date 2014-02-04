package es.microforum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empresa;
import es.microforum.repository.EmpresaRepository;
import es.microforum.serviceapi.EmpresaService;




import com.google.common.collect.Lists;



@Service("empresaService")
@Repository
@Transactional
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Transactional(readOnly=true)
	public List<Empresa> consultaListado() {
		return Lists.newArrayList(empresaRepository.findAll());
	}	
	
//	@Transactional(readOnly=true)	
//	public Empresa consultaPorNombre(String nombre) {
//		return empresaRepository.findByNombre(nombre);
//	}
	
	@Transactional(readOnly=true)
	public Page<Empresa> consultaPaginable(Pageable pageable){
		return empresaRepository.findAll(pageable);
	}
	@Transactional(readOnly=true)
	public Page<Empresa> consultaPorNombrePaginable(String nombre, Pageable pageable){
		return empresaRepository.findByNombre(nombre, pageable);
	}

	@Transactional(readOnly=true)
	public Empresa consultaPorNif(String nif) {
		return empresaRepository.findOne(nif);
	}

	public Empresa altaModificacion(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	public void baja(Empresa empresa) {
		empresaRepository.delete(empresa);
		
	}


	
}
