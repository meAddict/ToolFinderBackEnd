package com.toolfinder.mytools.controllers;
import com.toolfinder.mytools.services.ShopService;
import com.toolfinder.mytools.services.ToolService;
import com.toolfinder.mytools.utils.AddTool;
import com.toolfinder.mytools.utils.DeleteShop;
import com.toolfinder.mytools.utils.DeleteTool;
import com.toolfinder.mytools.utils.SearchTools;
import com.toolfinder.mytools.utils.ShopFilters;
import com.toolfinder.mytools.utils.ShopLogin;
import com.toolfinder.mytools.utils.ShowTools;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toolfinder.mytools.entities.ShopDetails;
import com.toolfinder.mytools.entities.ToolDetails;

@RestController
@RequestMapping("/mytools")
@CrossOrigin
public class Controller {
    
    @Autowired
    private ShopService shopService;

    @Autowired
    private ToolService toolService;

    @PostMapping("/addtool")
    public void addTool(@RequestBody AddTool addTools) throws Exception {
        toolService.addTool(addTools.getShopId(), addTools.getToolDetails());;
    }

    @PostMapping("/showtool")
    public List<ToolDetails> findAllTools(@RequestBody ShowTools showTools) throws Exception {
        return toolService.findAllTools(showTools.getShopId());
    }
    
    @PostMapping("/deletetool")
    public void deleteTool(@RequestBody DeleteTool deleteTool) throws Exception {
        toolService.deleteTool(deleteTool.getToolId());
    }

    @PostMapping("/findtool")
    public List<ToolDetails> searchTool(@RequestBody SearchTools searchTool) throws Exception {
        return toolService.findToolsByNameAndLocation(searchTool.getToolName(), searchTool.getLocationPincode());
    }

    @PostMapping("/login")
    public ShopDetails loginShop(@RequestBody ShopLogin shopLogin) throws Exception {
        if(shopService.shopLogin(shopLogin.getShopEmail(), shopLogin.getShopPassword()) == null) {
            throw new Exception("Invalid credentials");
        } 
        return shopService.shopLogin(shopLogin.getShopEmail(), shopLogin.getShopPassword());
    }
    
    @PostMapping("/createshop")
    public void createShop(@RequestBody ShopDetails shopDetails) throws Exception {
        if (shopService.findShopByEmail(shopDetails.getShopEmail()) != null) {  
            throw new Exception("Shop already exists");
        } else {
            shopService.addShop(shopDetails);
        }
    }

    @PostMapping("/getshop/pincode")
    public List<ShopDetails> getShopByPincode(@RequestBody ShopFilters shopFilters) throws Exception {
        return shopService.findShopByPincode(shopFilters.getFilterField());
    }

    @PostMapping("/getshop/name")
    public List<ShopDetails> getShopByName(@RequestBody ShopFilters shopFilters) throws Exception {
        return shopService.findShopByName(shopFilters.getFilterField());
    }

    @PostMapping("/deleteshop")
    public void deleteShop(@RequestBody DeleteShop deleteShop) throws Exception {
        shopService.deleteShop(deleteShop.getShopId());
    }
}
