package com.cesur.examenaddicc22;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

class Ejercicio1 {

    /**
     * Enunciado:
     * 
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola 
     * el número de caracteres, palabras y líneas total.
     * 
     * Modificar solo el código del método.
     * 
     */
    
    static void solucion() {

        estadísticasDeArchivo("ejemplo.txt");
    }

    private static void estadísticasDeArchivo(String archivo)  {

        Integer caracteresconEspacio = 0;
        Integer caracteresSinEspacio = 0;
        Integer palabras = 0;
        Integer lineas = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String s;
            try {
                while ((s = br.readLine()) != null) {
                    caracteresconEspacio += s.length();

                    caracteresSinEspacio += contadorCaracteresSinEspacios(s);

                    palabras += s.split(" ").length;

                    lineas++;
                }
            } catch (Exception e) {
                System.out.println("Error al leer la línea");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo");
        }

        System.out.println("Nombre del archivo: "+archivo);
        System.out.println("Número de caracteres (con espacios) del archivo: "+caracteresconEspacio);
        System.out.println("Número de caracteres (sin espacios) del archivo: "+caracteresSinEspacio);
        System.out.println("Número de palabras del archivo: "+palabras);
        System.out.println("Número de líneas del archivo: "+lineas);


    }
    public static Integer contadorCaracteresSinEspacios(String s) {
        Integer contador = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ')         contador++;
        }
        return contador;
    }
}
