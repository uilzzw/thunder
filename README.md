# thunder
  thunder已有功能:
  * [x]添加了实体参数接收对象
  * [x]添加了restful风格的资源路径
  * [x]优化了一些细节。
  * [x]增加了activerecord基础
  * [x]优化了activerecord
  * [x]添加了 ioc 控制翻转
  * [x]添加了 路由注解
  
  thunder 最新更新:
  *[x] 添加了类似于aspectj 的aop 功能
  *[x] 优化了aop功能


  ## 表单参数对象获取

  ``` html

<form method="post" action="/welcome/create">

    <input type="text" name="User.username" />
   <button type="submit">asd</button>
</form>
  ```

  ```java
  public void create(Request request ,Response response){

          User user = (User)request.getModel(User.class);

          System.out.print(user.getUsername());

     }
  ```

  ##数据库操作

  ```java
    public static void main(String args[]){
        DB.open("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1/test", "root", "root");
        User user = new User();
        user.setAge(3);
        user.setId(34);
        user.setPassword("");
        Model.select("id","username").list(User.class);
        Model.where("id","26").list(User.class);
        Model.where("id","25").orderBy("id").one(User.class);
        List<User> users = Model.find_by_sql("select * from user");
        Model.find_all(User.class);
        Model.whereGt("id","20").list(User.class);
        Model.save(user);
        Model.where("id","28").update(user);
        System.out.print(users.size());
    }
}

  ```
  
  #增加路由&&配置
  ```xml
    <servlet>
        <servlet-name>thunder</servlet-name>
        <servlet-class>com.thunder.web.ThunderDispatcher</servlet-class>
        <init-param>
            <param-name>lighting</param-name>
            <param-value>test.App</param-value>
        </init-param>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>thunder</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
  ```
  app 类必须继承lightning核心服务
  ```java
  public class App implements Lightning{

    public void init(Thunder thunder) {
      //这里会增加restful 7条路由
        //get -> /welcome  method:index
        //get -> /welcome/new  method:fresh
        //post -> /welcome/create  method:create
        //put  -> /welcome/:id/update method:update
        //get  -> /welcome/:id/edit   method:edit
        //delete  -> /welcome/:id/delete method:delete
        //get -> /welcome/:id   method:show
        thunder.addResource("welcome",new Welcome());
        //增加单独一条路由 执行welcome 类下index方法
        thunder.addRoute("/asd", Var.GET,"index",new Welcome());
        //增加一条过滤器路由
        thunder.before("/asd",Var.GET,(request,response)->{
           System.out.print("asdasd");
        });
        <!--配置注解扫描包-->
        <!--空即代表项目路径下所有包都被扫描-->
        thunder.setAppBasePackage("")
    }
}
  
  ```
    增加路由（注解方式）
    
    ```java
  @Controller
  public class test {
  
    //IOC 依赖注入
    @Inject
    Util util;
    
    @Action(value = "/demo",method = "GET")
    //Aop 拦截此方法
    @Check
    public void demo(Request request, Response response){
        System.out.print("hello world");
    }
}
    ```
    拦截注解类
    ```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {
}

    ```
    拦截切面类
    ```java
 /**
 * Created by icepoint1999 on 3/24/16.
 */

/**要代理的注解类 或注解方法
 * 
 */
@Aspect(value = Check.class)
public class Aoptest extends AspectProxy {

    /**
     * 重写父类代理方法 对应前置增强 后置增强 环绕增强
     * @param c 对应的代理类
     * @param method 代理执行方法
     * @param params 参数
     * @throws Throwable
     */
    public void before(Class<?> c , Method method, Object[] params)throws Throwable{

        System.out.print("in proxy");

    }

    /**
     * 环绕增强 如果返回true 则进行前后增强  false则只执行被切入方法
     * @param c
     * @param method
     * @param params
     * @return
     * @throws Throwable
     */
    public boolean intercept(Class<?> c ,Method method,Object[] params)throws Throwable{
        //返回
        return true;
    }

    /**
     * 
     * @param c
     * @param method
     * @param params
     * @param o 代理对象
     * @throws Throwable
     */
    public void after(Class<?> c ,Method method,Object[] params , Object o)throws Throwable{


    }
}

    ```
  当前默认页面模板存放在web-inf 后续会完善配置功能,暂定 约束>配置
  当前默认id 为int类型 即支持关系型数据库
  当前过滤器为路由型过滤器.你也可以自行配置单独路由。
  
  
  
  
  
努力完善中。。。如果感兴趣可以和我一起学习。
