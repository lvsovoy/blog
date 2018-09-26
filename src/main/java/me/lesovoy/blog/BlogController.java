package me.lesovoy.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String homePage(Model model,@RequestParam(value = "sort", required = false) String sort ) throws Exception {

        Connection conn = DriverManager.getConnection("jdbc:h2:~/posts","","");
        List<Post> postList = new LinkedList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = null;
        //list all

        if (sort!=null && sort.equals("asc")) {
            rs = stmt.executeQuery("SELECT * FROM PUBLIC.POSTS ORDER BY ID ASC");
        } else {
            rs = stmt.executeQuery("SELECT * FROM PUBLIC.POSTS ORDER BY ID DESC");
        }
        while(rs.next()){
            postList.add(new Post(rs.getInt("ID"),rs.getTimestamp("DATE"),rs.getString("TEXT")));
        }
        conn.close();

        model.addAttribute("appname", appName);
        model.addAttribute("posts",postList);

        return "home";
    }
    @GetMapping("/add")
    public String add(){

        return "add";
    }

    @GetMapping("/delete")
    public String delete(Model model,@RequestParam("id") int id) throws Exception{

        Connection conn = DriverManager.getConnection("jdbc:h2:~/posts","","");
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM PUBLIC.POSTS WHERE ID = '"+id+"' ORDER BY ID DESC");
        while(rs.next()){
            model.addAttribute("text",rs.getString("TEXT"));
        }
        conn.close();
        model.addAttribute("id",id);

        return "delete";
    }

    @GetMapping("/edit")
    public String edit(Model model,@RequestParam("id") int id) throws Exception{

        Connection conn = DriverManager.getConnection("jdbc:h2:~/posts","","");
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM PUBLIC.POSTS WHERE ID = '"+id+"' ORDER BY ID DESC");
        while(rs.next()){
            model.addAttribute("text",rs.getString("TEXT"));
        }
        conn.close();
        model.addAttribute("id",id);
        return "edit";
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/add")
    public String add(@RequestParam("text") String text)throws Exception{

        Connection conn = DriverManager.getConnection("jdbc:h2:~/posts","","");
        Statement stmt = conn.createStatement();

        stmt.execute("INSERT INTO POSTS.PUBLIC.Posts(Date,Text) VALUES(\"CURRENT_TIMESTAMP\"(),\'"+text+"\')"); //SQL injecting is possible

        conn.close();
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") int id) throws Exception{

        Connection conn = DriverManager.getConnection("jdbc:h2:~/posts","","");
        Statement stmt = conn.createStatement();
        stmt.execute("DELETE FROM PUBLIC.POSTS WHERE ID = '"+id+"'");

        conn.close();

        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") int id, @RequestParam("text") String text) throws Exception{

        Connection conn = DriverManager.getConnection("jdbc:h2:~/posts","","");
        Statement stmt = conn.createStatement();
        stmt.execute("UPDATE POSTS.PUBLIC.Posts SET DATE=\"CURRENT_TIMESTAMP\"(),TEXT='"+text+"' WHERE ID = '"+id+"'");

        conn.close();

        return "redirect:/";
    }


}

