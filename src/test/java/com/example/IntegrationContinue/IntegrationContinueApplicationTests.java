package com.example.IntegrationContinue;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class IntegrationContinueApplicationTests {
	Calcul calcul ;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {}

	@AfterAll
	static void  tearDownAfterClass() throws  Exception{}

	@BeforeEach
	void setUp() throws Exception {
		calcul= new Calcul();
	}
	@AfterEach
	void tearDown() throws Exception {
		calcul=null;
	}
	@Test
	void testSomme() {

		if(calcul.somme(2,3) !=6)
			fail("Faux pour deux entiers positifs");

		if(calcul.somme(-2,-3) !=-5)
			fail("Faux pour deux entiers négatifs");

		if(calcul.somme(-2,3) !=1)
			fail("Faux pour deux entiers de signe différent");

		if(calcul.somme(0,3) !=3)
			fail("Faux pour x nul");

		if(calcul.somme(2,0) !=2)
			fail("Faux pour y nul");

		if(calcul.somme(0,0) !=0)
			fail("Faux pour x et y nuls");

	}

}
