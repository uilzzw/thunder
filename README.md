# thunder

```顾名思义 力求一个如闪电般开发速度的java mvc 框架


  最新更新: 1.添加了实体参数接收对象
           2.添加了restful风格的资源路径
           3.优化了一些细节。
           4.增加了activerecord基础
           5.优化了activerecord

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

  ```
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
  
努力完善中。。。如果感兴趣可以和我一起学习。
