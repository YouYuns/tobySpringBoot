package com.example.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
        //스프링 컨테이너를 만드는 작업
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext() {

            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this);

                WebServer webServer = serverFactory.getWebServer((ServletContextInitializer)
                        servletContext -> {
                            servletContext.addServlet("dispatcherServlet",
                                    dispatcherServlet
                            ).addMapping("/*");
                        });
                webServer.start();
            }
        };
        applicationContext.register(applicationClass);
        applicationContext.refresh();
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
    }
}
