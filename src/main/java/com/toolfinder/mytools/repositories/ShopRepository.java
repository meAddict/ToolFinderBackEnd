package com.toolfinder.mytools.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.toolfinder.mytools.entities.ShopDetails;

@Repository
public interface ShopRepository extends MongoRepository<ShopDetails, String> {

    public ShopDetails findByShopId(String shopId);
    public List<ShopDetails> findByShopName(String shopName);
    public List<ShopDetails> findByShopPincode(String shopPincode);
    public ShopDetails findByShopEmail(String shopEmail);
}
