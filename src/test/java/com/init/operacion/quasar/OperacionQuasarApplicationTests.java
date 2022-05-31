package com.init.operacion.quasar;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.init.operacion.quasar.controller.InteligenciaRebeldeController;


@SpringBootTest
class OperacionQuasarApplicationTests {

	@org.springframework.beans.factory.annotation.Autowired
	private InteligenciaRebeldeController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
}
