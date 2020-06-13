package com.project.search.service;

import com.project.search.common.BusinessException;
import com.project.search.model.CategoryModel;

import java.util.List;

public interface CategoryService {

    CategoryModel create(CategoryModel categoryModel) throws BusinessException;

    CategoryModel get(Integer id);

    List<CategoryModel> selectAll();

    Integer countAllCategory();
}
