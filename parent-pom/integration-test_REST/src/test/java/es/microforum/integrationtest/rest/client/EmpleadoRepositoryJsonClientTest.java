package es.microforum.integrationtest.rest.client;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import es.microforum.model.Empleado;

@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@ActiveProfiles("integration_test")
public class EmpleadoRepositoryJsonClientTest {

	@Autowired
	ApplicationContext context;
	
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void before() {
		DataSource dataSource = (DataSource) context.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("DELETE FROM empleado where dni='dniTEST1'");
		jdbcTemplate.execute("INSERT INTO empresa values('nifTEST1', 'nombreTEST1', 'direccionTEST1', '2014-02-26 00:00:00', '0')");
	}
	
	RestTemplate restTemplate = new RestTemplate();

	@Test
	public void getTest() {
		try {
			jdbcTemplate.execute("INSERT INTO `jee`.`empleado` (`dni`, `nombre`, `direccion`, `tipoEmpleado`, `empleadocol`, `salarioAnual`, `valorHora`, `cantidadHoras`, `nif`, `version`) VALUES ('dniTEST1', 'nombreTEST1', 'direccionTEST1', 'tipoETEST1', 'empcolTEST1', '10000', '10', '100', 'nifTEST1', '0')");
			Resource<Empleado> resource = getEmpleado(new URI("http://localhost:8081/service-frontend-0.0.3-SNAPSHOT/empleado/dniTEST1"));
			assertTrue(resource.getContent().getNombre().equals("nombreTEST1"));
			jdbcTemplate.execute("DELETE FROM empleado where nombre like 'nombreTEST1%'");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	private Resource<Empleado> getEmpleado(URI uri) {
		return restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Empleado>>() {
		}).getBody();

	}
	
	@Test
	public void postTest() throws RestClientException, URISyntaxException {
		jdbcTemplate.execute("DELETE FROM empleado where dni like 'dniTEST1'");
		String url = "http://localhost:8081/service-frontend-0.0.3-SNAPSHOT/empleado";
		String acceptHeaderValue = "application/json";

		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.valueOf(acceptHeaderValue));
		requestHeaders.setAccept(mediaTypes);
		requestHeaders.setContentType(MediaType.valueOf(acceptHeaderValue));
		HttpMethod post = HttpMethod.POST;

		String body = "{\"dni\":\"dniTEST1\",\"nombre\":\"nombreTEST1_BORRAR!\",\"direccion\":\"direccionTEST1_BORRAR!\",\"tipoEmpleado\":\"tipoETEST1_BORRAR!\",\"empleadocol\":\"empcolTEST1_BORRAR!\",\"salarioAnual\":\"10000\",\"valorHora\":\"10\",\"cantidadHoras\":\"100\",\"empresa\":{\"nif\":\"nifTEST1\",\"version\":\"0\"}}";
		HttpEntity<String> entity = new HttpEntity<String>(body, requestHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, post, entity, String.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
		int count = jdbcTemplate.queryForInt("select count(*) from empleado where nombre like 'nombreTEST1_BORRAR!%'");
		assertTrue(count == 1);
		jdbcTemplate.execute("DELETE FROM empleado where nombre like 'nombreTEST1_BORRAR!%'");
	}
	
	@Test
	public void putTest() throws RestClientException, URISyntaxException {
		jdbcTemplate.execute("INSERT INTO `jee`.`empleado` (`dni`, `nombre`, `direccion`, `tipoEmpleado`, `empleadocol`, `salarioAnual`, `valorHora`, `cantidadHoras`, `nif`, `version`) VALUES ('dniTEST1', 'nombreTEST1', 'direccionTEST1', 'tipoETEST1', 'empcolTEST1', '10000', '10', '100', 'nifTEST1', '0')");
		String url = "http://localhost:8081/service-frontend-0.0.3-SNAPSHOT/empleado/dniTEST1";
		String acceptHeaderValue = "application/json";

		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.valueOf(acceptHeaderValue));
		requestHeaders.setAccept(mediaTypes);
		requestHeaders.setContentType(MediaType.valueOf(acceptHeaderValue));
		HttpMethod put = HttpMethod.PUT;

		String body = "{\"nombre\":\"nombreTEST1_MODIFICADO!\"}";
		HttpEntity<String> entity = new HttpEntity<String>(body, requestHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, put, entity, String.class);
		assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
		int count = jdbcTemplate.queryForInt("select count(*) from empleado where nombre = 'nombreTEST1_MODIFICADO!'");
		assertTrue(count == 1);
		jdbcTemplate.execute("DELETE FROM empleado where nombre = 'nombreTEST1_MODIFICADO!'");
	}
	
	@Test
	public void deleteTest() {
		try {
			jdbcTemplate.execute("INSERT INTO `jee`.`empleado` (`dni`, `nombre`, `direccion`, `tipoEmpleado`, `empleadocol`, `salarioAnual`, `valorHora`, `cantidadHoras`, `nif`, `version`) VALUES ('dniTEST1', 'nombreTEST1', 'direccionTEST1', 'tipoETEST1', 'empcolTEST1', '10000', '10', '100', 'nifTEST1', '0')");
			int count = jdbcTemplate.queryForInt("select count(*) from empleado where dni='dniTEST1'");
			assertTrue(count == 1);
			restTemplate.delete("http://localhost:8081/service-frontend-0.0.3-SNAPSHOT/empleado/dniTEST1");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		int count = jdbcTemplate.queryForInt("select count(*) from empleado where dni='dniTEST1'");
		assertTrue(count == 0);
	}
	
	@After
	public void after() {
		jdbcTemplate.execute("DELETE FROM empleado where dni='dniTEST1'");
		jdbcTemplate.execute("DELETE FROM empresa where nif='nifTEST1'");
		
	}


}
