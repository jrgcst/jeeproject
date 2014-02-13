package es.microforum.servicefrontend.soap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import es.microforum.model.Empleado;
import es.microforum.serviceapi.EmpleadoService;


public class JaxServiceClientTest {
	private IModificadorSalarioWebService modificadorSalarioWebService;
	private JdbcTemplate jdbcTemplate;
	EmpleadoService empleadoService;
	//List<Empleado> empleados;

	@Before
	public void setUp() throws Exception {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationClientContext.xml");
			DataSource dataSource = (DataSource) context.getBean("dataSource");
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.execute("DELETE FROM empleado where dni='dniTEST1'");
			jdbcTemplate.execute("INSERT INTO empresa values('nifTEST', 'nombreTEST', 'direccionTEST', '2014-02-26 00:00:00', '0')");
			jdbcTemplate.execute("INSERT INTO `jee`.`empleado` (`dni`, `nombre`, `direccion`, `tipoEmpleado`, `empleadocol`, `salarioAnual`, `valorHora`, `cantidadHoras`, `nif`, `version`) VALUES ('dni1TEST', 'nombre1TEST', 'direccion1TEST', 'tipoE1TEST', 'empcol1TEST', '12000', '10', '100', 'nif1TEST', '0')");
			jdbcTemplate.execute("INSERT INTO `jee`.`empleado` (`dni`, `nombre`, `direccion`, `tipoEmpleado`, `empleadocol`, `salarioAnual`, `valorHora`, `cantidadHoras`, `nif`, `version`) VALUES ('dni2TEST', 'nombre2TEST', 'direccion2TEST', 'tipoE2TEST', 'empcol2TEST', '15000', '10', '100', 'nif2TEST', '0')");
			modificadorSalarioWebService = (IModificadorSalarioWebService) context.getBean("jaxModificadorSalarioWebService");
			empleadoService = context.getBean("empleadoService", EmpleadoService.class);
		} catch (Throwable t) {
			t.printStackTrace();
			fail();
		}
	}

	@Test
	public void testCallSumador() {
		//empleados = empleadoService.consultaListado();
		try {
			modificadorSalarioWebService.callModificadorSalario(3.5f);
			assertTrue(empleadoService.consultaPorDni("dni1TEST").getSalarioAnual()==12420d);
			assertTrue(empleadoService.consultaPorDni("dni2TEST").getSalarioAnual()==15525d);
			modificadorSalarioWebService.callModificadorSalario(-3.5f);
			assertTrue(empleadoService.consultaPorDni("dni1TEST").getSalarioAnual()==11985.3d);
			assertTrue(empleadoService.consultaPorDni("dni2TEST").getSalarioAnual()==14981.625d);
		} catch (Throwable t) {
			t.printStackTrace();
			fail();
		}
	}
	
	@After
	public void after() {
		jdbcTemplate.execute("DELETE FROM empleado where dni='dni1TEST'");
		jdbcTemplate.execute("DELETE FROM empleado where dni='dni2TEST'");
		jdbcTemplate.execute("DELETE FROM empresa where nif='nifTEST1'");
		
	}


}
