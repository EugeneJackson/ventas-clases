package com.example.demo;

import org.iesvdm.dao.ComercialDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.example.demo", "org.iesvdm"})
public class DemoApplication implements CommandLineRunner {

	private final ComercialDAO comercialDAO;

	@Autowired
	public DemoApplication(ComercialDAO comercialDAO) {
		this.comercialDAO = comercialDAO;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		comercialDAO.getAll().forEach(comercial ->
				log.info("Comercial: id={}, nombre={} {}, comisión={}",
						comercial.getId(),
						comercial.getNombre(),
						comercial.getApellido1(),
						comercial.getComision()));

		comercialDAO.findById(1).ifPresentOrElse(comercial -> {
			comercial.setNombre(comercial.getNombre() + " Test");
			comercialDAO.update(comercial);
			log.info("Comercial con id={} actualizado: nombre={}",
					comercial.getId(),
					comercial.getNombre());
		}, () -> log.info("No se encontró un comercial con id=1"));
	}

}
