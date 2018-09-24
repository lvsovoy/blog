package me.lesovoy.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Controller
public class BlogController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:h2:~/posts","","");


        List<Post> postList = new LinkedList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PUBLIC.POSTS ORDER BY ID DESC");
        while(rs.next()){
            postList.add(new Post(rs.getInt("ID"),rs.getTimestamp("DATE"),rs.getString("TEXT")));
        }
//        postList.add(new Post(001,"Loreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolortLoreum ipsum sic amet dolort"));
//        postList.add(new Post(002,"Test2"));
        model.addAttribute("appname", appName);
        model.addAttribute("posts",postList);

        return "home";
    }
}

