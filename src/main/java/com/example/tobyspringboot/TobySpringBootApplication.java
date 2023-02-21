package com.example.tobyspringboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@ComponentScan
public class TobySpringBootApplication {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        //스프링 컨테이너를 만드는 작업
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext() {

            @Override
            protected void onRefresh() {
                super.onRefresh();
                //서블릿을 등록하는 작업

                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this);

                WebServer webServer = serverFactory.getWebServer((ServletContextInitializer)
                        servletContext -> {
                            // 저 HelloController라는 것은 매 요청마다 새로운 인스턴스를 만들 필요가 없다 계속 재사용해도된다.
                            servletContext.addServlet("dispatcherServlet",
                                    dispatcherServlet
                            ).addMapping("/*");
                        });
                webServer.start();
            }
        };
//        applicationContext.registerBean(HelloController.class);
//        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.register(com.example.tobyspringboot.TobySpringBootApplication.class);
        applicationContext.refresh();

        //서블릿 컨테이너를 코드로 실행
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
    }
}