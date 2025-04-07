package com.toolfinder.mytools.repositories;

import java.util.List;

import org.apache.catalina.startup.Tool;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.toolfinder.mytools.entities.ShopDetails;
import com.toolfinder.mytools.entities.ToolDetails;

@Repository
public interface ToolRepository extends MongoRepository<ToolDetails, String> {

    public ToolDetails findByToolId(String toolId);
    public List<ToolDetails> findByToolName(String toolName);
    public List<ToolDetails> findByToolDescription(String toolDescription);
    public List<ToolDetails> findByToolPrice(String toolPrice);
    public List<ToolDetails> findByToolImage(String toolImage);
    public List<ToolDetails> findByShopDetails(ShopDetails shopDetails);
}
