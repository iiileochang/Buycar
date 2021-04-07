package com.buycar.tw.controller;

import com.buycar.tw.model.entity.Cart;
import com.buycar.tw.model.entity.Product;
import com.buycar.tw.repository.UserRepository;
import com.buycar.tw.service.CartService;
import com.buycar.tw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class CartController {
    @Autowired
    ProductService service;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartService cartService;

    @RequestMapping("/addCar")
    @PostMapping
    public String addCar(Model model, Integer pid, Integer needQuantitiy, HttpSession session) {
//        取得資料庫中所選的商品資訊
        Product product = service.findByPid(pid).get();
//        判斷是否丟null數量
        if (needQuantitiy == null) {
            service.setProductList(model, "", "請輸入產品數量後再選擇ADD", "warning");
            return "product";
        }
        if (needQuantitiy == 0) {
            service.setProductList(model, "", "輸入的數量必須大於0", "warning");
            return "product";
        }
//        判斷原先已加入購物車的數量
        Integer existedQuantity = 0;
        if (session.getAttribute("cart") != null && ((Map<Integer, Cart>) session.getAttribute("cart")).get(pid) != null) {
            Integer tempQuantity = ((Map<Integer, Cart>) session.getAttribute("cart")).get(pid).getQuantity();
            existedQuantity = (tempQuantity == null) ? 0 : tempQuantity;
        }
        Integer allNeedQuantity = existedQuantity + needQuantitiy;
//        如果購買總數量大於庫存，退回
        if (needQuantitiy == null || allNeedQuantity > product.getPquantity()) {
            service.setProductList(model, "", "所選總數量" + allNeedQuantity + "，已超出商品庫存" + product.getPquantity(), "warning");
            return "product";
        }
        if (allNeedQuantity * product.getProductDetail().getPprice() < 0) {
            service.setProductList(model,"","超出Integer負荷 " + allNeedQuantity * product.getProductDetail().getPprice() + " 為負數 ","error");
            return "product";
        }
//        如果購買總數量小於庫存，加入購物車
        cartService.addCart(session, product, needQuantitiy, allNeedQuantity);
//        回傳前端
        service.setProductList(model, "", "所選商品" + product.getPname() + "共" + allNeedQuantity + "個，已加入購物車", "success");
        return "product";
    }

    @GetMapping("/clearCart")
    public String clearCart(Model model, HttpSession session) {
//        清除Session內購物車Map
        session.removeAttribute("cart");
        session.setAttribute("cartTotalPrice", 0);
        service.setProductList(model, "", "已清空購物車", "success");
        return "product";
    }

    @GetMapping("/removeCar")
    public String removeCar(Model model, HttpSession session, Integer pid) {
//        清除單一購物車內商品
        if (session.getAttribute("cart") != null && ((Map<Integer, Cart>) session.getAttribute("cart")).get(pid) != null) {
            cartService.removeCar(model, session, pid);
            service.setProductList(model, "", "已將商品從購物車移除", "success");
        } else {
            service.setProductList(model, "", "移出購物車錯誤", "error");
        }
        return "product";
    }

}

