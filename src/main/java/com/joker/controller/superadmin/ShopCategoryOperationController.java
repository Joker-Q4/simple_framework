package com.joker.controller.superadmin;

import com.joker.entity.dto.Result;
import com.joker.entity.bo.ShopCategory;
import com.joker.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ShopCategoryOperationController {

    @Autowired
    private ShopCategoryService shopCategoryService;

    public Result<Boolean> addShopCategory(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.addShopCategory(new ShopCategory());
    }
    public Result<Boolean> removeShopCategory(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.removeShopCategory(1);
    }
    public Result<Boolean> modifyShopCategory(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.modifyShopCategory(new ShopCategory());
    }
    public Result<ShopCategory> queryShopCategoryById(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.queryShopCategoryById(1);
    }
    public Result<List<ShopCategory>> queryShopCategory(HttpServletRequest req, HttpServletResponse resp){
        return shopCategoryService.queryShopCategory(null, 1, 100);
    }

}
