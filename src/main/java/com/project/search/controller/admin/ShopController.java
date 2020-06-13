package com.project.search.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.search.common.AdminPermission;
import com.project.search.common.BusinessErrorEnum;
import com.project.search.common.BusinessException;
import com.project.search.common.CommonUtil;
import com.project.search.model.ShopModel;
import com.project.search.request.PageQuery;
import com.project.search.request.ShopCreateReq;
import com.project.search.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller("/admin/shop")
@RequestMapping("/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 1. All-Shop List
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        List<ShopModel> shopModelList = shopService.selectAll();
        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/shop/index.html");
        modelAndView.addObject("data", shopModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "shop");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    /**
     * 2. Create Page
     */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/shop/create.html");
        modelAndView.addObject("CONTROLLER_NAME", "shop");
        modelAndView.addObject("ACTION_NAME", "create");
        return modelAndView;
    }

    /**
     * 3. Create a new Shop
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        ShopModel shopModel = new ShopModel();
        shopModel.setName(shopCreateReq.getName());
        shopModel.setPricePerMan(shopCreateReq.getPricePerMan());
        shopModel.setCategoryId(shopCreateReq.getCategoryId());
        shopModel.setSellerId(shopCreateReq.getSellerId());
        shopModel.setStartTime(shopCreateReq.getStartTime());
        shopModel.setEndTime(shopCreateReq.getEndTime());
        shopModel.setLatitude(shopCreateReq.getLatitude());
        shopModel.setLongitude(shopCreateReq.getLongitude());
        shopModel.setAddress(shopCreateReq.getAddress());
        shopModel.setIconUrl(shopCreateReq.getIconUrl());

        shopService.create(shopModel);
        return "redirect:/admin/shop/index";
    }
}
