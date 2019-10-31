package zktest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BTraceTest {
    public int add(Integer a, Integer b) {
        return a + b;
    }

    public void run() {
//        Integer a = Integer.parseInt((int)(Math.random() * 1000));
        Integer a = (int) (Math.random() * 1000);
        Integer b = (int) (Math.random() * 1000);
        System.out.println(add(a, b));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        BTraceTest bTraceTest = new BTraceTest();
        bReader.readLine();
        for (int i = 0; i < 10; i++) {
            bTraceTest.run();
        }
    }
}
