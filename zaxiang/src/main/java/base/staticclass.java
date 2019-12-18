package base;

/**
 * PROJECT_name: mytest
 * package: base
 * creat_user: zhangkun19
 * creat_date: 2019/12/05
 * creat_time: 14:37
 * describe: TODO
 **/
public class staticclass {

    /** 用户行为数据结构 **/
    public static class UserBehavior {
        public long userId;         // 用户ID
        public long itemId;         // 商品ID
        public int categoryId;      // 商品类目ID
        public String behavior;     // 用户行为, 包括("pv", "buy", "cart", "fav")
        public long timestamp;      // 行为发生的时间戳，单位秒
    }

    public static void main(String[] args) {
        UserBehavior u = new UserBehavior();
        UserBehavior u1 = new UserBehavior();
        UserBehavior u2 = new UserBehavior();
    }
}

