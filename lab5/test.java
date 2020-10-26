import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test {
    public static void main(String[] args) {
        Optional<String> intStr = Optional.of("23");
        Optional<Integer> intValue = intStr.map(Integer::parseInt);

        // Optional wrapper over another Optional wrapper holding result
        Optional<Optional<Integer>> intValue2 = intStr.map(str -> Optional.of(Integer.parseInt(str)));
        // using flatMap() will unwrap the Optional
        Optional<Integer> intValue3 = intStr.flatMap(str -> Optional.of(Integer.parseInt(str)));

        System.out.println(intValue);
        System.out.println(intValue2);
        System.out.println(intValue3);

        List<Integer> listOfIntegers = Arrays.asList("5", "10", "15", "20").stream().map(Integer::parseInt)
                .collect(Collectors.toList());

        // using flatMap() when working with a stream of streams
        List<String> listOfIntegers2 = Arrays.asList(Arrays.asList("5", "10"), Arrays.asList("15", "20")).stream()
                .flatMap(List::stream).collect(Collectors.toList());

        System.out.println(listOfIntegers);
        System.out.println(listOfIntegers2);

    }
}
