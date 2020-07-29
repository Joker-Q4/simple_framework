package com.joker.service.combine.impl;

import com.joker.entity.dto.MainPageInfoDTO;
import com.joker.entity.dto.Result;
import com.joker.entity.bo.HeadLine;
import com.joker.entity.bo.ShopCategory;
import com.joker.service.combine.HeadLineShopCategoryCombineService;
import com.joker.service.solo.HeadLineService;
import com.joker.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

import java.util.List;

@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    @Autowired
    private HeadLineService headLineService;
    @Autowired
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
