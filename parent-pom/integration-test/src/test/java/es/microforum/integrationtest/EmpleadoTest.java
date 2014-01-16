package es.microforum.integrationtest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:app-context.xml") //esto crea un contexto ya
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class EmpleadoTest {
	Empleado empleado1;
	Empleado empleado2;
	
	GenericXmlApplicationContext ctx; //se ha de eliminar este ctx y poner @Autowired (http://stackoverflow.com/questions/4166983/rollback-transaction-when-testing-service-with-spring-hibernate-junit)
	
	@Before
	public void setUp() throws Exception {
		empleado1 = new Empleado();
		empleado2 = new Empleado();
		
		ctx = new GenericXmlApplicationContext();
		
	}

	
	
	@Test
//	@Transactional
	public void testAlta() {
		ctx.load("classpath:app-context.xml");
		ctx.refresh();
		
		EmpleadoService empleadoService = ctx.getBean("empleadoService", EmpleadoService.class);
		
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
		empleadoService.save(empleado1);
		String comprobacion=null;
		List<Empleado> empleados = empleadoService.findAll();
		for (Empleado empleado: empleados) {
			if((empleado.getDni()).equals("dni1")){
				comprobacion="true";
			}
		}
		assertTrue(comprobacion.equals("true"));
		
	}
	
	//@Test
	public void testBaja() {
		fail("Not yet implemented");
	}
	
	//@Test
	public void testModificacion() {
		fail("Not yet implemented");
	}
	
	//@Test
	public void testConsulta() {
		fail("Not yet implemented");
	}

}
