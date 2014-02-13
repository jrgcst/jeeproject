package es.microforum.servicefrontend.soap;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;





import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.microforum.model.Empleado;
import es.microforum.model.Empresa;
import es.microforum.serviceapi.EmpleadoService;
import es.microforum.serviceapi.EmpresaService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
@TransactionConfiguration(defaultRollback=true)
public class JaxServiceClientTest {
	@Autowired
	private IModificadorSalarioWebService modificadorSalarioWebService;
	
	@Autowired
	private EmpleadoService empleadoService;
	@Autowired
	private EmpresaService empresaService;
	

	
	Empleado empleado1;
	Empleado empleado2;
	Empresa empresa1;
	SimpleDateFormat sdf;
	
	@Before
	public void setUp() throws Exception {
		try {
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
			empleado1.setDni("dni1TEST");
			empleado1.setEmpleadocol("empleadocol1");
			empleado1.setNombre("nom1");
			empleado1.setSalarioAnual(12000d);
			empleado1.setTipoEmpleado("tipo1");
			empleado1.setValorHora(7d);
			empleado1.setEmpresa(empresa1);
			empleado2.setCantidadHoras(160d);
			empleado2.setDireccion("dir2");
			empleado2.setDni("dni2TEST");
			empleado2.setEmpleadocol("empleadocol2");
			empleado2.setNombre("nom2");
			empleado2.setSalarioAnual(15000d);
			empleado2.setTipoEmpleado("tipo2");
			empleado2.setValorHora(7d);
			empleado2.setEmpresa(empresa1);
			
			ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");		
			modificadorSalarioWebService = (IModificadorSalarioWebService) context.getBean("jaxModificadorSalarioWebService");
			//empleadoService = context.getBean("empleadoService", EmpleadoService.class);
			
			Empresa emp = empresaService.altaModificacion(empresa1);
			empleado1.setEmpresa(emp);
			empleadoService.altaModificacion(empleado1);
			empleado2.setEmpresa(emp);
			empleadoService.altaModificacion(empleado2);
			
		} catch (Throwable t) {
			t.printStackTrace();
			fail();
		}
	}

	@Test
	@Transactional
	public void testCallModificadorSalario() {
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
	



}
