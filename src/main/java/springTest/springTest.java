package springTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import springTest.ioc.MessageService;

/**
 * PROJECT_name: mytest
 * package: PACKAGE_NAME
 * creat_user: zhangkun19
 * creat_date: 2019/09/30
 * creat_time: 9:31
 * describe: TODO
 **/
public class springTest {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:springtest/application.xml");

        System.out.println("context 启动成功");

        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);
        // 这句将输出: hello world
        System.out.println(messageService.getMessage());
    }
}
