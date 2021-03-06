package es.microforum.model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

public class EmpresaTest {
	SimpleDateFormat sdf;
	Empresa empresa1;
	Empresa empresa2;
	Empresa empresa3;
	
	@Before
	public void setUp() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		empresa1 = new Empresa();
		empresa2 = new Empresa();
		empresa3 = new Empresa();
	}

	@Test
	public void testEquals() throws ParseException {
		empresa1.setDireccionFiscal("direccion1");
		empresa1.setEmpleados(null);
		empresa1.setFechaInicioActividades(sdf.parse("1999-01-01"));
		empresa1.setNif("nif1");
		empresa1.setNombre("nom1");
		empresa1.setVersion(1);
		empresa2.setDireccionFiscal("direccion2");
		empresa2.setEmpleados(null);
		empresa2.setFechaInicioActividades(sdf.parse("1999-01-01"));
		empresa2.setNif("nif2");
		empresa2.setNombre("nom2");
		empresa2.setVersion(1);
		empresa3.setDireccionFiscal("direccion2");
		empresa3.setEmpleados(null);
		empresa3.setFechaInicioActividades(sdf.parse("1999-01-01"));
		empresa3.setNif("nif2");
		empresa3.setNombre("nom2");
		empresa3.setVersion(1);
		assertTrue(empresa2.equals(empresa3));
		assertTrue(!empresa1.equals(empresa2));
		
		
	}

}
