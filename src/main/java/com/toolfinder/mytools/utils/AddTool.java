package com.toolfinder.mytools.utils;

import com.toolfinder.mytools.entities.ToolDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddTool {
    
    private String shopId;
    private ToolDetails toolDetails;
}
