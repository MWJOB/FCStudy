package org.example.naver_api_restaurant.wishlist.service;

import lombok.RequiredArgsConstructor;
import org.example.naver_api_restaurant.naver.NaverClient;
import org.example.naver_api_restaurant.naver.dto.SearchImageReq;
import org.example.naver_api_restaurant.naver.dto.SearchLocalReq;
import org.example.naver_api_restaurant.wishlist.dto.WishListDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    public WishListDto search(String query){

        //지역 검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        //지역 검색 결과가 있을 때
        if(searchLocalRes.getTotal() > 0){
            var localItem = searchLocalRes.getItems().stream().findFirst().get();

            //검색하기 용이하도록 괄호가 쳐져있으면 전부 삭제
            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            //이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                //결과물 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }
        return new WishListDto();

    }
}
