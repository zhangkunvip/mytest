package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * PROJECT_name: github
 * package: collection
 * creat_user: zhangkun19
 * creat_date: 2019/07/12
 * creat_time: 16:15
 * describe: TODO
 **/
public class testHash {
    public static void main(String[] args) {
        Map hash = new HashMap();
        hash.put(Objects.hash("a", "b"), "2233");
        KeyObj newone = new KeyObj("a", "b");
        CompareObj compareObj = new CompareObj("a");
        hash.put(newone, "223311");
       Object x =  hash.get(newone);

System.out.println(newone.hashCode());
System.out.println(compareObj.hashCode());
       System.out.println(newone.equals(compareObj));
    }

   static class KeyObj {
        public KeyObj(String aa, String bb) {
            this.aa = aa;
            this.bb = bb;
        }

        String aa;
        String bb;

        public String getAa() {
            return aa;
        }

        public void setAa(String aa) {
            this.aa = aa;
        }

        public String getBb() {
            return bb;
        }

        public void setBb(String bb) {
            this.bb = bb;
        }


       @Override
       public int hashCode() {
           return Objects.hash(aa, bb);
       }
   }
   static class CompareObj {
        public CompareObj(String aa) {
            this.aa = aa;
        }

        String aa;

        public String getAa() {
            return aa;
        }

        public void setAa(String aa) {
            this.aa = aa;
        }



       @Override
       public int hashCode() {
           return Objects.hash(aa, "b");
       }
   }
}
