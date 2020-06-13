package com.project.search.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.search.common.*;
import com.project.search.model.SellerModel;
import com.project.search.request.PageQuery;
import com.project.search.request.SellerCreateReq;
import com.project.search.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller("/admin/seller")
@RequestMapping("/admin/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /**
     * 1. Seller list
     */
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getSize());
        List<SellerModel> sellerModelList = sellerService.selectAll();
        PageInfo<SellerModel> sellerModelPageInfo = new PageInfo<>(sellerModelList);

        ModelAndView modelAndView = new ModelAndView("/admin/seller/index.html");
        modelAndView.addObject("data", sellerModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        modelAndView.addObject("ACTION_NAME", "index");
        return modelAndView;
    }

    /**
     * 2. Redirect to create a Seller
     */
    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        List<SellerModel> sellerModelList = sellerService.selectAll();
        ModelAndView modelAndView = new ModelAndView("/admin/seller/create.html");

        modelAndView.addObject("data", sellerModelList);
        modelAndView.addObject("CONTROLLER_NAME", "seller");
        modelAndView.addObject("ACTION_NAME", "create");
        return modelAndView;
    }

    /**
     * 3. Create a New Seller
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid SellerCreateReq sellerCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        SellerModel sellerModel = new SellerModel();
        sellerModel.setName(sellerCreateReq.getName());
        sellerService.create(sellerModel);
        return "redirect:/admin/seller/index";
    }

    /**
     * 4. Disable Seller
     */
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes disable(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, 1);
        return CommonRes.create(sellerModel);
    }

    /**
     * 5. Disable Seller
     */
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @AdminPermission
    @ResponseBody
    public CommonRes enable(@RequestParam(value = "id") Integer id) throws BusinessException {
        SellerModel sellerModel = sellerService.changeStatus(id, 0);
        return CommonRes.create(sellerModel);
    }

}
