package lambada;

public class Student {
    int stature;
    String name;
    int age;

    public Student(String name,int stature,  int age) {
        this.stature = stature;
        this.name = name;
        this.age = age;
    }

    public int getStature() {
        return stature;
    }

    public void setStature(int stature) {
        this.stature = stature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
