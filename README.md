 * 1、ProxyFactoryBean
 * 	> target 目标类
 * 	> proxyInterfaces 代理接口
 * 	> interceptorNames 拦截器（通知器Advise）
 * 	针对目标对象创建代理对象，将对目标对象的方法调用转到相应的代理对象方法调用
 * 	可以在代理对 象方法调用前后执行与之匹配的各个通知器中定义好的方法
 *
 * 2、三种创建代理对象的方法  ==> AopProxy
 * 	> JDK动态代理 > InvocationHandler + Proxy = JdkDynamicAopProxy
 * 	> CGLIB > CglibAopProxy
 * 	> ObjenesisCglibAopProxy extends CglibAopProxy [Spring 4.0新增，默认]
 
 *
 * 3、JDK动态代理 JdkDynamicAopProxy , 基于接口 interface 
 *  > interface ==> java.lang.reflect.InvocationHandler
 *  > Class ==> Proxy#newProxyInstance
 *  >> JDK动态代理基于接口的原因是：生成的代理类是 extends Proxy 类的，单继承原则决定基于接口
 * 
 * 4、CGLIB 代理  CglibAopProxy、ObjenesisCglibAopProxy
 *  > 生成代理类的子类
 *
 * 5、aop 的应用场景
 *  > 事务
 *  > 权限
 *  > 日志
 
 * 6、事务
 *  > PlatformTransactionManager
 *  
 
 * 7、Xml 配置实现
 *  > ProxyFactoryBean
 *      > target
 *      > interceptorNames
 *      > proxyInterfaces
 *
 * 8、 ProxyFactoryBean(FactoryBean) 与 普通Bean的区别
 *  > FactoryBean 也是一个Bean，也会存放在 bean 工厂中，但是这个 bean 会动态生成代理对象，通过其 getObject 方法
 *  > 普通的 bean 只是一个 bean
 *  
 
 * 9、 BeanFactory是Spring IOC的工厂，它里面管理着Spring锁创建出来的各种bean对象，当我们在配置文件（注解）中声明了某个bean的id后 ，通过这个id
 *     就可以获取到与该id所对应的class对象的实例（新建或从缓存中获取）
 *     FactoryBean本质上也是一个bean，它通其他bean一样，也是由BeanFactory所管理和维护的，实例也会缓存到Spring的工厂中（单例），它与普通bean的
 *     区别在于，创建完一个FactoryBean后，会判断所创建的bean是否为FactoryBean ,如果不是，直接返回给客户端；如果是，则会进一步处理，根据所配置的
 *     target、advisor、interfaces等信息，在运行期间动态构建出一个类，并生成一个实例返回给客户端，在其 getObject 方法中使用反射进行代理对象的创建
 *
 *
 * 10、关于Spring AOP 代理的生成过程：
 *     * 通过ProxyFactoryBean（FactoryBean接口的实现）来去配置相应的代理对象的信息 
 *     * 在获取ProxyFactoryBean实例时，本质上并表示获取到ProxyFactoryBean的对象，而是获取到由ProxyFactoryBean所返回的那个对象实例（getObject方法）
 *     * 在整个ProxyFactoryBean实例的构建与缓存的过程中，其流程与普通bean的对象完全一致
 *     * 差别在于，当创建完成ProxyFactoryBean对象后，Spring会判断当前所创建的对象是否是一个FactoryBean实例
 *     * 如果不是，那么Spring就直接将其返回
 *     * 如果是，Spring则会进入到一个新的流程分支中
 *     * Spring会根据我们在配置信息中所指定的各种元素，如目标对象是否实现了接口以及Advisor等信息，使用动态代理或是CGLIB等方式来为目标对象创建相应的代理对象
 *     * 当相应的代理对象创建完毕后，Spring就会通过ProxyFactoryBean的getObject方法将所创建的代理对象返回
 *     * 对象返回到调用端，它本质上是一个代理对象，可以代理对目标对象的访问与调用，这个代理对象对用户来说，就好像是一个目标对象一样
 *     * 客户在使用代理对象时，可以正常调用目标对象的方法，同时在执行过程中，会根据我们在配置文件中所配置的信息来在调用前后执行额外的附加逻辑
 *
 *
 * 11、查看生成的代理类信息(JVM参数)
 *  >JDK动态代理： -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true 或者 System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true"); 
 *  >CGLIB代理： System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");
 *