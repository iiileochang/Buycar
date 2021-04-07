package com.buycar.tw.service;

import com.buycar.tw.model.entity.Order;
import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.entity.User;
import com.buycar.tw.model.request.ProductInsertRequest;
import com.buycar.tw.model.response.ProductInsertResponse;
import com.buycar.tw.model.response.ProductSelectResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface OrderService {
//    public List<ProductSelectResponse> addCar(Integer pid,Integer needQuantitiy);
    void addOrder(Order order, List<Product> orderProductList, Integer totalPrice,User user);
    void showOrder(Model model, HttpSession session,Integer pageIndex, Integer pageSize);
}
