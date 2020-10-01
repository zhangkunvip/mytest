package 热加载;

public class ClassLoadTest {
    public static void main(String[] args) {
        new Thread(new MsgHandle()).start();
    }
}