package com.example.IntegrationContinue;

import org.junit.jupiter.api.*;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;

class CalculTest {
    Calcul calcul;

//    Intanciation pour test avec JUnit
    CalculService calculService = new CalculService() {
        @Override
        public int carre(int x) {
            return x*x;
        }
    };

    //    Instanciation pour teste avec Mockito
    CalculService calculService = mock(CalculService.class); // création d'un objet factice

    //Pre-ambule Test
    @BeforeAll
    static void setUpBeforeClass() throws Exception {}

    @BeforeEach
    void setUp() throws Exception{
        calcul = new Calcul(calculService);
    }


    //Post-ambule Test
    @AfterAll
    static void tearDownAfterClass() throws Exception {}

    @AfterEach
    void tearDown() throws Exception {
        calcul = null;
    }


    // Body test

//    @Test
//    void testSommeCarre(){ // test avec JUnit
//        assertTrue(calcul.sommeCarre(2, 3) == 13, "calcul exact");
//    }

    @Test
    void testSommeCarre(){ // test avec Mockito
        when(calculService.carre(2)).thenReturn(4); // simule le comportemant de la méthode "carre"
        doReturn(9).when(calculService).carre(3); // simule le comportemant de la méthode "carre"
//        when(calculService.carre(3)).thenReturn(9);
        assertTrue(calcul.sommeCarre(2, 3) == 13, "calcul exact");
        verify(calculService).carre(3); // Vérifier la méthode carré avec le paramètre 3, ça va réussir parceque verify utilise les resultats de "assertTrue", donc elle ne fonctionne que pour des inputs qui ont déjà été testé dans cette méthode de test
        verify(calculService, times(1)).carre(2); // Vérifier que le carre a été appelé une seule fois avec le paramètre 2
        verify(calculService, times(2)).carre(Mockito.any(Integer.class)); // Vérifier que le carre a été appelé une seule fois pour un  paramètre entier quelconque

        InOrder ordre = Mockito.inOrder(calculService);
        ordre.verify(calculService).carre(2);
        verify(calculService, times(2)).carre(Mockito.any(Integer.class)); // Vérifier l'ordre d'itération
    }

    @Test
    void testSomme() {
        assumeTrue(System.getenv("OS").startsWith("Windows")); // permet de lancer le test si nous somme dans un environnement windows

        if(calcul.somme(2, 3) != 5)
            fail("faux pour deux entiers positifs");
        if(calcul.somme(-2, -3) != -5)
            fail("faux pour deux entiers négatifs");
        if(calcul.somme(-2, 3) != 1)
            fail("faux pour deux entiers de signe différent");
        if(calcul.somme(0, 2) != 2)
            fail("faux pour x nul");
        if(calcul.somme(2, 0) != 2)
            fail("faux pour y nul");
        if(calcul.somme(0, 0) != 0)
            fail("faux pour x et y nuls");
    }

    @Test
    void testDivision() {

        assumeTrue(System.getenv("OS").startsWith("Windows")); // permet de lancer le test si nous somme dans un environnement windows

        assertFalse(calcul.division(6, 3) == 0, "2 entiers positifs");
        assertEquals(2, calcul.division(-6, -3), "2 entiers négatifs");
        assertNull(calcul.division(-6, 3), "2 entiers de signe différent");
        assertTrue(calcul.division(0, 3) == 0, "entiers x null");
        Throwable e = null;
        try{
            calcul.division(2,0);
        }catch (Throwable ex){
            e = ex;
        }
        assertTrue(e instanceof  ArithmeticException);
        e = null;
        try{
            calcul.division(0, 0);
        }catch (Throwable ex){
            e = ex;
        }
        assertTrue(e instanceof ArithmeticException);
    }

    @Test
    void testSoustraction() {
        assumeTrue(System.getenv("OS").startsWith("Windows")); // permet de lancer le test si nous somme dans un environnement windows

        if(calcul.soustraction(2, 3) != -1)
            fail("faux pour deux entiers positifs avec x < y");
        if(calcul.soustraction(-2, -3) != 1)
            fail("faux pour deux entiers négatifs avec x > y");
        if(calcul.soustraction(-2, 3) != -5)
            fail("faux pour x négatif et y positif");
        if(calcul.soustraction(0, 2) != -2)
            fail("faux pour x nul et y positif");
        if(calcul.soustraction(2, 0) != 2)
            fail("faux pour y nul");
        if(calcul.soustraction(0, 0) != 0)
            fail("faux pour x et y nuls");
    }

    @Test
    void testModulo() {

        assumeTrue(System.getenv("OS").startsWith("Windows")); // permet de lancer le test si nous somme dans un environnement windows

        assertNotNull(calcul.modulo(6, 3), "2 entiers positifs");
        assertEquals(18, calcul.multiplication(-6, -3), "2 entiers négatifs");
        assertEquals(18, calcul.multiplication(6, 3), "2 entiers positifs");
        assertEquals(-18, calcul.multiplication(6, -3), "2 entiers de signe différents");
        assertNotNull(calcul.multiplication(-6, 3), "2 entiers de signe différent");
        assertTrue(calcul.multiplication(0, 3) == 0, "entiers x null");

    }

    @Test
    void testMultiplication() {

        assumeTrue(System.getenv("OS").startsWith("Windows")); // permet de lancer le test si nous somme dans un environnement windows

        assertFalse(calcul.multiplication(6, 3) == 0, "2 entiers positifs");
        assertEquals(18, calcul.multiplication(-6, -3), "2 entiers négatifs");
        assertEquals(18, calcul.multiplication(6, 3), "2 entiers positifs");
        assertEquals(-18, calcul.multiplication(6, -3), "2 entiers de signe différents");
        assertNotNull(calcul.multiplication(-6, 3), "2 entiers de signe différent");
        assertTrue(calcul.multiplication(0, 3) == 0, "entiers x null");
    }

}