package com.wuxi.aop;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * AOP核心概念
 * <p>
 * 1、横切关注点
 * 对哪些方法进行拦截，拦截后怎么处理，这些关注点称之为横切关注点
 * 2、切面（aspect）
 * 类是对物体特征的抽象，切面就是对横切关注点的抽象
 * 3、连接点（joinpoint）
 * 被拦截到的点，因为Spring只支持方法类型的连接点，所以在Spring中连接点指的就是被拦截到的方法，实际上连接点还可以是字段或者构造器
 * 4、切入点（pointcut）
 * 对连接点进行拦截的定义
 * 5、通知（advice）
 * 所谓通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类
 * 6、目标对象
 * 代理的目标对象
 * 7、织入（weave）
 * 将切面应用到目标对象并导致代理对象创建的过程
 * 8、引入（introduction）
 * 在不修改代码的前提下，引入可以在运行期为类动态地添加一些方法或字段
 * <p>
 * Spring对AOP的支持
 * Spring中AOP代理由Spring的IOC容器负责生成、管理，其依赖关系也由IOC容器负责管理。因此，AOP代理可以直接使用容器中的其它bean实例作为目标，这种关系可由IOC容器的依赖注入提供。Spring创建代理的规则为：
 * 1、默认使用Java动态代理来创建AOP代理，这样就可以为任何接口实例创建代理了
 * 2、当需要代理的类不是代理接口的时候，Spring会切换为使用CGLIB代理，也可强制使用CGLIB
 * <p>
 * AOP编程其实是很简单的事情，纵观AOP编程，程序员只需要参与三个部分：
 * 1、定义普通业务组件
 * 2、定义切入点，一个切入点可能横切多个业务组件
 * 3、定义增强处理，增强处理就是在AOP框架为普通业务组件织入的处理动作
 * <p>
 * 所以进行AOP编程的关键就是定义切入点和定义增强处理，一旦定义了合适的切入点和增强处理，AOP框架将自动生成AOP代理，即：代理对象的方法=增强处理+被代理对象的方法。
 */
public class TestBeforeAdvice {

    public static void main(String[] args) {
        Waiter waiter = new WaiterImpl();
        BeforeAdvice advice = new GreetingBeforeAdvice();

        ProxyFactory pf = new ProxyFactory();

        pf.setTarget(waiter);
        //指定对接口代理(jdk)，否则对类代理(cglib)
        pf.setInterfaces(waiter.getClass().getInterfaces());
        //优化代理
        pf.setOptimize(true);
        //可设置多个增强
        pf.addAdvice(advice);

        Waiter proxy = (Waiter) pf.getProxy();
        proxy.greetTo("xixi");
        proxy.serveTo("wang");
    }
}
