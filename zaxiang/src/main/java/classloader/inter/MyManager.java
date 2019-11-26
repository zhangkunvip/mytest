package classloader.inter;

import java.time.LocalTime;

public class MyManager implements BaseManager {

    @Override
    public void logic() {
        System.out.println(LocalTime.now() + ": Java类的热加载");
    }
}