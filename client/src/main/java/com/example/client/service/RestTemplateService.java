package com.example.client.service;

import com.example.client.dto.Req;
import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http://localhost/api/server/hello
    //response
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "aaaa")
                .queryParam("age", "90")
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public void post() {
        //http://localhost:9090/api/server/user/{userId}/name/{UserName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();
        System.out.println(uri);

        //http body -> object -< object mapper -> json -> rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(20);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(uri, req, String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

//        return response.getBody();
    }
        public UserResponse exchange(){

            URI uri = UriComponentsBuilder
                    .fromUriString("http://localhost:9090")
                    .path("/api/server/user/{userId}/name/{userName}")
                    .encode()
                    .build()
                    .expand(100,"steve")
                    .toUri();
            System.out.println(uri);

            //http body -> object -< object mapper -> json -> rest template -> http body json
            UserRequest req = new UserRequest();
            req.setName("steve");
            req.setAge(20);

            RequestEntity<UserRequest> requestEntity = RequestEntity
                    .post(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("x-authorization", "abcd")
                    .header("custom-header", "ffff")
                    .body(req);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
            return response.getBody();
    }

    public Req<UserResponse> genericExchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();
        System.out.println(uri);

        //http body -> object -< object mapper -> json -> rest template -> http body json
        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(20);

        Req<UserRequest> req = new Req<>();
        req.setHeader(
                new Req.Header()
        );

        req.setResBody(
                userRequest
        );



        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();


        //ParameterizedTypeReference는 제네릭 타입을 갖는 객체의 타입 정보를 보존하기 위한 목적으로 사용한다.
        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});

        //첫 getBody는 ResponseEntity
        return response.getBody();

    }
}
