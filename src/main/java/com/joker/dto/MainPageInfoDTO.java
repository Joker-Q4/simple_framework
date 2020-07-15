package com.joker.dto;

import com.joker.entity.HeadLine;
import com.joker.entity.ShopCategory;
import lombok.Data;

import java.util.List;

@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}