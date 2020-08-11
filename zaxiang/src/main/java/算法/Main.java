package 算法;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {

//        Map<String, Integer> map = new ConcurrentHashMap<>(16);
//        map.computeIfAbsent(
//                "AaAa",
//                key -> {
//                    return map.computeIfAbsent(
//                            "BBBB",
//                            key2 -> 42);
//                }
//        );

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16);
        String why = map.get("why1");
        System.out.println("第一次why----" + why);

        map.computeIfAbsent("why1", key -> getValue());
        why = map.get("why1");
        System.out.println("第二次why----" + why);


    }

    private static String getValue(){
        return "why计算";
    }

}