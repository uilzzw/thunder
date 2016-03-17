# thunder

＃顾名思义 追求一个如闪电般开发速度的java mvc 框架


  最新更新:
  * [x]添加了实体参数接收对象
  * [x]添加了restful风格的资源路径
  * [x]优化了一些细节。
  * [x]增加了activerecord基础
  * [x]优化了activerecord

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


    }
}
  
  ```
  当前默认页面模板存放在web-inf 后续会完善配置功能,暂定约束>配置
  当前默认id 为int类型 即支持关系型数据库
  当前过滤器为路由型过滤器.你也可以自行配置单独路由。
  
  
  
  
  
努力完善中。。。如果感兴趣可以和我一起学习。
