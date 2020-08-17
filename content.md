#JAVABASE
> <a href="http://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650139864&idx=1&sn=d78c1d113d332d98b612dc1d95913531" target="_blank">《Java开发手册》解读：大整数传输为何禁用Long类型? </a>
- 双精度分配了8个字节，总共64位，从左至右划分是1位符号、11位指数、52位有效数字。如下图所示，以0.7为例，展示了双精度浮点数的存储方式。
![alt text](image\微信截图_20200817140248.png "双精度浮点数的存储方式")

  - 一问：JS的Number类型能安全表达的最大整型数值是多少？为什么（注意要求更严，是安全表达）？
  - 二问：在Long取值范围内，2的指数次整数转换为JS的Number类型，不会有精度丢失，但能放心使用么？
  - 三问：我们一般都知道十进制数转二进制浮点数有可能会出现精度丢失，但精度丢失具体怎么发生的？
  - 四问：如果不幸中招，服务端正在使用Long类型作为大整数的返回，有哪些办法解决？




##JVM
> <a href="https://www.cnblogs.com/xdecode/p/8948277.html" target="_blank">Java内存模型与指令重排</a>
- 原子性；
- 有序性；
- 可见性；
- 指令重排：CPU 指令重排、编译器优化重排；
  - 一条汇编指令的执行是可以分为很多步骤得，分为不同的硬件执行： 
    - 取指 IF；
    - 译码和取寄存器操作数 ID；
    - 执行或者有效地址计算 EX（ALU 逻辑计算单元）；
    - 存储器访问 MEM；
    - 写回 WB（寄存器）。
- Happen-Before 规则。
>如果光靠 sychronized 和 volatile 来保证程序执行过程中的原子性、有序性、可见性，那么代码将会变得异常繁琐。JMM 提供了 Happen-Before 规则来约束数据之间是否存在竞争，线程环境是否安全。具体如下：

  - 顺序原则
    
    一个线程内保证语义的串行性：a = 1; b = a + 1;

  - volatile 规则
    
    volatile 变量的写先发生于读，从而保证了 volatile 变量的可见性。

  - 锁规则
    
    解锁（unlock）必然发生在随后的加锁（lock）前。

  - 传递性
    
    A 先于 B，B 先于 C，那么 A 必然先于 C。

  - 线程启动、中断、终止

        线程的 start() 方法先于它的每一个动作；
        线程的中断 interrupt() 先于被中断线程的代码；
        线程的所有操作先于线程的终结 Thread.join()。

  - 对象终结
    
    对象的构造函数执行结束先于 finalize() 方法。

----
#SPRING


----
#RPC
![alt text](image\dubbo-extension.jpg "dubbo-extension")
![alt text](image\dubboFrameWork.png "dubboFrameWork")



----
#CACHE
##redis
###Redis的内存淘汰策略问题 
> <a href="https://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247491843&idx=2&sn=816871265811b80b52d2073ec604317b" target="_blank">数据库系统设计概述</a>
####内存淘汰
- noeviction(默认策略)：对于写请求不再提供服务，直接返回错误（DEL请求和部分特殊请求除外）
- allkeys-lru：从所有key中使用LRU算法进行淘汰
- volatile-lru：从设置了过期时间的key中使用LRU算法进行淘汰
- allkeys-random：从所有key中随机淘汰数据
- volatile-random：从设置了过期时间的key中随机淘汰
- volatile-ttl：在设置了过期时间的key中，根据key的过期时间进行淘汰，越早过期的越优先被淘汰

####LRU在Redis中的实现
- 1.近似LRU算法 Redis使用的是近似LRU算法，它跟常规的LRU算法还不太一样。近似LRU算法通过随机采样法淘汰数据，每次随机出5（默认）个key，从里面淘汰掉最近最少使用的key。
可以通过maxmemory-samples参数修改采样数量：例：maxmemory-samples 10 maxmenory-samples配置的越大，淘汰的结果越接近于严格的LRU算法
Redis为了实现近似LRU算法，给每个key增加了一个额外增加了一个24bit的字段，用来存储该key最后一次被访问的时间。
- 2.Redis3.0对近似LRU的优化 Redis3.0对近似LRU算法进行了一些优化。新算法会维护一个候选池（大小为16），池中的数据根据访问时间进行排序，第一次随机选取的key都会放入池中，随后每次随机选取的key只有在访问时间小于池中最小的时间才会放入池中，直到候选池被放满。当放满后，如果有新的key需要放入，则将池中最后访问时间最大（最近被访问）的移除。
当需要淘汰的时候，则直接从池中选取最近访问时间最小（最久没被访问）的key淘汰掉就行。
- 3.LRU算法的对比 我们可以通过一个实验对比各LRU算法的准确率，先往Redis里面添加一定数量的数据n，使Redis可用内存用完，再往Redis里面添加n/2的新数据，这个时候就需要淘汰掉一部分的数据，如果按照严格的LRU算法，应该淘汰掉的是最先加入的n/2的数据。生成如下各LRU算法的对比图


----
#DB
> <a href="https://www.cnblogs.com/WeaRang/p/13417772.html" target="_blank">数据库系统设计概述</a>
##mysql
> <a href="http://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247491492&idx=1&sn=27cdbf23efe2b1b6314e208ccb2b0b17" target="_blank">47 张图带你 MySQL 进阶</a>
##主从
- 主备模式
    
    为了解决单点模式的风险，主备模式产生。目前，主备模式应该是各个线上服务系统的最低配置了，比如你在各个云平台购买的数据库服务一般都会开启备份功能。一旦主节点出现问题，还可以切换到备份节点，不至于整个系统瘫痪。
    主备又分为一主一备、一主多备。多个备份是为了保证更高的安全性，万一主节点出现问题的时候，碰巧备份节点也出问题呢。
    当主节点出现问题的时候要切换到备份节点，切换方式又分为手动切换和自动切换。手动切换具有一定的延时，当主节点出现问题时，只能等运维人员发现或者收到系统通知。
    
- 主从模式

    主从配置一般都是和读写分离相结合，主服务器负责写数据，从服务器负责读数据，并保证主服务器的数据及时同步到从服务器。
    主从模式又分为一主一从、一主多从和多主多从，越往后部署越复杂，同时，系统稳定性更高。主从模式可以更好的分担数据库压力，将插入更新操作和查询操作分开，提高系统整体性能。

- 主节点

        1、当主节点上进行 insert、update、delete 操作时，会按照时间先后顺序写入到 binlog 中；
        2、当从节点连接到主节点时，主节点会创建一个叫做 binlog dump 的线程；
        3、一个主节点有多少个从节点，就会创建多少个 binlog dump 线程；
        4、当主节点的 binlog 发生变化的时候，也就是进行了更改操作，binlog dump 线程就会通知从节点 (Push模式)，并将相应的 binlog 内容发送给从节点；

- 从节点

        当开启主从同步的时候，从节点会创建两个线程用来完成数据同步的工作。
        I/O线程： 此线程连接到主节点，主节点上的 binlog dump 线程会将 binlog 的内容发送给此线程。此线程接收到 binlog 内容后，再将内容写入到本地的 relay log。
        SQL线程： 该线程读取 I/O 线程写入的 relay log，并且根据 relay log 的内容对从数据库做对应的操作。
##事务
###事务的四大特性（ACID）
- 原子性（Atomicity，或称不可分割性）
> 「一个事务必须被视为一个不可分割的最小工作单元，整个事务中所有的操作要么全部提交成功，要么全部失败回滚，对于一个事务来说，不可能只执行其中的一部分操作，这就是事务的原子性」

- 一致性（Consistency）
> 「数据库总是从一个一致性的状态转换到另外一个一致性的状态，在事务开始之前和之后，数据库的完整性约束没有被破坏。在前面的例子中，事务结束前后A、B账户总额始终保持不变」

- 隔离性（Isolation）
> 「隔离性是指，事务内部的操作与其他事务是隔离的，并发执行的各个事务之间不能互相干扰。严格的隔离性，对应了事务隔离级别中的Serializable (可串行化)，但实际应用中出于性能方面的考虑很少会使用可串行化。」

- 持久性（Durability）
> 「持久性是指事务一旦提交，它对数据库的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。」

###事务的隔离级别
在前文中我们介绍了隔离性，但实际上隔离性比想象的要复杂的多。在SQL标准中定义了四种隔离级别，每一种隔离级别都规定了一个事务所做的修改，哪些在事务内和事务间是可见的，哪些是不可见的，较低级别的隔离通常可以执行跟高的并发，系统的开销也更低

- 未提交读（READ UNCOMMITTED）
> 在这个隔离级别下，事务的修改即使没有提交，对其他事务也是可见的。事务可以读取未提交的数据，这也被称之为脏读。这个级别会带来很多问题，从性能上来说，READ UNCOMMITTED不会比其他的级别好太多，但是却会带来很多问题，除非真的有非常必要的理由，在实际应用中一般很少使用。

- 提交读（REDA COMMITED）
> 大多数数据系统的默认隔离级别都是REDA COMMITED（MySql不是），REDA COMMITED满足前面提到的隔离性的简单定义：一个事务开始时，只能看到已经提交的事务所做的修改。换句话说，一个事物从开始直到提交前，所做的修改对其他事务不可见。这个级别有时候也叫做不可重复读，因为执行两次相同的查询可能会得到不同的结果。

- 可重复读（REPEATABLE READ）
> REPEATABLE READ解决了脏读以及不可重复度的问题。该级别保证了同一个事务多次读取同样记录的结果是一致的。但是理论上，可重复度还是无法解决另外一个幻读的问题。所谓幻读，指的是当某个事务在读取某个范围内的记录时，另外一个事务又在该范围内插入了新的记录，当之前的事务再次读取该范围的记录时，就会产生幻行。不可重复读跟幻读的区别在于，「前者是数据发生了变化，后者是数据的行数发生了变化」。

- 可串行化（SERIALIZABLE）
> SERIALIZABLE是最高的隔离级别，它通过强制事务串行执行，避免前面说的幻读。简单来说SERIALIZABLE会在读取的每一行数据上都加锁，所以可能会导致大量的超时和锁争用的问题。实际应用中也很少使用这个隔离级别，只有在非常需要确保数据一致性而且可以接受没有并发的情况下，才考虑此级别。

- @Transactional 的写法

> 开始主题@Transactional如果只这样写，Spring框架的事务基础架构代码将默认地 只 在抛出运行时和unchecked exceptions时才标识事务回滚。也就是说，当抛出个RuntimeException 或其子类例的实例时。（Errors 也一样 - 默认地 - 标识事务回滚。）从事务方法中抛出的Checked exceptions将不被标识进行事务回滚。

- 1 让checked例外也回滚：在整个方法前加上 @Transactional(rollbackFor=Exception.class)

- 2 让unchecked例外不回滚：@Transactional(notRollbackFor=RunTimeException.class)

- 3 不需要事务管理的(只查询的)方法：@Transactional(propagation=Propagation.NOT_SUPPORTED)

注意：如果异常被try｛｝catch｛｝了，事务就不回滚了，如果想让事务回滚必须再往外抛try｛｝catch｛throw Exception｝。



> <a href="http://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247491906&idx=2&sn=a54e6fbde09a7a64397409f7f9f945ad" target="_blank">走进spring事务</a>
![alt text](image\MVCC.jpg "MVCC")
>


----

#ES

----
#MQ

##rabbitMq
###RabbitMQ 七战 Kafka，差异立现
> <a href="http://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247491130&idx=1&sn=a3348f22be3a85211154d2f27b3d5bb8" target="_blank">RabbitMQ 七战 Kafka，差异立现 </a>
- 什么时候使用RabbitMQ以及什么时候使用Kafka？

        优先选择RabbitMQ的条件：

        高级灵活的路由规则；
        消息时序控制（控制消息过期或者消息延迟）；
        高级的容错处理能力，在消费者更有可能处理消息不成功的情景中（瞬时或者持久）；
        更简单的消费者实现。


        优先选择Kafka的条件：

        严格的消息顺序；
        延长消息留存时间，包括过去消息重放的可能；
        传统解决方案无法满足的高伸缩能力。
- 
        当开发复杂的软件系统时，我们可能被诱导使用同一个消息平台去实现所有必须的消息用例。但是，从我的经验看，通常同时使用这两个消息平台能够带来更多的好处。
        例如，在一个事件驱动的架构系统中，我们可以使用RabbitMQ在服务之间发送命令，并且使用Kafka实现业务事件通知。
        原因是事件通知常常用于事件溯源，批量操作（ETL风格），或者审计目的，因此Kafka的消息留存能力就显得很有价值。
        相反，命令一般需要在消费者端做额外处理，并且处理可以失败，所以需要高级的容错处理能力。
        这里，RabbitMQ在功能上有很多闪光点。以后我可能会写一篇详细的文章来介绍，但是你必须记住--你的里程（mileage）可能会变化，因为适合性取决于你的特定需求。


###RabbitMQ之交换机的四种类型和属性
> <a href="https://blog.csdn.net/hry2015/article/details/79118804" target="_blank">RabbitMQ之交换机的四种类型和属性</a> 
#### 交换机主要包括如下4种类型：
- Direct exchange（直连交换机）
- Fanout exchange（扇型交换机）
- Topic exchange（主题交换机）
- Headers exchange（头交换机）
#### 订阅有两种类型：
- 临时（ephemeral）订阅，这种订阅只有在消费者启动并且运行的时候才存在。一旦消费者退出，相应的订阅以及尚未处理的消息就会丢失。
- 持久（durable）订阅，这种订阅会一直存在，除非主动去删除。消费者退出后，消息系统会继续维护该订阅，并且后续消息可以被继续处理。


----
#BIGDATA
> <a href="https://blog.csdn.net/zhaodedong/article/details/105963444" target="_blank">实时数据架构&实时数据仓库，你到底了解多少？</a>

##kafka
###
> <a href="http://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650138701&idx=3&sn=181b58773b36a50ec313e00b8624c07a" target="_blank">Kafka 为什么能那么快的 6 个原因</a>
- 1 利用 Partition 实现并行处理
- 2 顺序写磁盘
- 3 充分利用 Page Cache
- 4 零拷贝技术

        4.1 网络数据持久化到磁盘 (Producer 到 Broker)
        传统模式下，数据从网络传输到文件需要 4 次数据拷贝、4 次上下文切换和两次系统调用。
        
        data = socket.read()// 读取网络数据 
        File file = new File() 
        file.write(data)// 持久化到磁盘 
        file.flush()

        这一过程实际上发生了四次数据拷贝：
    
        首先通过 DMA copy 将网络数据拷贝到内核态 Socket Buffer
        然后应用程序将内核态 Buffer 数据读入用户态（CPU copy）
        接着用户程序将用户态 Buffer 再拷贝到内核态（CPU copy）
        最后通过 DMA copy 将数据拷贝到磁盘文件
        
-        
        4.2 磁盘文件通过网络发送（Broker 到 Consumer）
        传统方式实现：先读取磁盘、再用 socket 发送，实际也是进过四次 copy
        
        buffer = File.read 
        Socket.send(buffer)
        
        这一过程可以类比上边的生产消息：
        
        首先通过系统调用将文件数据读入到内核态 Buffer（DMA 拷贝）
        然后应用程序将内存态 Buffer 数据读入到用户态 Buffer（CPU 拷贝）
        接着用户程序通过 Socket 发送数据时将用户态 Buffer 数据拷贝到内核态 Buffer（CPU 拷贝）
        最后通过 DMA 拷贝将数据拷贝到 NIC Buffer
- 5 批处理
- 6 数据压缩
###消息的【确认机制】

       默认值是0。在面试中常被问到。
       producer有个ack参数，有三个值，分别代表：
       1） 不在乎是否写入成功；   【0】
       2）写入leader成功；            【1】
       3）写入leader和所有副本都成功；【-1】
       要求非常可靠的话可以牺牲性能设置成最后一种。为了保证消息不丢失，至少要设置为1，也就是说至少保证leader将消息保存成功。
       
       设置发送数据是否需要服务端的反馈,有三个值0,1,-1，分别代表3种状态：
       0: producer不会等待broker发送ack。生产者只要把消息发送给broker之后，就认为发送成功了，这是第1种情况；
       1: 当leader接收到消息之后发送ack。生产者把消息发送到broker之后，并且消息被写入到本地文件，才认为发送成功，这是第2种情况；
       -1: 当所有的follower都同步消息成功后发送ack。不仅是主的分区将消息保存成功了，而且其所有的分区的副本数也都同步好了，才会被认为发动成功，这是第3种情况。

##spark

##storm

##flink



> <a href="https://blog.csdn.net/baichoufei90/article/details/84328666" target="_blank">MPP架构</a>
----
#算法
##一致性hash
> <a href="https://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247492221&idx=2&sn=c4ed5cb2d72518bcd89b80f313b4524e" target="_blank">图解一致性哈希算法，全网（小区局域网）最通俗易懂</a>
#IO


----
####版本管理 git/svn
####Mysql  sql执行计划
####Java基础  异常/error，多线程安全
####Spring熟悉程度  注解/spring事务传播机制
####Redis熟悉程度   数据类型，分布式锁。跳表原理等（面试效果好的，可以继续问一下）
####Mq熟悉程度  可选： 为什么使用mq，是否使用过。是否有顺序？
####Es熟悉程度   可选： 使用方法，
####RPC调用熟悉程度   服务注册、发现原理。调用时是否通过注册中心。Rpc调用过程描述（面试效果好的，可以继续问一下）
####整体感觉


##other
- <a href="https://www.jianshu.com/p/191d1e21f7ed/" target="_blank">Markdown基本语法</a>
- <a href="https://mp.weixin.qq.com/s?__biz=MzIxNjA5MTM2MA==&mid=2652438634&idx=1&sn=38cfe4a47c71ff16c7ec4cb6c873ea7f" target="_blank">10个 解放双手的 IDEA 插件，少些冤枉代码</a>
- <a href="https://www.cnblogs.com/zuge/p/7397255.html" target="_blank">再见，Navicat！同事安利的这个IDEA的兄弟，真香！（全图注解） DataGrip</a>
- <a href="http://mp.weixin.qq.com/s?__biz=Mzg3MjA4MTExMw==&mid=2247492730&idx=2&sn=7a7e264f6d3f9a1765482cb1b0c1cffe" target="_blank">丢弃掉那些BeanUtils工具类吧，MapStruct真香！！！</a>



