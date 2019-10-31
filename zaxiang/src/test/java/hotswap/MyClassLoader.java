package hotswap;

import java.io.IOException;
import java.io.InputStream;

/**
 * * Created by yz on 2018/2/7.
 * * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader {

    /**
     * 重写父类方法 Alt+Insert
     *
     * @param name : 包名+类名路径
     * @return
     * @throws ClassNotFoundException
     */

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        //将传进来的java文件转换成class文件
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        InputStream is = this.getClass().getResourceAsStream(fileName);
        try {
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.findClass(name);
    }
}

