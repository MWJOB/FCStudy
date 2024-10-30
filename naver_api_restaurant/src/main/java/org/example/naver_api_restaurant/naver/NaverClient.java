package org.example.naver_api_restaurant.naver;

import org.example.naver_api_restaurant.naver.dto.SearchImageReq;
import org.example.naver_api_restaurant.naver.dto.SearchImageRes;
import org.example.naver_api_restaurant.naver.dto.SearchLocalReq;
import org.example.naver_api_restaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        //주소
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        //헤더
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalRes>() {
        };

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq) {
            //주소
            var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                    .queryParams(searchImageReq.toMultiValueMap())
                    .build()
                    .encode()
                    .toUri();

            //헤더
            var headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", naverClientId);
            headers.set("X-Naver-Client-Secret", naverClientSecret);
            headers.setContentType(MediaType.APPLICATION_JSON);

            var httpEntity = new HttpEntity<>(headers);
            var responseType = new ParameterizedTypeReference<SearchImageRes>() {
            };

            var responseEntity = new RestTemplate().exchange(
                    uri,
                    HttpMethod.GET,
                    httpEntity,
                    responseType
            );

            return responseEntity.getBody();
    }
}
