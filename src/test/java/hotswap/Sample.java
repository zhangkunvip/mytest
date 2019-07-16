package hotswap;

public class Sample {
  //step.1   
  static int b = 2;   
  //step.2   
  static {   
    b = 3;   
  }   

  public static void main(String[] args) {   
    Sample s = new Sample();   
    System.out.println(s.b);   
    //b=3   
  }   
}