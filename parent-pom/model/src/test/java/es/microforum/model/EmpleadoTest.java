package es.microforum.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EmpleadoTest {

	
	@Before
	public void setUp() throws Exception {
		
		
	}
	
	@Test
	public void testEquals() {
		Empleado empleado1 = new Empleado();
		Empleado empleado2 = new Empleado();
		Empleado empleado3 = new Empleado();
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
		
		empleado3.setCantidadHoras(160d);
		empleado3.setDireccion("dir2");
		empleado3.setDni("dni2");
		empleado3.setEmpleadocol("empleadocol2");
		empleado3.setImagen(null);
		empleado3.setNombre("nom2");
		empleado3.setSalarioAnual(15000d);
		empleado3.setTipoEmpleado("tipo2");
		empleado3.setValorHora(7d);
		empleado3.setVersion(1);
		empleado3.setEmpresa(null);
		assertTrue(empleado2.equals(empleado3));
		assertTrue(!empleado1.equals(empleado2));
	}

}
