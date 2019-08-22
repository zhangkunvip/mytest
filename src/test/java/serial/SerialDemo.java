package serial;

import java.io.*;

public class SerialDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        FileOutputStream fos = new FileOutputStream("C:\\object.out");
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        User user1 = new User("xiaoming", "145263", "female","20");
//        oos.writeObject(user1);
//        System.out.println(oos);
//        oos.flush();
//        oos.close();
//
        FileInputStream fis = new FileInputStream("C:\\object.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user2 = (User) ois.readObject();
        System.out.println(user2.getUserName() + " " + user2.getPassword() + " " + user2.getSex() + " " + user2.getXxx() );
    }
}
 

 
