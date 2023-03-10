package com.example.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface UnitTest{

}
public class HelloServiceTest {

    @UnitTest
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();

       String ret =  helloService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }
}
