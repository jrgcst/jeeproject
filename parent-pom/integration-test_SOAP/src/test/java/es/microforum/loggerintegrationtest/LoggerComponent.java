package es.microforum.loggerintegrationtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerComponent {

	private static final Logger logger = LoggerFactory.getLogger(LoggerComponent.class);
	String dni;
	String nombre;
	
	public void trazaAntes() {
		
//		String name = "lordofthejars";
//		
//		logger.info("Hello from Bar.");
//		
//		
//		logger.debug("In bar my name is {}.", name);
		
		logger.info("Comprobacion de alta de empleado con dni=dni1");
		
	}
	public void trazaDespues(String nombreEmpleado, String dniEmpleado) {
		
		dni=dniEmpleado;
		nombre=nombreEmpleado;
		logger.info("Empleado: "+"{}"+" | "+"{}",dni,nombre);
		
	}

}