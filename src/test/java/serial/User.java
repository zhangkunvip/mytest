package serial;

import java.io.Serializable;

public class User implements Serializable {


    private static final long serialVersionUID = 147819785695476450L;

    public User(String userName, String password, String sex, String xxx) {
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.xxx = xxx;
    }

    private transient String userName;
    private String password;
    private String sex;
    private static String xxx="10";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        User.xxx = xxx;
    }


}