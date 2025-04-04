package com.toolfinder.mytools.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolfinder.mytools.entities.ShopDetails;
import com.toolfinder.mytools.repositories.ShopRepository;
import com.toolfinder.mytools.repositories.ToolRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ToolRepository toolRepository;

    public ShopDetails shopLogin(String shopEmail, String shopPassword) throws Exception {
        ShopDetails shopDetails = shopRepository.findByShopEmail(shopEmail);
        if (shopDetails != null) {
            if (shopDetails.getShopPassword().equals(shopPassword)) {
                return shopRepository.findByShopEmail(shopEmail);
            } else {
                throw new Exception("Invalid password");
            }
        } else {
            throw new Exception("No shops found with the email id. Please Sign up");
        }
    }
    
    public ShopDetails addShop(ShopDetails shopDetails) throws Exception {
        if (shopRepository.findByShopEmail(shopDetails.getShopEmail()) == null) {
            return shopRepository.save(shopDetails);
        } else {
            throw new Exception("Shop already exists. Please Sign in");
        }
    }

    public ShopDetails findShopById(String shopId) {
        return shopRepository.findByShopId(shopId);
    }

    public List<ShopDetails> findShopByName(String shopName) {
        return shopRepository.findByShopName(shopName);
    }

    public ShopDetails findShopByEmail(String shopEmail) {
        return shopRepository.findByShopEmail(shopEmail);
    }

    public List<ShopDetails> findShopByPincode(String shopPincode) {
        return shopRepository.findByShopPincode(shopPincode);
    }

    public void deleteShop(String shopId) throws Exception {
        if (shopRepository.findByShopId(shopId) != null) {
            shopRepository.delete(shopRepository.findByShopId(shopId));
            toolRepository.findAll().forEach(tool -> {
                if (tool.getShopDetails().getShopId().equals(shopId)) {
                    toolRepository.delete(tool);
                }
            });
        } else {
            throw new Exception("Shop not found");
        }
    }

    public void updateShop(ShopDetails shopDetails) throws Exception {
        if (shopRepository.findByShopId(shopDetails.getShopId()) != null) {
            shopRepository.save(shopDetails);
        } else {
            throw new Exception("Shop not found");
        }
    }
}
