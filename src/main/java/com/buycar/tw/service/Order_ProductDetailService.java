package com.buycar.tw.service;

import com.buycar.tw.model.entity.Cart;
import com.buycar.tw.model.entity.Order;
import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.request.ProductInsertRequest;
import com.buycar.tw.model.response.ProductInsertResponse;
import com.buycar.tw.model.response.ProductSelectResponse;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Order_ProductDetailService {
    void addDetail(Map<Integer, Cart> map,Order order);
    void showOrderDetail(Model model, HttpSession session, Integer oid);

}