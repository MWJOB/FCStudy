package org.example.naver_api_restaurant.wishlist.repository;

import org.example.naver_api_restaurant.db.MemoryDbRepositoryAbstract;
import org.example.naver_api_restaurant.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository  //db를 저장하는 곳
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
