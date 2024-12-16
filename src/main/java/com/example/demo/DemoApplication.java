package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

import org.apache.velocity.app.Velocity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class App extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getPathInfo();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Velocity.init();
        VelocityContext context = new VelocityContext();

        if ("/details".equals(page)) {
            Template template = Velocity.getTemplate("src/main/resources/templates/details.vm");
            context.put("body", "Details of the selected tournament will go here.");
            template.merge(context, out);
        } else {
            Template template = Velocity.getTemplate("src/main/resources/templates/home.vm");
            context.put("body", "List of upcoming tournaments will go here.");
            template.merge(context, out);
        }
        out.close();
    }
}