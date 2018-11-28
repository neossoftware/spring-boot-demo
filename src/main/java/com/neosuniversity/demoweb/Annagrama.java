package com.neosuniversity.demoweb;

import java.util.Arrays;

public class Annagrama {

    public static void main(String args[]){

        System.out.println("is a anagrama: " + IsAnagram("Quieren Poder ","Enrique Pedro"));
        System.out.println("is a palindromo: " + isPalindromo("dabale arroz a la zorra el abad"));
    }

    public static boolean IsAnagram(String cad1,String cad2){

        char[] array1 = cad1.trim().replaceAll("\\s","").toLowerCase().toCharArray();
        char[] array2 = cad2.trim().replaceAll("\\s","").toLowerCase().toCharArray();

        Arrays.sort(array1);
        Arrays.sort(array2);

        return new String(array1).equals(new String (array2));
    }

    public static boolean isPalindromo(String cad){
        String outSpace = cad.trim().replaceAll("\\s","");
        StringBuilder build= new StringBuilder(outSpace);
        String reverse = build.reverse().toString();
        System.out.println(reverse);
        return outSpace.equals(reverse);
    }

}
