package com.joker.service.combine.impl;

import com.joker.dto.MainPageInfoDTO;
import com.joker.dto.Result;
import com.joker.entity.HeadLine;
import com.joker.entity.ShopCategory;
import com.joker.service.combine.HeadLineShopCategoryCombineService;
import com.joker.service.solo.HeadLineService;
import com.joker.service.solo.ShopCategoryService;

import java.util.List;

public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    private HeadLineService headLineService;
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        //获取头条列表
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLine, 1, 4);
        //获取店铺类别列表
        ShopCategory shopCategory = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategory, 1, 100);
        //合并两者并返回
        return mergeMainPageInfoResult(headLineResult, shopCategoryResult);
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }
}
