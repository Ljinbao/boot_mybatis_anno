# boot_mybatis_anno
这是一个SpringBoot整合Mybatis的注解版
后续的更新，主要在此基础上增加其他框架，定时任务调度框架、redis集成、dobbo等

1.现在SpringBoot2.1.3整合Quartz完成，出现的问题简单整理一下：
（1）SpringBoot在2.0之前与之后的配置有区别，在项目中我也做了两种配置。
（2）SpringBoot2.0之后Quartz的容器默认是Spring容器。在写Job类时，我们可以直接注入想要的对象。而在SpringBoot2.0之前，SpringBoot没有集成Quartz所以
我采用传统的依赖和配置。
（3）Quartz的配置文档中数据源的问题，没有太多的去深入。后期会做一些了解
