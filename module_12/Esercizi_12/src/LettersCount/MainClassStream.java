package LettersCount;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainClassStream {
    public static void main(String[] args) {
        List<String> nomi = Arrays.asList("Marco", "Giovanni", "Filiberto", "Francesco", "Franco");
        long res = nomi.stream()
                .map(String::toLowerCase)
                .filter(s -> s.startsWith("f"))
                .map(String::length).reduce(0, Integer::sum);
        System.out.println(res);
    }
}
