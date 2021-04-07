package org.isj.gesionvehicule.webapp;


import org.isj.gesionvehicule.webapp.test.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;


@SpringBootApplication
public class GesionVehiculeApplication implements CommandLineRunner {

	@Resource
	FilesStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(GesionVehiculeApplication.class, args);
	}

	@Override
	public void run(String... args) {
		storageService.deleteAll();
		storageService.init();
	}
}
