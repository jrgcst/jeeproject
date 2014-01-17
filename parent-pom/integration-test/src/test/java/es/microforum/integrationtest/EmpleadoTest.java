package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml") //esto crea un contexto
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
@TransactionConfiguration(defaultRollback=true)
//@Transactional
public class EmpleadoTest {
	
	@Autowired
	private EmpleadoService empleadoService;
	
	Empleado empleado1;
	Empleado empleado2;
	
	//GenericXmlApplicationContext ctx; //se ha de eliminar este ctx y poner @Autowired (http://stackoverflow.com/questions/4166983/rollback-transaction-when-testing-service-with-spring-hibernate-junit)
	
	@Before
	public void setUp() throws Exception {
		empleado1 = new Empleado();
		empleado2 = new Empleado();
		empleado1.setCantidadHoras(160d);
		empleado1.setDireccion("dir1");
		empleado1.setDni("dni1");
		empleado1.setEmpleadocol("empleadocol1");
		empleado1.setImagen(null);
		empleado1.setNombre("nom1");
		empleado1.setSalarioAnual(12000d);
		empleado1.setTipoEmpleado("tipo1");
		empleado1.setValorHora(7d);
		empleado1.setVersion(1);
		empleado1.setEmpresa(null);
		empleado2.setCantidadHoras(160d);
		empleado2.setDireccion("dir2");
		empleado2.setDni("dni2");
		empleado2.setEmpleadocol("empleadocol2");
		empleado2.setImagen(null);
		empleado2.setNombre("nom2");
		empleado2.setSalarioAnual(15000d);
		empleado2.setTipoEmpleado("tipo2");
		empleado2.setValorHora(7d);
		empleado2.setVersion(1);
		empleado2.setEmpresa(null);
		
		//ctx = new GenericXmlApplicationContext();
		
	}

	
	
	@Test
	@Transactional
	public void testAltaEmpleado() {
		//ctx.load("classpath:app-context.xml");
		//ctx.refresh();
		//EmpleadoService empleadoService = ctx.getBean("empleadoService", EmpleadoService.class);
				
		empleadoService.altaModificacion(empleado1);
		String comprobacion=null;
		List<Empleado> empleados = empleadoService.consultaListado();
		System.out.println("Comprobacion de alta de empleado con dni=dni1");
		System.out.println("Empleados: ");
		for (Empleado empleado: empleados) {
			if((empleado.getDni()).equals("dni1")){
				comprobacion="true";
			}
			System.out.println(empleado.getDni()+" | "+empleado.getNombre());
		}
		assertTrue(comprobacion.equals("true"));
		
	}
	
	@Test
	@Transactional
	public void testBajaEmpleado() {
		empleadoService.baja(empleado1);
		String comprobacion=null;
		List<Empleado> empleados = empleadoService.consultaListado();
		System.out.println("Comprobacion de baja de empleado con dni=dni1");
		System.out.println("Numero de empleados: " + empleados.size());
		assertTrue(empleados.size()==0);
	}
	
	@Test
	@Transactional
	public void testModificacionEmpleado() {
		empleadoService.altaModificacion(empleado1);
		empleadoService.altaModificacion(empleado2);
		List<Empleado> empleados = empleadoService.consultaListado();
		System.out.println("Comprobacion de alta de 2 empleados");
		System.out.println("Empleados: ");
		for (Empleado empleado: empleados) {
			System.out.println(empleado.getDni()+" | "+empleado.getNombre());
		}
		Empleado empleadoA = empleadoService.consultaPorDni(empleado1.getDni());
		empleadoA.setNombre("nomA");
		empleadoService.altaModificacion(empleadoA);
		String comprobacion=null;
		empleados = empleadoService.consultaListado();
		System.out.println("Comprobacion de modificacion del nombre de empleado con dni=dni1");
		System.out.println("Empleados: ");
		for (Empleado empleado: empleados) {
			if((empleado.getNombre()).equals("nomA")){
				comprobacion="true";
			}
			System.out.println(empleado.getDni()+" | "+empleado.getNombre());
		}
		assertTrue(comprobacion.equals("true"));
	}
	
	@Test
	@Transactional
	public void testConsultaEmpleado() {
		empleadoService.altaModificacion(empleado1);
		empleadoService.altaModificacion(empleado2);
		List<Empleado> empleados = empleadoService.consultaListado();
		System.out.println("Comprobacion del listado de todos los empleados");
		for (Empleado empleado : empleados) {
			System.out.println(empleado.getDni()+" | "+empleado.getNombre());
		}
		Empleado empleadoB = empleadoService.consultaPorDni("dni1");
		Empleado empleadoC = empleadoService.consultaPorNombre("nom2");
		System.out.println("Comprobacion de busqueda por dni del empleado con dni=dni1");
		System.out.println(empleadoB.getDni()+" | "+empleadoB.getNombre());
		System.out.println("Comprobacion de busqueda por nombre del empleado con nombre=nom2");
		System.out.println(empleadoC.getDni()+" | "+empleadoC.getNombre());
		assertTrue(empleados.size()==2);
		assertTrue(empleadoB.getNombre().equals("nom1"));
		assertTrue(empleadoC.getDni().equals("dni2"));
	}
	
}
