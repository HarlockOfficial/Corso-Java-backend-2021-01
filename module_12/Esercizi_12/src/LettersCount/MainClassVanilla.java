package LettersCount;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainClassVanilla {
    public static void main(String[] args) {
        List<String> nomi = Arrays.asList("Marco", "Giovanni", "Filiberto", "Francesco", "Franco");
        long sum = 0;
        for(String nome: nomi){
            if(nome.toLowerCase().startsWith("f")){
                sum += nome.length();
            }
        }
        System.out.println(sum);
    }
}
