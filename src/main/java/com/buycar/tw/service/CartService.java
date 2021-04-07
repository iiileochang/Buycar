package com.buycar.tw.service;

import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.request.ProductInsertRequest;
import com.buycar.tw.model.response.ProductInsertResponse;
import com.buycar.tw.model.response.ProductSelectResponse;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface CartService {
    void addCart(HttpSession session,Product product,Integer needQuantitiy,Integer allNeedQuantity);
    void removeCar(Model model, HttpSession session,Integer pid);

}