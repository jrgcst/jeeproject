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




//import es.microforum.loggerintegrationtest.LoggerComponent;
import es.microforum.model.Empleado;
import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceapi.EmpresaService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
@TransactionConfiguration(defaultRollback=true)
public class EmpleadoTest {
	SimpleDateFormat sdf;
	
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private EmpresaService empresaService;
	
	Empleado empleado1;
	Empleado empleado2;
	Empresa empresa1;
	
		
	@Before
	public void setUp() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		empleado1 = new Empleado();
		empleado2 = new Empleado();
		empresa1 = new Empresa();
		empresa1.setDireccionFiscal("dir1");
		empresa1.setNif("nif1");
		empresa1.setNombre("nom1");
		empresa1.setFechaInicioActividades(sdf.parse("2014-01-17"));
		empleado1.setCantidadHoras(160d);
		empleado1.setDireccion("dir1");
		empleado1.setDni("dni1");
		empleado1.setEmpleadocol("empleadocol1");
		empleado1.setNombre("nom1");
		empleado1.setSalarioAnual(12000d);
		empleado1.setTipoEmpleado("tipo1");
		empleado1.setValorHora(7d);
		empleado1.setEmpresa(empresa1);
		empleado2.setCantidadHoras(160d);
		empleado2.setDireccion("dir2");
		empleado2.setDni("dni2");
		empleado2.setEmpleadocol("empleadocol2");
		empleado2.setNombre("nom2");
		empleado2.setSalarioAnual(15000d);
		empleado2.setTipoEmpleado("tipo2");
		empleado2.setValorHora(7d);
		empleado2.setEmpresa(empresa1);
	}

	
	
	@Test
	@Transactional
	public void testAltaEmpleado() {
		//LoggerComponent loggerComponent = new LoggerComponent();
		//loggerComponent.trazaAntes();
		Empresa emp = empresaService.altaModificacion(empresa1);
		empleado1.setEmpresa(emp);
		empleadoService.altaModificacion(empleado1);
		
		String comprobacion=null;
		List<Empleado> empleados = empleadoService.consultaListado();
		//System.out.println("Comprobacion de alta de empleado con dni=dni1");
		//System.out.println("Empleados: ");
		for (Empleado empleado: empleados) {
			if((empleado.getDni()).equals("dni1")){
				comprobacion="true";
			}
			//System.out.println(empleado.getDni()+" | "+empleado.getNombre());
			//loggerComponent.trazaDespues(empleado.getNombre(), empleado.getDni());
			
		}
		assertTrue(comprobacion.equals("true"));
		
	}
	
	@Test
	@Transactional
	public void testBajaEmpleado() {
		Empresa emp = empresaService.altaModificacion(empresa1);
		empleado1.setEmpresa(emp);
		empleadoService.altaModificacion(empleado1);
		empleadoService.baja(empleado1);
		List<Empleado> empleados = empleadoService.consultaListado();
		//System.out.println("Comprobacion de baja de empleado con dni=dni1");
		//System.out.println("Numero de empleados: " + empleados.size());
		assertTrue(empleados.size()==0);
	}
	
	@Test
	@Transactional
	public void testModificacionEmpleado() {
		Empresa emp = empresaService.altaModificacion(empresa1);
		empleado1.setEmpresa(emp);
		empleadoService.altaModificacion(empleado1);
		empleado2.setEmpresa(emp);
		empleadoService.altaModificacion(empleado2);
		List<Empleado> empleados = empleadoService.consultaListado();
		//System.out.println("Comprobacion de alta de 2 empleados");
		//System.out.println("Empleados: ");
//		for (Empleado empleado: empleados) {
//			System.out.println(empleado.getDni()+" | "+empleado.getNombre());
//		}
		Empleado empleadoA = empleadoService.consultaPorDni(empleado1.getDni());
		empleadoA.setNombre("nomA");
		empleadoService.altaModificacion(empleadoA);
		String comprobacion=null;
		empleados = empleadoService.consultaListado();
		//System.out.println("Comprobacion de modificacion del nombre de empleado con dni=dni1");
		//System.out.println("Empleados: ");
		for (Empleado empleado: empleados) {
			if((empleado.getNombre()).equals("nomA")){
				comprobacion="true";
			}
			//System.out.println(empleado.getDni()+" | "+empleado.getNombre());
		}
		assertTrue(comprobacion.equals("true"));
	}
	
	@Test
	@Transactional
	public void testConsultaEmpleado() {
		Empresa emp = empresaService.altaModificacion(empresa1);
		empleado1.setEmpresa(emp);
		empleadoService.altaModificacion(empleado1);
		empleado2.setEmpresa(emp);
		empleadoService.altaModificacion(empleado2);
		List<Empleado> empleados = empleadoService.consultaListado();
		//System.out.println("Comprobacion del listado de todos los empleados");
//		for (Empleado empleado : empleados) {
//			System.out.println(empleado.getDni()+" | "+empleado.getNombre());
//		}
		Empleado empleadoB = empleadoService.consultaPorDni("dni1");

//		System.out.println("Comprobacion de busqueda por dni del empleado con dni=dni1");
//		System.out.println(empleadoB.getDni()+" | "+empleadoB.getNombre());
//		System.out.println("Comprobacion de busqueda por nombre del empleado con nombre=nom2");
//		System.out.println(empleadoC.getDni()+" | "+empleadoC.getNombre());
		assertTrue(empleados.size()==2);
		assertTrue(empleadoB.getNombre().equals("nom1"));

	}
	
}
