package lambada;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Data {
    private static List<PersonModel> list = null;

    static {
        PersonModel wu = new PersonModel("wu qi", 18, "男");
        PersonModel wu1 = new PersonModel("wu qi", 18, "男");
        PersonModel zhang = new PersonModel("zhang san", 19, "男");
        PersonModel wang = new PersonModel("wang si", 20, "女");
        PersonModel zhao = new PersonModel("zhao wu", 20, "男");
        PersonModel chen = new PersonModel("chen liu", 21, "男");
        list = Arrays.asList(wu, zhang, wang, zhao, chen,wu1);
    }

    public static List<PersonModel> getData() {
        return list;
    }


    public static void flatMapString() {
        List<PersonModel> data = Data.getData();
        //返回类型不一样
        List<String> collect = data.stream()
                .flatMap(person -> Arrays.stream(person.getName().split(" "))).collect(toList());

        List<Stream<String>> collect1 = data.stream()
                .map(person -> Arrays.stream(person.getName().split(" "))).collect(toList());

        //用map实现
        List<String> collect2 = data.stream()
                .map(person -> person.getName().split(" "))
                .flatMap(Arrays::stream).collect(toList());
        //另一种方式
        List<String> collect3 = data.stream()
                .map(person -> person.getName().split(" "))
                .flatMap(str -> Arrays.asList(str).stream()).collect(toList());
        System.out.println();
    }

    public static void main(String[] args) {
        reduceTest();
    }

    public static void reduceTest(){
        //累加，初始化值是 10
        Integer reduce = Stream.of(1, 2, 3, 4)
                .reduce(10, (count, item) ->{
                    System.out.println("count:"+count);
                    System.out.println("item:"+item);
                    return count + item;
                } );
        System.out.println(reduce);

        Integer reduce1 = Stream.of(1, 2, 3, 4)
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce1);

        String reduce2 = Stream.of("1", "2", "3")
                .reduce("0", (x, y) -> (x + "," + y));
        System.out.println(reduce2);
    }

}