package com.buycar.tw.service.Impl;

import com.buycar.tw.model.entity.Cart;
import com.buycar.tw.model.entity.Product;
import com.buycar.tw.repository.UserRepository;
import com.buycar.tw.service.CartService;
import com.buycar.tw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductService service;

    @Override
    public void addCart(HttpSession session,Product product,Integer needQuantitiy,Integer allNeedQuantity) {
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
//        如果購物車為空
        if (cart == null) {
            cart = new HashMap<>();
        }
//        判斷此商品是否已經有在購物車內，沒有建一個新的cart物件實體，有就數量往上加
        Cart item = cart.get(product.getPid());
        if (item == null) {
            item = new Cart();
            item.setUser(userRepository.findByUname((String) session.getAttribute("uname")));
            item.setProduct(product);
            item.setQuantity(needQuantitiy);
            item.setTotalprice(product.getProductDetail().getPprice()*allNeedQuantity);
        } else {
            item.setQuantity(item.getQuantity() + needQuantitiy);
            item.setTotalprice(product.getProductDetail().getPprice()*allNeedQuantity);
        }
        Integer cartTotalPrice =(Integer)session.getAttribute("cartTotalPrice");
        cartTotalPrice+=product.getProductDetail().getPprice()*needQuantitiy;
//        把商品塞入購物車cart內，再加入Session
        cart.put(product.getPid(), item);
        session.setAttribute("cartTotalPrice",cartTotalPrice);
        session.setAttribute("cart", cart);
    }

    @Override
    public void removeCar(Model model, HttpSession session,Integer pid) {
        Map<Integer, Cart> cart=(Map<Integer, Cart>) session.getAttribute("cart");
        cart.remove(pid);
        session.setAttribute("cart", cart);
        Integer cartTotalPrice =0;
        for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
            cartTotalPrice+=entry.getValue().getTotalprice();
        }
        session.setAttribute("cartTotalPrice",cartTotalPrice);
    }
}

