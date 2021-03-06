# JAVABASE

##

> <a href="https://blog.csdn.net/qq_44377709/article/details/105993577" target="_blank">无锁、偏向锁、轻量级锁、重量级锁，完整的锁升级！</a>
> 
    -偏向锁是指一段同步代码一直被一个线程所访问，那么该线程会自动获取锁。降低获取锁的代价。
    -轻量级锁是指当锁是偏向锁的时候，被另一个线程所访问，偏向锁就会升级为轻量级锁，其他线程会通过自旋的形式尝试获取锁，不会阻塞，提高性能。 
    -重量级锁是指当锁为轻量级锁的时候，另一个线程虽然是自旋，但自旋不会一直持续下去，当自旋一定次数的时候，还没有获取到锁，就会进入阻塞，该锁膨胀为重量级锁。重量级锁会让其他申请的线程进入阻塞，性能降低。 

> <a href="http://mp.weixin.qq.com/s?__biz=MzIxNjA5MTM2MA==&mid=2652435146&idx=2&sn=fd72622e7152f0b7d0c9d91290b3aac6" target="_blank">Java7/8 中的HashMap 和 ConcurrentHashMap</a>

> <a href="http://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650139864&idx=1&sn=d78c1d113d332d98b612dc1d95913531" target="_blank">《Java开发手册》解读：大整数传输为何禁用Long类型? </a>

- 双精度分配了8个字节，总共64位，从左至右划分是1位符号、11位指数、52位有效数字。如下图所示，以0.7为例，展示了双精度浮点数的存储方式。
  ![alt text](image/微信截图_20200817140248.png "双精度浮点数的存储方式")

    - 一问：JS的Number类型能安全表达的最大整型数值是多少？为什么（注意要求更严，是安全表达）？
    - 二问：在Long取值范围内，2的指数次整数转换为JS的Number类型，不会有精度丢失，但能放心使用么？
    - 三问：我们一般都知道十进制数转二进制浮点数有可能会出现精度丢失，但精度丢失具体怎么发生的？
    - 四问：如果不幸中招，服务端正在使用Long类型作为大整数的返回，有哪些办法解决？

## 树

> <a href="https://mp.weixin.qq.com/s/yHWEl-d-Hl7A_O6N4GCAPg" target="_blank">25 张图演示红黑树 </a>

    - 二叉树
    - AVL树（平衡二叉树）
    - B树（每个节点上有多个值）
    - 红黑树（首位为黑，从任意节点到叶子节点上黑色相同，红黑相间），特点：左旋、右旋

## 多线程

> <a href="https://blog.csdn.net/qq_42175986/article/details/107980811" target="_blank">敞开心扉，一起聊聊Java多线程</a>

> <a href="http://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247492647&idx=1&sn=4d85bc69e46ba7ab67972348577b8e78" target="_blank">2w字 + 40张图带你参透并发编程！</a>

> <a href="https://blog.csdn.net/qq_35634181/article/details/103996977" target="_blank">深入理解ThreadLocal的原理及内存泄漏问题</a>

- StrongRerence为JVM内部实现。其他三类引用类型全部继承自Reference父类。
    - 强引用（StrongReference）

      强引用类型，如果JVM垃圾回收器GC Roots可达性分析结果为可达，表示引用类型仍然被引用着，这类对象始终不会被垃圾回收器回收，即使JVM发生OOM（内存溢出）也不会回收。而如果GC
      Roots（垃圾回收器的对象）的可达性分析结果为不可达，那么在GC时会被回收。
    - 软引用（SoftReference）

      软引用是用来描述一些还有用但并非必须的对象。对于软引用关联着的对象，在系统将要发生内存溢出异常之前，将会把这些对象列进回收范围进行第二次回收。如果这次回收还没有足够的内存，才会抛出内存溢出异常。
    - 弱引用（WeakReference）

      弱引用也是用来描述非必须对象的，他的强度比软引用更弱一些，被弱引用关联的对象，在垃圾回收时，如果这个对象只被弱引用关联（没有任何强引用关联他），那么这个对象就会被回收。
    - 虚引用（PhantomReference）

      虚引用与前面的几种都不一样，这种引用类型不会影响对象的生命周期，所持有的引用就跟没持有一样，随时都能被GC回收。需要注意的是，在使用虚引用时，必须和引用队列关联使用。在对象的垃圾回收过程中，如果GC发现一个对象还存在虚引用，则会把这个虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象内存被回收之前采取必要的行动防止被回收。虚引用主要用来跟踪对象被垃圾回收器回收的活动。

- 实现 Runnable 接口和继承 Thread 类哪种方式更好?
    - 从代码架构角度。（应该去解耦，两件事情：1.具体的任务即 run 方法中的内容；2.和线程生命周期相关的如创建线程、运行线程、销毁线程即 Thread 类去做的事情）
    - 新建线程的损耗的角度。（继承 Thread 类，需要新建线程、执行完之后还要销毁，实现 Runnable 接口的方式可以反复的利用同一个线程，比如线程池就是这么做的，用于线程生命周期的损耗就减少了）
    - Java 不支持多继承的角度。（对于扩展性而言）

> <a href="https://mp.weixin.qq.com/s/_OnEZ9tuDsOKBnSKmGoWKA" target="_blank">【高并发】面试官：讲讲高并发场景下如何优化加锁方式？</a>
> 通俗的说，通过synchronized加锁的对象，通过wait方法进入队列，notify进行出队，notifyall全部出队。

-

我们使用synchronized加锁时，只允许一个线程进入synchronized保护的代码块，也就是临界区。如果一个线程进入了临界区，则其他的线程会进入阻塞队列里等待，这个阻塞队列和synchronized互斥锁是一对一的关系，也就是说，一把互斥锁对应着一个独立的阻塞队列。

- 在并发编程中，如果一个线程获得了synchronized互斥锁，但是不满足继续向下执行的条件，则需要进入等待状态。此时，可以使用Java中的wait()方法来实现。当调用wait()
  方法后，当前线程就会被阻塞，并且会进入一个等待队列中进行等待，这个由于调用wait()
  方法而进入的等待队列也是互斥锁的等待队列。而且，线程在进入等待队列的同时，会释放自身获得的互斥锁，这样，其他线程就有机会获得互斥锁，进而进入临界区了。整个过程可以表示成下图所示。

> <a href="https://mp.weixin.qq.com/s/HAGYzsiWwB7DQ-LT1Q2pZg" target="_blank">阿里为什么推荐使用LongAdder，而不是volatile？</a>
> 为什么会出现上面的情况？这是因为 AtomicInteger 在高并发环境下会有多个线程去竞争一个原子变量，而始终只有一个线程能竞争成功，而其他线程会一直通过 CAS 自旋尝试获取此原子变量，因此会有一定的性能消耗；而 LongAdder 会将这个原子变量分离成一个 Cell 数组，每个线程通过 Hash 获取到自己数组，这样就减少了乐观锁的重试次数，从而在高竞争下获得优势；而在低竞争下表现的又不是很好，可能是因为自己本身机制的执行时间大于了锁竞争的自旋时间，因此在低竞争下表现性能不如 AtomicInteger。

### JUC

> <a href="https://baijiahao.baidu.com/s?id=1666548481761194849&wfr=spider&for=pc" target="_blank">LockSupport：一个很灵活的线程工具类</a>

> <a href="https://mp.weixin.qq.com/s/ydZLcf7eY3FE15uvsqfGjA" target="_blank">JUC并发编程之Semaphore信号量、CountDownLatch、CyclicBarrier栅栏、Executo...</a>

- CountDownLaunch 只使用一次，设置CountDownLatch countDownLatch = new CountDownLatch(3); 同时设置countDownLatch.await();
  线程用到3时countDownLatch.countDown(); 从await位置继续执行

> <a href="https://mp.weixin.qq.com/s/m60Tq18Vbp5ml-qAp505VQ" target="_blank">一文搞懂 CountDownLatch 用法和源码！</a>

- CyclicBarrier 多次循环使用，类似于CountDownLaunch 创建， CyclicBarrier cyclicBarrier = new CyclicBarrier(11)
  使用cyclicBarrier.await();对数量进行占用
- Semaphore 设置信号量大小Semaphore semaphore = new Semaphore(2); 多个线程进去只能最多2个获取到信号进行执行 semaphore.acquire();
  semaphore.release();
- Exchanger 基于线程的数据交换，交换进去的数据保存在一个管道中，消费的时候按照顺序获取

### 锁

> <a href="https://www.cnblogs.com/cxiaocai/p/12189848.html" target="_blank">java架构之路（多线程）synchronized详解以及锁的膨胀升级过程</a>
![alt text](image/1506597-20200113215936916-1466585772.png "锁的分类")

## JVM

> <a href="http://mp.weixin.qq.com/s?__biz=MzIxNjA5MTM2MA==&mid=2652439158&idx=2&sn=13b7bb8bb9d491775bd5d50ec895944a" target="_blank">别再说自己不会JVM了，看完这篇能和面试官扯上半小时</a>

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

> 如果光靠 sychronized 和 volatile 来保证程序执行过程中的原子性、有序性、可见性，那么代码将会变得异常繁琐。JMM 提供了 Happen-Before 规则来约束数据之间是否存在竞争，线程环境是否安全。具体如下：

- 顺序原则

一个线程内保证语义的串行性：a = 1; b = a + 1;

- volatile 规则

> <a href="https://mp.weixin.qq.com/s/tuzfPEdPOBKeMXGniDus6A" target="_blank">26张图带你彻底搞懂volatile关键字</a>

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

## trim移除的空白字符指的是指ASCII值小于或等于32的任何字符(' U+0020 ')：

![Alt text](image/微信图片_20201008165746.jpg "双精度浮点数的存储方式")

> <a href="https://blog.csdn.net/wslwno1/article/details/109211366" target="_blank">String长度有限制吗？是多少？</a>

> 答：首先字符串的内容是由一个字符数组 char[] 来存储的，由于数组的长度及索引是整数，且String类中返回字符串长度的方法length() 的返回值也是int ，所以通过查看java源码中的类Integer我们可以看到Integer的最大范围是2^31 -1,由于数组是从0开始的，所以数组的最大长度可以使【0~2^31】通过计算是大概4GB。 但是通过翻阅java虚拟机手册对class文件格式的定义以及常量池中对String类型的结构体定义我们可以知道对于索引定义了u2，就是无符号占2个字节，2个字节可以表示的最大范围是2^16 -1 = 65535。 其实是65535，但是由于JVM需要1个字节表示结束指令，所以这个范围就为65534了。超出这个范围在编译时期是会报错的，但是运行时拼接或者赋值的话范围是在整形的最大范围。



----

# SPRING

![alt text](image/b8324b093108e320693eb995b7e11a7f.jpg "dubbo-extension")

## spring基础

> <a href="http://mp.weixin.qq.com/s?__biz=MzUxNDA1NDI3OA==&mid=2247485475&idx=1&sn=bf60a8064835a71a512d6fe993d80dbc" target="_blank">Spring基础！</a>

        Spring Core：核心类库，提供IOC服务；
        Spring Context：提供框架式的Bean访问方式，以及企业级功能（JNDI、定时任务等）；
        Spring AOP：AOP服务；
        Spring DAO：对JDBC的抽象，简化了数据访问异常的处理；
        Spring ORM：对现有的ORM框架的支持；
        Spring Web：提供了基本的面向Web的综合特性，例如多方文件上传；
        Spring MVC：提供面向Web应用的Model-View-Controller实现。

- Spring的AOP理解：
    - （1）AspectJ是静态代理的增强，所谓静态代理，就是AOP框架会在编译阶段生成AOP代理类，因此也称为编译时增强，他会在编译阶段将AspectJ(切面)织入到Java字节码中，运行的时候就是增强之后的AOP对象。

    - （2）Spring
      AOP使用的动态代理，所谓的动态代理就是说AOP框架不会去修改字节码，而是每次运行时在内存中临时为方法生成一个AOP对象，这个AOP对象包含了目标对象的全部方法，并且在特定的切点做了增强处理，并回调原对象的方法。

          Spring AOP中的动态代理主要有两种方式，JDK动态代理和CGLIB动态代理：
          ①JDK动态代理只提供接口的代理，不支持类的代理。核心InvocationHandler接口和Proxy类，InvocationHandler 通过invoke()方法反射来调用目标类中的代码，动态地将横切逻辑和业务编织在一起；接着，Proxy利用 InvocationHandler动态创建一个符合某一接口的的实例,  生成目标类的代理对象。
          ②如果代理类没有实现 InvocationHandler 接口，那么Spring AOP会选择使用CGLIB来动态代理目标类。CGLIB（Code Generation Library），是一个代码生成的类库，可以在运行时动态的生成指定类的一个子类对象，并覆盖其中特定方法并添加增强代码，从而实现AOP。CGLIB是通过继承的方式做的动态代理，因此如果某个类被标记为final，那么它是无法使用CGLIB做动态代理的。
    - （3）静态代理与动态代理区别在于生成AOP代理对象的时机不同，相对来说AspectJ的静态代理方式具有更好的性能，但是AspectJ需要特定的编译器进行处理，而Spring AOP则无需特定的编译器处理。

> <a href="https://mp.weixin.qq.com/s/VUQ4HOqoZ78l0V4u5MTvSA" target="_blank">写了个牛逼的日志切面，甩锅更方便了！</a>

- 4、Spring的IoC理解：
- Spring的IOC有三种注入方式 ：构造器注入、setter方法注入、根据注解注入。

> <a href="https://mp.weixin.qq.com/s/jttHRgG0ZE2P9h_IWTtvZw" target="_blank">面试必问：Spring 循环依赖的三种方式 ！</a>



## spring-boot

> <a href="https://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247491617&idx=1&sn=160680769e5e0a8c8200d252527f8bdc" target="_blank">最棒 Spring Boot 干货总结 ！</a>
>
> <a href="https://mp.weixin.qq.com/s/QI794IRoITy_q5JgkV1Jrw" target="_blank">SpringBoot 处理异常的几种常见姿势</a>
>
> <a href="https://mp.weixin.qq.com/s/61x28xkeB8uyYxtBA0_QsQ" target="_blank">项目实践：SpringBoot三招组合拳，手把手教你打出优雅的后端接口</a>

IOC容器、JavaConfig、事件监听、SpringFactoriesLoader详解

- 一、抛砖引玉：探索Spring IoC容器
- 二、夯实基础：JavaConfig与常见Annotation
- 三、削铁如泥：SpringFactoriesLoader详解
- 四、另一件武器：Spring容器的事件监听机制
- 五、出神入化：揭秘自动配置原理
- 六、启动引导：Spring Boot应用启动的秘密

> <a href="http://mp.weixin.qq.com/s?__biz=Mzg3MjA4MTExMw==&mid=2247493155&idx=2&sn=0f94719e6ed2dc88e15afbc8001344f2" target="_blank">Spring Cloud Feign 自定义配置(重试、拦截与错误码处理) 实践</a>
>
> <a href="https://blog.csdn.net/qq_29519041/article/details/103654564" target="_blank">SpringCloud Zuul 网关搭建及配置</a>
>
> <a href="https://www.cnblogs.com/cjsblog/p/9712457.html" target="_blank">SpringBoot+MyBatis+MySQL读写分离</a>

![alt text](image/20200809103713108.png "cloud客户端调用")

----

# RPC

> <a href="https://blog.csdn.net/mindfloating/article/details/39473807" target="_blank">深入浅出 RPC - 浅出篇</a>

> <a href="https://blog.csdn.net/mindfloating/article/details/39474123" target="_blank">深入浅出 RPC - 深入篇</a>

![alt text](image/dubbo-extension.jpg "dubbo-extension")
![alt text](image/dubboFrameWork.png "dubboFrameWork")
> <a href="http://mp.weixin.qq.com/s?__biz=MzUxNDA1NDI3OA==&mid=2247485488&idx=1&sn=1c47b24be47c0119689f9150255ff0a4" target="_blank">基于 Netty 手写 RPC</a>



----

# CACHE

## memcache

> <a href="http://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651962373&idx=1&sn=7bb5925b94ea1240abcf89e2e5f89916" target="_blank">memcache内核，一文搞定！面试再也不怕了！！！（值得收藏）</a>

- （1）mc的核心职能是KV内存管理，value存储最大为1M，它不支持复杂数据结构（哈希、列表、集合、有序集合等）；
- （2）mc不支持持久化；
- （3）mc支持key过期；
- （4）mc持续运行很少会出现内存碎片，速度不会随着服务运行时间降低；
- （5）mc使用非阻塞IO复用网络模型，使用监听线程/工作线程的多线程模型；

## redis

### Redis的内存淘汰策略问题

> <a href="https://mp.weixin.qq.com/s?__biz=Mzg3MjA4MTExMw==&mid=2247493297&idx=1&sn=5ef9b81682e26381dfc0a4fae84782b6" target="_blank">阿里巴巴官方最新Redis开发规范！</a>

- 一、键值设计
    - 1、key名设计
    - 2、value设计
- 二、命令使用
- 三、客户端使用
- 四、相关工具
- 五、删除bigkey

> <a href="http://mp.weixin.qq.com/s?__biz=Mzg3MjA4MTExMw==&mid=2247494452&idx=1&sn=cb01a4bedd639f1b0679d4b18f60948c" target="_blank">Redis延时队列，这次彻底给你整明白了</a>

> <a href="https://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247491843&idx=2&sn=816871265811b80b52d2073ec604317b" target="_blank">数据库系统设计概述</a>

#### 内存淘汰

        chunk：它是将内存分配给用户使用的最小单元。
        item：用户要存储的数据，包含key和value，最终都存储在chunk里。
        slab：它会管理一个固定chunk size的若干个chunk，而mc的内存管理，由若干个slab组成。

- noeviction(默认策略)：对于写请求不再提供服务，直接返回错误（DEL请求和部分特殊请求除外）
- allkeys-lru：从所有key中使用LRU算法进行淘汰
- volatile-lru：从设置了过期时间的key中使用LRU算法进行淘汰
- allkeys-random：从所有key中随机淘汰数据
- volatile-random：从设置了过期时间的key中随机淘汰
- volatile-ttl：在设置了过期时间的key中，根据key的过期时间进行淘汰，越早过期的越优先被淘汰

#### LRU在Redis中的实现

- 1.近似LRU算法 Redis使用的是近似LRU算法，它跟常规的LRU算法还不太一样。近似LRU算法通过随机采样法淘汰数据，每次随机出5（默认）个key，从里面淘汰掉最近最少使用的key。
  可以通过maxmemory-samples参数修改采样数量：例：maxmemory-samples 10 maxmenory-samples配置的越大，淘汰的结果越接近于严格的LRU算法
  Redis为了实现近似LRU算法，给每个key增加了一个额外增加了一个24bit的字段，用来存储该key最后一次被访问的时间。
- 2.Redis3.0对近似LRU的优化
  Redis3.0对近似LRU算法进行了一些优化。新算法会维护一个候选池（大小为16），池中的数据根据访问时间进行排序，第一次随机选取的key都会放入池中，随后每次随机选取的key只有在访问时间小于池中最小的时间才会放入池中，直到候选池被放满。当放满后，如果有新的key需要放入，则将池中最后访问时间最大（最近被访问）的移除。
  当需要淘汰的时候，则直接从池中选取最近访问时间最小（最久没被访问）的key淘汰掉就行。
- 3.LRU算法的对比
  我们可以通过一个实验对比各LRU算法的准确率，先往Redis里面添加一定数量的数据n，使Redis可用内存用完，再往Redis里面添加n/2的新数据，这个时候就需要淘汰掉一部分的数据，如果按照严格的LRU算法，应该淘汰掉的是最先加入的n/2的数据。生成如下各LRU算法的对比图

> <a href="http://mp.weixin.qq.com/s?__biz=MzUxNDA1NDI3OA==&mid=2247487799&idx=1&sn=be29031f4021c4887b8e766a18f9ffd1" target="_blank">这5个常问的Redis面试题</a>

- redis 集群模式的工作原理能说一下么？在集群模式下， redis 的 key 是如何寻址的？分布式寻址都有哪些算法？了解一致性 hash 算法吗？
- 了解什么是 redis 的雪崩、穿透和击穿？redis 崩溃之后会 怎么样？系统该如何应对这种情况？如何处理 redis 的穿透？
- 如何保证缓存与数据库的双写一致性？
- redis 的并发竞争问题是什么？如何解决这个问题？了解redis 事务的 CAS 方案吗？
- redis生产环境中的 redis 是怎么部署的？

----

# DB

> <a href="https://www.cnblogs.com/WeaRang/p/13417772.html" target="_blank">数据库系统设计概述</a>

## mysql

> <a href="https://mp.weixin.qq.com/s/weXkuOjbGQpebdnxuxhXPg" target="_blank">47 张图带你 MySQL 进阶</a>
> <a href="https://mp.weixin.qq.com/s/yeIDU2m5EnC3AJNo-bKalQ" target="_blank">炸裂！MySQL 82 张图带你飞！</a>

### 主从

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

### 事务

#### 事务的四大特性（ACID）

- 原子性（Atomicity，或称不可分割性）

> 「一个事务必须被视为一个不可分割的最小工作单元，整个事务中所有的操作要么全部提交成功，要么全部失败回滚，对于一个事务来说，不可能只执行其中的一部分操作，这就是事务的原子性」

- 一致性（Consistency）

> 「数据库总是从一个一致性的状态转换到另外一个一致性的状态，在事务开始之前和之后，数据库的完整性约束没有被破坏。在前面的例子中，事务结束前后A、B账户总额始终保持不变」

- 隔离性（Isolation）

> 「隔离性是指，事务内部的操作与其他事务是隔离的，并发执行的各个事务之间不能互相干扰。严格的隔离性，对应了事务隔离级别中的Serializable (可串行化)，但实际应用中出于性能方面的考虑很少会使用可串行化。」

- 持久性（Durability）

> 「持久性是指事务一旦提交，它对数据库的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。」

#### 事务的隔离级别

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

- 注意：如果异常被try｛｝catch｛｝了，事务就不回滚了，如果想让事务回滚必须再往外抛try｛｝catch｛throw Exception｝。

> <a href="http://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247491906&idx=2&sn=a54e6fbde09a7a64397409f7f9f945ad" target="_blank">走进spring事务</a>
![alt text](image/MVCC.jpg "MVCC")

> <a href="https://mp.weixin.qq.com/s/Fb9sHAVeVQp-4rWjx3oOCg" target="_blank">事务注解 @Transactional 失效的3种场景及解决办法</a>
- 第一种 Transactional注解标注方法修饰符为非public时，@Transactional注解将会不起作用

- 在类内部调用调用类内部@Transactional标注的方法。这种情况下也会导致事务不开启。

- 事务方法内部捕捉了异常，没有抛出新的异常，导致事务操作不会进行回滚。

### 索引

> <a href="http://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247492581&idx=2&sn=3c162c7fabb046190ee0f23ca391e135" target="_blank">微信大牛教你深入了解数据库索引</a>

- 线性索引
    - 稠密索引

            索引项有序就意味着可以直接对索引表进行像二分查找、插值查找、斐波那契查找等高效率的查找操作，大大减少查询的时间复杂度。
    - 分块索引

            第一个是块间有序，例如要求第二块所有记录的索引关键字均大于第一块中所有的记录关键字，第三块要大于第二块的。
            第二个是块内无序，块内的记录可以瞎排虽然可以实现块内有序，但是这就造成了和稠密索引一样的问题。
- 树形索引
    - 二叉树、平衡二叉树和红黑树索引

- 多级索引【B-/B+树索引】

### binlog

> <a href="https://developer.aliyun.com/article/770659?utm_content=g_1000172574" target="_blank">详细讲解！Canal+Kafka实现MySQL与Redis数据同步！</a>

### 日志

#### 日志分类

> <a href="https://www.cnblogs.com/wy123/p/8365234.html" target="_blank">MySQL到底有几种日志类型需要我们记住？</a>

- 重做日志（redo log）
- 回滚日志（undo log）
- 二进制日志（binlog）
- 错误日志（errorlog）
- 慢查询日志（slow query log）
- 一般查询日志（general log）
- 中继日志（relay log）

> <a href="https://blog.csdn.net/mshxuyi/article/details/100652769" target="_blank">MySQL 中继日志</a>

----

# ES

> <a href="https://www.jianshu.com/p/28fb017be7a7" target="_blank">Elasticsearch详解</a>

> <a href="https://www.jianshu.com/p/8e22558dc8a1" target="_blank">Elasticsearch详解-续</a>

> <a href="https://mp.weixin.qq.com/s/CxIb7SARtoBQX7Yml5Dpxg" target="_blank">ElasticSearch 面试 4 连问，你顶得住么？</a>


----

# MQ

## MQ，究竟如何保证消息幂等？

- 首先，上半场幂等。 MQ-client生成inner-msg-id，保证上半场幂等。 这个ID全局唯一，业务无关，由MQ保证。

- 然后，下半场幂等。 业务发送方带入biz-id，业务接收方去重保证幂等。 这个ID对单业务唯一，业务相关，对MQ透明。

## rabbitMq

### RabbitMQ 七战 Kafka，差异立现

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

### RabbitMQ之交换机的四种类型和属性

> <a href="http://mp.weixin.qq.com/s?__biz=Mzg3MjA4MTExMw==&mid=2247492996&idx=2&sn=55db10f5df239d07a69672b29f5b68bd" target="_blank">springboot + rabbitmq 用了消息确认机制，感觉掉坑里了</a>

#### 消息发送确认：

- 1、 ConfirmCallback确认模式
- 2、 ReturnCallback 退回模式

#### 消息接收确认：

- 1、basicAck
- 2、basicNack
- 3、basicReject

> <a href="https://blog.csdn.net/hry2015/article/details/79118804" target="_blank">RabbitMQ之交换机的四种类型和属性</a>

#### 交换机主要包括如下4种类型：

- Direct exchange（直连交换机）
- Fanout exchange（扇型交换机）
- Topic exchange（主题交换机）
- Headers exchange（头交换机）

#### 订阅有两种类型：

- 临时（ephemeral）订阅，这种订阅只有在消费者启动并且运行的时候才存在。一旦消费者退出，相应的订阅以及尚未处理的消息就会丢失。
- 持久（durable）订阅，这种订阅会一直存在，除非主动去删除。消费者退出后，消息系统会继续维护该订阅，并且后续消息可以被继续处理。

> <a href="http://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650141257&idx=3&sn=0539c85d6fd5c392639610457f0d170c" target="_blank">给你1分钟，回答下RabbitMQ如何保证消息不丢？</a>

- 如何保证消息的可靠性？

        发送方采取发送者确认模式
        MQ进行队列及消息的持久化
        消费者消费成功后手动确认消息

### 什么是死信队列

> <a href="https://mp.weixin.qq.com/s/_AXWy5XdYf_6OBf2VhvJGg" target="_blank">RabbitMQ之什么是死信队列</a>

- 当一条消息在队列中出现以下三种情况的时候，该消息就会变成一条死信。

        消息被拒绝(basic.reject / basic.nack)，并且requeue = false
        消息TTL过期
        队列达到最大长度
- 当消息在一个队列中变成一个死信之后，如果配置了死信队列，它将被重新publish到死信交换机，死信交换机将死信投递到一个队列上，这个队列就是死信队列。

> <a href="https://mp.weixin.qq.com/s/Hq-XwI8JwYzAkbT4bG8Hhg" target="_blank">系统通知，居然用拉取？</a>

- 广义系统通知，究竟是推送还是拉取呢？不同业务，不同需求，实现方式不同。
- 系统对1的通知：
    - （1）实时性要求高，可以推送；
    - （2）实时性要求低，可以拉取；
- 系统对N的通知：
    - （1）登录弹窗新闻，拉取更佳，可以用一个last_msg_date来避免大量数据的存储；
    - （2）批量弹窗广告，常见的方法是推送，需要注意限速，也可以拉取，以实现请求的均匀分散；

----

# BIGDATA

> <a href="https://blog.csdn.net/zhaodedong/article/details/105963444" target="_blank">实时数据架构&实时数据仓库，你到底了解多少？</a>

## kafka

> <a href="http://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650138701&idx=3&sn=181b58773b36a50ec313e00b8624c07a" target="_blank">Kafka 为什么能那么快的 6 个原因</a>
> <a href="https://mp.weixin.qq.com/s/9i4lDtWqOrzJbzN1I1yAwg" target="_blank">Kafka性能篇：为何Kafka这么"快"？</a>

- 1 利用 Partition 实现并行处理
- 2 顺序写磁盘
- 3 充分利用 Page Cache
- 4 零拷贝技术

 <a href="https://mp.weixin.qq.com/s/lYDfWMxZY4uLO99G2unK8Q" target="_blank">什么是mmap？</a>


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

### 消息的【确认机制】

> <a href="https://mp.weixin.qq.com/s/5HLRalpmvUdHxEtniZ5gLA" target="_blank">面试官问：Kafka 会不会丢消息？怎么处理的? </a>

- 1 broker **
- 2 producer
- 3 consumer

       默认值是0。在面试中常被问到。
       producer有个ack参数，有三个值，分别代表：
       1）不在乎是否写入成功；         【0】
       2）写入leader成功；           【1】
       3）写入leader和所有副本都成功； 【-1】
       要求非常可靠的话可以牺牲性能设置成最后一种。为了保证消息不丢失，至少要设置为1，也就是说至少保证leader将消息保存成功。
       
       设置发送数据是否需要服务端的反馈,有三个值0,1,-1，分别代表3种状态：
       0: producer不会等待broker发送ack。生产者只要把消息发送给broker之后，就认为发送成功了，这是第1种情况；
       1: 当leader接收到消息之后发送ack。生产者把消息发送到broker之后，并且消息被写入到本地文件，才认为发送成功，这是第2种情况；
       -1: 当所有的follower都同步消息成功后发送ack。不仅是主的分区将消息保存成功了，而且其所有的分区的副本数也都同步好了，才会被认为发动成功，这是第3种情况。

> <a href="https://blog.csdn.net/lukabruce/article/details/101012815" target="_blank">关于kafka中ISR、AR、HW、LEO、LSO、LW的含义详解</a>

## spark

## storm

## flink

## clickhouse

> <a href="http://mp.weixin.qq.com/s?__biz=MzI3MjUxNzkxMw==&mid=2247485984&idx=2&sn=8fabf1a3f7eb6b59062f28e343d2f65c" target="_blank">ClickHouse到底是什么？凭啥这么牛逼！</a>




> <a href="https://blog.csdn.net/baichoufei90/article/details/84328666" target="_blank">MPP架构</a>

----

# 算法

## 一致性hash

> <a href="https://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247492221&idx=2&sn=c4ed5cb2d72518bcd89b80f313b4524e" target="_blank">图解一致性哈希算法，全网（小区局域网）最通俗易懂</a>

> <a href="https://mp.weixin.qq.com/s?__biz=MzI2NjA3NTc4Ng==&mid=2652082269&idx=2&sn=51f9e1ad67e4a27a2d56ed29d2166100" target="_blank">漫画：什么是 “哈夫曼树” ？</a>

> <a href="https://mp.weixin.qq.com/s/t8HxUTxzyHNNBJ22rnCh-g" target="_blank">字符串匹配算法详解</a>

- BF(brute force)【从左到右，使用被比较字符串从左到右逐一匹配】
- BM()【从左到右，使用被比较字符串从右到左，匹配上单词后会以单子右移】
- CMP()【从左到右，使用被比较字符串从左到右匹配，匹配后会缓存】

## leetcode
- ![img.png](image/img.png)

# IO

> <a href="http://mp.weixin.qq.com/s?__biz=MzIzOTU0NTQ0MA==&mid=2247498435&idx=1&sn=b8643bbc80110ca8971c3747b8cd7f99" target="_blank">Java 开发必备！ I/O与Netty原理精讲</a>

> <a href="http://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650139093&idx=3&sn=0d3c602cf4bc3119adc410af21a1a921" target="_blank">凉了！张三同学没答好「进程间通信」，被面试官挂了</a>

    由于每个进程的用户空间都是独立的，不能相互访问，这时就需要借助内核空间来实现进程间通信，原因很简单，每个进程都是共享一个内核空间。
    
    Linux 内核提供了不少进程间通信的方式，其中最简单的方式就是管道，管道分为「匿名管道」和「命名管道」。
    
    匿名管道顾名思义，它没有名字标识，匿名管道是特殊文件只存在于内存，没有存在于文件系统中，shell 命令中的「|」竖线就是匿名管道，通信的数据是无格式的流并且大小受限，通信的方式是单向的，数据只能在一个方向上流动，如果要双向通信，需要创建两个管道，再来匿名管道是只能用于存在父子关系的进程间通信，匿名管道的生命周期随着进程创建而建立，随着进程终止而消失。
    
    命名管道突破了匿名管道只能在亲缘关系进程间的通信限制，因为使用命名管道的前提，需要在文件系统创建一个类型为 p 的设备文件，那么毫无关系的进程就可以通过这个设备文件进行通信。另外，不管是匿名管道还是命名管道，进程写入的数据都是缓存在内核中，另一个进程读取数据时候自然也是从内核中获取，同时通信数据都遵循先进先出原则，不支持 lseek 之类的文件定位操作。
    
    消息队列克服了管道通信的数据是无格式的字节流的问题，消息队列实际上是保存在内核的「消息链表」，消息队列的消息体是可以用户自定义的数据类型，发送数据时，会被分成一个一个独立的消息体，当然接收数据时，也要与发送方发送的消息体的数据类型保持一致，这样才能保证读取的数据是正确的。消息队列通信的速度不是最及时的，毕竟每次数据的写入和读取都需要经过用户态与内核态之间的拷贝过程。
    
    共享内存可以解决消息队列通信中用户态与内核态之间数据拷贝过程带来的开销，它直接分配一个共享空间，每个进程都可以直接访问，就像访问进程自己的空间一样快捷方便，不需要陷入内核态或者系统调用，大大提高了通信的速度，享有最快的进程间通信方式之名。但是便捷高效的共享内存通信，带来新的问题，多进程竞争同个共享资源会造成数据的错乱。
    
    那么，就需要信号量来保护共享资源，以确保任何时刻只能有一个进程访问共享资源，这种方式就是互斥访问。信号量不仅可以实现访问的互斥性，还可以实现进程间的同步，信号量其实是一个计数器，表示的是资源个数，其值可以通过两个原子操作来控制，分别是 P 操作和 V 操作。
    
    与信号量名字很相似的叫信号，它俩名字虽然相似，但功能一点儿都不一样。信号是进程间通信机制中唯一的异步通信机制，信号可以在应用进程和内核之间直接交互，内核也可以利用信号来通知用户空间的进程发生了哪些系统事件，信号事件的来源主要有硬件来源（如键盘 Cltr+C ）和软件来源（如 kill 命令），一旦有信号发生，进程有三种方式响应信号 1. 执行默认操作、2. 捕捉信号、3. 忽略信号。有两个信号是应用进程无法捕捉和忽略的，即 SIGKILL 和 SEGSTOP，这是为了方便我们能在任何时候结束或停止某个进程。
    
    前面说到的通信机制，都是工作于同一台主机，如果要与不同主机的进程间通信，那么就需要 Socket 通信了。Socket 实际上不仅用于不同的主机进程间通信，还可以用于本地主机进程间通信，可根据创建 Socket 的类型不同，分为三种常见的通信方式，一个是基于 TCP 协议的通信方式，一个是基于 UDP 协议的通信方式，一个是本地进程间通信方式。
    
    以上，就是进程间通信的主要机制了。你可能会问了，那线程通信间的方式呢？
    
    同个进程下的线程之间都是共享进程的资源，只要是共享变量都可以做到线程间通信，比如全局变量，所以对于线程间关注的不是通信方式，而是关注多线程竞争共享资源的问题，信号量也同样可以在线程间实现互斥与同步：
    互斥的方式，可保证任意时刻只有一个线程访问共享资源；
    同步的方式，可保证线程 A 应在线程 B 之前执行；

----

#### 版本管理 git/svn

#### Mysql  sql执行计划

#### Java基础 异常/error，多线程安全

#### Spring熟悉程度 注解/spring事务传播机制

#### Redis熟悉程度 数据类型，分布式锁。跳表原理等（面试效果好的，可以继续问一下）

#### Mq熟悉程度 可选： 为什么使用mq，是否使用过。是否有顺序？

#### Es熟悉程度 可选： 使用方法，

#### RPC调用熟悉程度 服务注册、发现原理。调用时是否通过注册中心。Rpc调用过程描述（面试效果好的，可以继续问一下）

#### 整体感觉

## other

- <a href="https://www.jianshu.com/p/191d1e21f7ed/" target="_blank">Markdown基本语法</a>
- <a href="https://mp.weixin.qq.com/s?__biz=MzIxNjA5MTM2MA==&mid=2652438634&idx=1&sn=38cfe4a47c71ff16c7ec4cb6c873ea7f" target="_blank">
  10个 解放双手的 IDEA 插件，少些冤枉代码</a>
- <a href="https://www.cnblogs.com/zuge/p/7397255.html" target="_blank">再见，Navicat！同事安利的这个IDEA的兄弟，真香！（全图注解） DataGrip</a>
- <a href="http://mp.weixin.qq.com/s?__biz=Mzg3MjA4MTExMw==&mid=2247492730&idx=2&sn=7a7e264f6d3f9a1765482cb1b0c1cffe" target="_blank">
  丢弃掉那些BeanUtils工具类吧，MapStruct真香！！！</a>
- <a href="http://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247492653&idx=2&sn=b029eb7d33708abcca0640ab741b02d7" target="_blank">
  40张图揭秘，「键入网址发生了什么」 ，http,tcp</a>
- <a href="http://mp.weixin.qq.com/s?__biz=MzU2NDg0OTgyMA==&mid=2247493143&idx=1&sn=b4be69be1d29920f1b19b03b47433e94" target="_blank">
  64 张图带你 Maven 实战通关</a>
- <a href="http://mp.weixin.qq.com/s?__biz=MzIzOTU0NTQ0MA==&mid=2247499652&idx=1&sn=f8ce3697f4c3ea1573fabe456535885d" target="_blank">
  源码解析：Git的第一个提交是什么样的？</a>
- <a href="http://mp.weixin.qq.com/s?__biz=MzIzOTU0NTQ0MA==&mid=2247499892&idx=1&sn=0513bd832d82f213ffd01f3a071566e6" target="_blank">
  如何写好单元测试？</a>
- <a href="https://blog.csdn.net/lc130628/article/details/82114220" target="_blank">大B和小b的区别及关联</a>
- <a href="https://mp.weixin.qq.com/s/FbDT7bZM0gqvL_80TIoS-g" target="_blank">这些最常用的Linux命令，每一条都应该会，没有借口！</a>
- <a href="https://albenw.github.io/posts/854fc091/" target="_blank">Java日志体系总结</a>
- <a href="https://mp.weixin.qq.com/s/R6G0ph6qmyQUucgw7-irBw" target="_blank">《我想进大厂》之Zookeeper夺命连环9问</a>
- <a href="https://zhuanlan.zhihu.com/p/145551967" target="_blank">我终于看懂了HBase，太不容易了</a>;
    - HBase是一个NoSQL数据库，一般我们用它来存储海量的数据（因为它基于HDFS分布式文件系统上构建的）
    - HBase的一行记录由一个RowKey和一个或多个的列以及它的值所组成。先有列族后有列，列可以随意添加。
    - HBase的增删改记录都有「版本」，默认以时间戳的方式实现。
    - RowKey的设计如果没有特殊的业务性，最好设计为散列的，这样避免热点数据分布在同一个HRegionServer中。
    - HBase的读写都经过Zookeeper去拉取meta数据，定位到对应的HRegion，然后找到HRegionServer
- <a href="https://mp.weixin.qq.com/s/WH3C63-5dvwQ3WsA8zcjAQ" target="_blank">BIO、NIO、AIO、多路复用IO的区别(图解)</a>
    - ①同步阻塞IO（Blocking IO）：即传统的IO模型。
    - ②同步非阻塞IO（Non-blocking IO）：默认创建的socket都是阻塞的，非阻塞IO要求socket被设置为NONBLOCK。注意这里所说的NIO并非Java的NIO（New IO）库。
    - ③多路复用IO（IO
      Multiplexing）：即经典的Reactor设计模式，有时也称为异步阻塞IO，Java中的Selector和Linux中的epoll都是这种模型（Redis单线程为什么速度还那么快，就是因为用了多路复用IO和缓存操作的原因）
    - ④异步IO（Asynchronous IO）：即经典的Proactor设计模式，也称为异步非阻塞IO。
- <a href="https://mp.weixin.qq.com/s/RvU03iD1ZMdZ8356kbdL0g" target="_blank">一次线上JVM Young GC调优，搞懂了这么多东西！</a>
    - 增加s0,s1大小，会同步增加ygc的时间，比如原来为500ms，s0为500m，扩大s0为2倍为1G后，ygc的时间也同时增加到1000ms。
- <a href="https://books.halfrost.com/leetcode/" target="_blank">leetCode Cookbook</a>

#### 设计原则（S.O.L.I.D）：

- 1、SRP 单一职责原则
- 2、OCP 开闭原则
- 3、LSP 里式替换原则
- 4、ISP 接口隔离原则
- 5、DIP 依赖倒置原则

#### DRY 原则、KISS 原则、YAGNI 原则、LOD 法则:

- DRY：不要干重复的事儿。
- KISS：不要干复杂的事儿，思从深而行从简。
- YAGNI：不要干不需要的事儿，尺度把握尤为重要，超越尺度则会有过度设计之嫌。
- LOD：最小依赖。

#### 覆盖率准则

- 函式覆盖率（Function coverage）：有呼叫到程式中的每一个函式（或副程式）吗？
- 指令覆盖率（Statement coverage）：若用控制流图（英语：control flow graph）表示程式，有执行到控制流图中的每一个节点吗？
- 判断覆盖率（Decision coverage）：（和分支覆盖率不同）若用控制流图表示程式，有执行到控制流图中的每一个边吗？例如控制结构中所有IF指令都有执行到逻辑运算式成立及不成立的情形吗？
- 条件覆盖率（Condition coverage）：也称为谓词覆盖（predicate
  coverage），每一个逻辑运算式中的每一个条件（无法再分解的逻辑运算式）是否都有执行到成立及不成立的情形吗？条件覆盖率成立不表示判断覆盖率一定成立。
- 条件/判断覆盖率（Condition/decision coverage）：需同时满足判断覆盖率和条件覆盖率。



