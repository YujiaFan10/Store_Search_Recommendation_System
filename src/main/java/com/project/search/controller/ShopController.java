package com.project.search.controller;

import com.project.search.common.BusinessErrorEnum;
import com.project.search.common.BusinessException;
import com.project.search.common.CommonRes;
import com.project.search.model.CategoryModel;
import com.project.search.model.ShopModel;
import com.project.search.service.CategoryService;
import com.project.search.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("/shop")
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CategoryService categoryService;

    /**
     *  Recommendation Service v1.0
     */
    @RequestMapping("/recommend")
    @ResponseBody
    public CommonRes recommend(@RequestParam("latitude")BigDecimal latitude,
                                    @RequestParam("longitude")BigDecimal longitude) throws BusinessException {
        if(latitude == null || longitude == null){
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR);
        }
        List<ShopModel> shopModelList = shopService.recommend(longitude, latitude);
        return CommonRes.create(shopModelList);

    }

    /**
     * Search Service
     */
    @RequestMapping("/search")
    @ResponseBody
    public CommonRes search(@RequestParam(name = "longitude")BigDecimal longitude,
                            @RequestParam(name = "latitude") BigDecimal latitude,
                            @RequestParam(name = "keyword")String keyword,
                            @RequestParam(name = "categoryId", required = false) Integer categoryId,
                            @RequestParam(name = "orderby", required = false) Integer orderby,
                            @RequestParam(name = "tags", required = false) String tags) throws BusinessException, IOException {
        if(latitude==null || longitude == null || keyword == null){
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR);
        }
        //version 1.0 MySQL
        //List<ShopModel> shopModelList = shopService.search(longitude, latitude, keyword, orderby, categoryId, tags);
        //List<Map<String, Object>> tagsAggregation = shopService.searchGroupByTags(keyword, categoryId, tags);

        Map<String,Object> result = shopService.searchES(longitude,latitude,keyword,orderby,categoryId,tags);
        List<ShopModel> shopModelList = (List<ShopModel>) result.get("shop");
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        List<Map<String, Object>> tagsAggregation = (List<Map<String, Object>>) result.get("tags");
        HashMap<String, Object> map = new HashMap<>();
        map.put("shop", shopModelList);
        map.put("category", categoryModelList);
        map.put("tags", tagsAggregation);
        return CommonRes.create(map);

    }

}
