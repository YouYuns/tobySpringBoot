package com.example.tobyspringboot;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class HelloDecorator implements HelloService{

    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
