package test;

import com.thunder.activeRecord.DB;
import com.thunder.activeRecord.Model;
import test.model.User;

import java.util.List;

/**
 * Created by icepoint1999 on 3/17/16.
 */
public class demo {

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
