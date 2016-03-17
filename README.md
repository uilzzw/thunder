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
  
努力完善中。。。如果感兴趣可以和我一起学习。
