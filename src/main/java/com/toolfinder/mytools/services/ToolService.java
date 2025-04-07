package com.toolfinder.mytools.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toolfinder.mytools.entities.ShopDetails;
import com.toolfinder.mytools.entities.ToolDetails;
import com.toolfinder.mytools.repositories.ShopRepository;
import com.toolfinder.mytools.repositories.ToolRepository;

@Service
public class ToolService {

    @Autowired
    private ToolRepository toolRepository;
    @Autowired
    private ShopRepository shopRepository;
    
    public void addTool(String shopId, ToolDetails toolDetails) throws Exception {
        if(shopRepository.findByShopId(shopId) != null) {
            if (toolRepository.findByToolName(toolDetails.getToolName()) == null) {
                ShopDetails shopDetails = shopRepository.findByShopId(shopId);
                toolDetails.setShopDetails(shopDetails);
                toolRepository.save(toolDetails);
            } else {
                for (ToolDetails tools : toolRepository.findByToolName(toolDetails.getToolName())) {
                    if (tools.getShopDetails().getShopId().equals(shopId)) {
                        throw new Exception("Tool already exists in the shop");
                    } else {
                        continue;
                    }
                }
                ShopDetails shopDetails = shopRepository.findByShopId(shopId);
                toolDetails.setShopDetails(shopDetails);
                toolRepository.save(toolDetails);
            }
        } else {
            throw new Exception("Shop not found");
        }
    }

    public void deleteTool(String toolId) throws Exception {
        if(toolRepository.findByToolId(toolId) != null) {
            toolRepository.delete(toolRepository.findByToolId(toolId));
        } else {
            throw new Exception("Tool not found");
        }
    }

    public void updateTool(String toolId, ToolDetails toolDetails) throws Exception {
        if(toolRepository.findByToolId(toolId) != null) {
            toolRepository.save(toolRepository.findByToolId(toolId));
        } else {
            throw new Exception("Tool not found");
        }
    }
    
    public List<ToolDetails> findAllTools(String shopId) throws Exception {
        if (toolRepository.findByShopDetails(shopRepository.findByShopId(shopId)) != null) {
            return toolRepository.findByShopDetails(shopRepository.findByShopId(shopId));
        } else {
            throw new Exception("No shop found");
        }
    }

    public List<ToolDetails> findToolsByNameAndLocation(String toolName, String shopLocation) throws Exception {
        if (shopRepository.findByShopPincode(shopLocation) != null) {
            List<ToolDetails> tools = toolRepository.findAll();
            List<ToolDetails> toolsDesired = new ArrayList<>();
            tools.stream().forEach(tool -> {
                if (tool.getShopDetails().getShopPincode().equals(shopLocation) && tool.getToolName().toLowerCase().trim().contains(toolName.toLowerCase().trim())) {
                    toolsDesired.add(tool);
                }
            });
            if (toolsDesired.size() == 0) {
                throw new Exception("No tools find in any shops");
            } else {
                return toolsDesired;
            }
        } else {
            throw new Exception("No shop in the given location");
        }
    }
}
