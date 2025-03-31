package com.toolfinder.mytools.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "shopDetails")
public class ShopDetails {
    
    @Id
    private String shopId;
    @NonNull
    private String shopName;
    @NonNull
    private String shopPassword;
    @NonNull
    private String shopEmail;
    @NonNull
    private String shopContact;
    @NonNull
    private String shopAddress;
    @NonNull
    private String shopPincode;
    @NonNull
    private String shopCity;
    @NonNull
    private String shopState;
    private List<ToolDetails> tools;
}