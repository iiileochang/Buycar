package com.buycar.tw.controller;

import com.buycar.tw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductSortController {
    @Autowired
    ProductService service;

    @RequestMapping("/sortProduct")
    @PostMapping
    public String sortProduct(Model model, String newSort) {
        service.reSort(newSort);
        service.setProductList(model, "", "變更順序成功","success");
        return "productAdmin";
    }

}
