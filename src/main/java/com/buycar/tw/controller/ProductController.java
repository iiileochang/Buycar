package com.buycar.tw.controller;

import com.buycar.tw.model.request.ProductInsertRequest;
import com.buycar.tw.model.request.ProductSelectRequest;
import com.buycar.tw.model.response.EditProductAjaxResponse;
import com.buycar.tw.model.response.ProductInsertResponse;
import com.buycar.tw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ProductController {
    @Autowired
    ProductService service;


    @RequestMapping("/refreshProduct")
    @GetMapping
    public String findByNameLike(Model model) {
        service.setProductList(model, "", null, null);
        return "productAdmin";
    }

    @RequestMapping("/findByNameLike")
    @PostMapping
    public String findByNameLike(Model model, ProductSelectRequest request) {
        service.setProductList(model, request.getKeyword(), null, null);
        return "productAdmin";
    }

    @RequestMapping("/findByNameLike2")
    @PostMapping
    public String findByNameLike2(Model model, ProductSelectRequest request) {
        service.setProductList(model, request.getKeyword(), null, null);
        return "product";
    }

    @RequestMapping("/findByPsortAjax")
    @PostMapping
    @ResponseBody
    public EditProductAjaxResponse findByPsort(Model model, ProductSelectRequest request) {
        return new EditProductAjaxResponse(service.findByPsort(request.getPsort()));
    }

    @RequestMapping("/insertProduct")
    @PostMapping
    public String insertProduct(Model model, ProductInsertRequest request, HttpSession session) {
        model.addAttribute("product", service.insertProduct(request));
        service.setProductList(model, "", "新增產品 成功", "success");
        return "productAdmin";
    }

    @RequestMapping("/updateProduct")
    @PostMapping
    public String updateProduct(Model model, ProductInsertRequest request, HttpSession session) {
        ProductInsertResponse response = service.updateProduct(request);
        service.setProductList(model, "", "更新產品成功", "success");
        model.addAttribute("product", response);
        return "productAdmin";
    }

//    @RequestMapping("/uploadProduct")
//    @PostMapping
//    public String uploadProduct(Model model, ProductInsertRequest request, HttpSession session) {
//        service.setProductList(model, "", "上傳成功","success");
//        model.addAttribute("product", service.insertProduct(request));
//        return "productAdmin";
//    }

    @RequestMapping("/delProduct")
    @PostMapping
    public String delProduct(Model model, Integer[] keepArray) {
        if (keepArray != null) {
            service.delProduct(keepArray);
        }
        service.setProductList(model, "", "刪除成功", "success");
        return "productAdmin";
    }

}
