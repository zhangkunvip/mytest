import com.google.common.collect.Lists;

import java.util.List;

/**
 * PROJECT_name: mytest
 * package: PACKAGE_NAME
 * creat_user: zhangkun19
 * creat_date: 2020/02/19
 * creat_time: 17:42
 * describe: TODO
 **/
public class shardingrule {


    public static void main(String[] args) {
        shardingByCustomerIdInProduction();
    }

    /**
     * 根据商家id算路由到哪个库哪张表
     * 线上生产环境
     */

    public static void shardingByCustomerIdInProduction() {
        //商家id
        List<Integer> customerIds = Lists.newArrayList(1416661);
        //分库数量
        int dbNum = 8;
        //每个库分表数量
        int tableNum = 64;
        //计算商家id路由到哪个库哪张表
        shardingByCustomerId(dbNum, tableNum, customerIds);
    }

    private static void shardingByCustomerId(int dbNum, int tableNum, List<Integer> customerIds) {
        //计算商家id路由到哪个库哪张表
        for (Integer customerId : customerIds) {
            //取模 = id % (数据库数量 * 每个库表数量)
            int temp = customerId % (dbNum * tableNum);
            //计算路由到哪个库
            String dbIndex = String.format("%02d", temp / tableNum);
            //计算路由到哪张表
            String tableIndex = String.format("%02d", temp % tableNum);
            //商家id所在库所在表
            System.out.println("商家id：" + customerId + "，所在库：" + dbIndex + "，所在表：" + tableIndex);
            System.out.println("select * from orderinfo" + tableIndex + " where yn=1 and customer_id=" + customerId + " and delivery_id='';");
            System.out.println("select * from orderinfo_e" + tableIndex + " where yn=1 and customer_id=" + customerId + " and delivery_id='';");
        }
    }
}
