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
    public ToolDetails findByToolName(String toolName);
    public ToolDetails findByToolDescription(String toolDescription);
    public List<ToolDetails> findByShopDetails(ShopDetails shopDetails);
}
