import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main
{
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,9,10,11,12,14,15,16);
        Integer result = numbers.stream().filter(p -> p % 4 == 0).map(p -> p + 1).reduce(0,(acc, p) -> (acc + p) % 2);
        System.out.println(result);

    }

}
