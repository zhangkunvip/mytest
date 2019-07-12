package hotswap;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by yz on 2018/2/7.
 * 热部署原理测试
 */
public class App {
    public static void main(String[] args) throws Exception {
        loadHelo();
        //动态替换class
        System.gc();      //释放当前jvm所占用的class文件,因为装载进去变成正在运行,所以要释放
        Thread.sleep(1000);

        File old = new File("C:\\Users\\yz\\Desktop\\NioDemo\\hostwap-demo\\target\\classes\\com\\yz\\hostwap\\demo\\HelloService.class");
        old.delete();
        File newFile = new File("C:\\Users\\yz\\Desktop\\NioDemo\\hostwap-demo\\HelloService.class");
        newFile.renameTo(old); //新的文件替换旧的文件
        loadHelo();      //重新装载
    }

    /**
     * 使用自定义类加载器
     *
     * @throws Exception
     */
    public static void loadHelo() throws Exception {
        MyClassLoader loader = new MyClassLoader();
        Class<?> clazz = loader.findClass("hotswap.HelloService");
        //通过反射的方式
        Object object = clazz.newInstance();
        Method method = clazz.getMethod("sayHello");
        method.invoke(object);
        System.out.println(object.getClass() + " " + object.getClass().getClassLoader());
    }
}