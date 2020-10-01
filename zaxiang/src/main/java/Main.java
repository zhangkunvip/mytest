import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Deque<Integer> queue = new ArrayDeque<>();
        int count = 0;

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(5);

        while(!queue.isEmpty()) {
            int value = queue.pollFirst();
            count++;
            if(count == n) {
                System.out.println(value);
                break;
            }
            queue.addLast(value * 10 + 2);
            queue.addLast(value * 10 + 3);
            queue.addLast(value * 10 + 5);
        }
    }
}