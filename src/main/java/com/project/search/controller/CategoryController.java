package com.project.search.controller;

import com.project.search.common.CommonRes;
import com.project.search.model.CategoryModel;
import com.project.search.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller("/category")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    @ResponseBody
    public CommonRes list(){
        List<CategoryModel> categoryModelList = categoryService.selectAll();
        return CommonRes.create(categoryModelList);
    }

}
