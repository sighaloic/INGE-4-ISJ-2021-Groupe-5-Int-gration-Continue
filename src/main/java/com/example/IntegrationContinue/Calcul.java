package com.example.IntegrationContinue;

public class Calcul {

    CalculService calculService;

    public Calcul(CalculService calculService) {
        this.calculService = calculService;
    }

    public int sommeCarre(int x, int y){
        return somme(calculService.carre(x), calculService.carre(y));
    }

    public int somme(int x, int y){
        return x + y;
    }
    public int division(int x, int y){
        if(y == 0)
            throw  new ArithmeticException();
        return x / y;
    }
    public int multiplication(int x, int y){ return  x * y;}
    public int modulo(int x, int y){ return  x % y;}
    public int soustraction(int x, int y){ return  x - y;}
}
