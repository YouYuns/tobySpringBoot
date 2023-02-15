package com.example.tobyspringboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer((ServletContextInitializer)
                servletContext -> {
                    servletContext.addServlet("hello", new HttpServlet() {
                        @Override
                        public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                            res.setStatus(200); //응답코드
                            res.setHeader("Content-Type", "text/plain"); //헤더
                            res.getWriter().println("Hello Servlet");//바디
                        }
                    }).addMapping("/hello");
                });
        webServer.start();
    }
}
