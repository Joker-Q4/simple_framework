package com.joker.entity.dto;

import com.joker.entity.bo.HeadLine;
import com.joker.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}