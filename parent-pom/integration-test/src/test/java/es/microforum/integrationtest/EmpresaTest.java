package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpresaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
@TransactionConfiguration(defaultRollback = true)
public class EmpresaTest {
	SimpleDateFormat sdf;

	@Autowired
	private EmpresaService empresaService;

	Empresa empresa1;
	Empresa empresa2;

	@Before
	public void setUp() throws Exception {
		sdf = new SimpleDateFormat("yyyyMMdd");
		empresa1 = new Empresa();
		empresa2 = new Empresa();
		empresa1.setDireccionFiscal("dir1");
		empresa1.setNif("nif1");
		empresa1.setNombre("nom1");
		empresa1.setVersion(1);
		empresa1.setFechaInicioActividades(sdf.parse("20140117"));
		empresa1.setEmpleados(null);
		empresa2.setDireccionFiscal("dir2");
		empresa2.setNif("nif2");
		empresa2.setNombre("nom2");
		empresa2.setVersion(1);
		empresa2.setFechaInicioActividades(sdf.parse("20140202"));
		empresa2.setEmpleados(null);

	}

	@Test
	@Transactional
	public void testAltaEmpresa() {
		empresaService.altaModificacion(empresa1);
		String comprobacion = null;
		List<Empresa> empresas = empresaService.consultaListado();
		//System.out.println("Comprobacion de alta, de la empresa con nif = nif1");
		//System.out.println("Empresas: ");
		for (Empresa empresa : empresas) {
			if ((empresa.getNif()).equals("nif1")) {
				comprobacion = "true";
			}
			//System.out.println(empresa.getNif() + " | " + empresa.getNombre());
		}
		assertTrue(comprobacion.equals("true"));

	}

	@Test
	@Transactional
	public void testBajaEmpresa() {
		empresaService.baja(empresa1);
		List<Empresa> empresas = empresaService.consultaListado();
		//System.out.println("Comprobacion de baja, de la empresa con nif = nif1");
		//System.out.println("Numero de empresas: " + empresas.size());
		assertTrue(empresas.size() == 0);
	}

	@Test
	@Transactional
	public void testModificacionEmpresa() {
		empresaService.altaModificacion(empresa1);
		empresaService.altaModificacion(empresa2);
		List<Empresa> empresas = empresaService.consultaListado();
//		System.out.println("Comprobacion de alta de 2 empresas");
//		System.out.println("Empresas: ");
//		for (Empresa empresa : empresas) {
//			System.out.println(empresa.getNif() + " | " + empresa.getNombre());
//		}
		Empresa empresaA = empresaService.consultaPorNif(empresa1.getNif());
		empresaA.setNombre("nomA");
		empresaService.altaModificacion(empresaA);
		String comprobacion = null;
		empresas = empresaService.consultaListado();
		//System.out.println("Comprobacion de modificacion del nombre, de la empresa con nif = nif1");
		//System.out.println("Empresas: ");
		for (Empresa empresa : empresas) {
			if ((empresa.getNombre()).equals("nomA")) {
				comprobacion = "true";
			}
			//System.out.println(empresa.getNif() + " | " + empresa.getNombre());
		}
		assertTrue(comprobacion.equals("true"));
	}

	@Test
	@Transactional
	public void testConsultaEmpresa() {
		empresaService.altaModificacion(empresa1);
		empresaService.altaModificacion(empresa2);
		List<Empresa> empresas = empresaService.consultaListado();
		//System.out.println("Comprobacion del listado de todas las empresas");
		for (Empresa empresa : empresas) {
			//System.out.println("- Empresa: " + empresa.getNif() + " | "+ empresa.getNombre());
		}
		Empresa empresaB = empresaService.consultaPorNif("nif1");
		Empresa empresaC = empresaService.consultaPorNombre("nom2");
//		System.out.println("Comprobacion de busqueda por nif, de la empresa con nif = nif1");
//		System.out.println(empresaB.getNif() + " | " + empresaB.getNombre());
//		System.out.println("Comprobacion de busqueda por nombre, de la empresa con nombre = nom2");
//		System.out.println(empresaC.getNif() + " | " + empresaC.getNombre());
		assertTrue(empresas.size() == 2);
		assertTrue(empresaB.getNombre().equals("nom1"));
		assertTrue(empresaC.getNif().equals("nif2"));
	}

}
