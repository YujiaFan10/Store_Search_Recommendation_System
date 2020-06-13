package com.project.search.impl;

import com.project.search.common.BusinessErrorEnum;
import com.project.search.common.BusinessException;
import com.project.search.dao.CategoryModelMapper;
import com.project.search.model.CategoryModel;
import com.project.search.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryModelMapper categoryModelMapper;

    @Override
    @Transactional
    public CategoryModel create(CategoryModel categoryModel) throws BusinessException {
        categoryModel.setCreatedAt(new Date());
        categoryModel.setUpdatedAt(new Date());
        try {
            categoryModelMapper.insertSelective(categoryModel);
        } catch (DuplicateKeyException e){
            throw new BusinessException(BusinessErrorEnum.CATEGORY_NAME_DUPLICATE);
        }
        return get(categoryModel.getId());
    }

    @Override
    public CategoryModel get(Integer id) {
        return categoryModelMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CategoryModel> selectAll() {
        return categoryModelMapper.selectAll();
    }

    @Override
    public Integer countAllCategory() {
        return categoryModelMapper.countAllCategory();
    }
}
