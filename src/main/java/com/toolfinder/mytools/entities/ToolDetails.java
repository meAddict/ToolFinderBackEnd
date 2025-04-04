package com.toolfinder.mytools.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.Nullable;

import jakarta.annotation.Nonnull;
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
@Document(collection = "toolDetails")
public class ToolDetails {
    
    @Id
    private String toolId;
    @Nonnull
    private String toolName;
    @Nonnull
    private String toolDescription;
    @Nonnull
    private String toolPrice;
    @Nullable
    private String toolImage;
    @DBRef
    private ShopDetails shopDetails;
}
