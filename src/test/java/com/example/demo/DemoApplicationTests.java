package com.example.demo;

import org.iesvdm.dao.ComercialDAO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class DemoApplicationTests {

	@MockitoBean
	private ComercialDAO comercialDAO;

	@Test
	void contextLoads() {
	}

}
