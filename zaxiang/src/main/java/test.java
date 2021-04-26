import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class test {
    public static void main(String[] args) {
        List a = Arrays.asList("a", "b");
        a.add("c");


        String str1 = "通话";
        String str2 = "重地";
        System.out.println(String.format("str1：%d | str2：%d", str1.hashCode(), str2.hashCode()));
        System.out.println(str1.equals(str2));


        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream2 = Stream.iterate(5, (x) -> x + 3).limit(4);
        stream2.forEach(System.out::println); //
        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);
    }
}
