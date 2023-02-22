package com.example.tobyspringboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HelloApiTest {

    @Test
    void helloApi(){
        // http localhost:8080/hello?name=Spring
        // HTTPie
        TestRestTemplate rest =new TestRestTemplate();
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "Spring");

        // status code 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);

        // header (content-type) text/plain
        //equalto하면 인코딩정보까지 헤더에 있기 때문에 오류가 난다.
        //startsWith를 써서 앞부분이 같은지 비교한다.
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);


        //body Hello Spring
        Assertions.assertThat(res.getBody()).isEqualTo("Hello Spring");

        //이 세가지를 왔는지 검증해야된다.

    }
}
