package com.buycar.tw.controller;

import com.buycar.tw.model.entity.Cart;
import com.buycar.tw.model.entity.Order;
import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.entity.User;
import com.buycar.tw.repository.OrderRepository;
import com.buycar.tw.repository.Order_productsDetailRepository;
import com.buycar.tw.repository.ProductRepository;
import com.buycar.tw.repository.UserRepository;
import com.buycar.tw.service.OrderService;
import com.buycar.tw.service.Order_ProductDetailService;
import com.buycar.tw.service.ProductService;
import com.buycar.tw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    ProductService service;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    Order_productsDetailRepository order_productsDetailRepository;
    @Autowired
    Order_ProductDetailService order_productDetailService;
    
    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
//        從Session取出來的購物清單
        Map<Integer, Cart> map = (Map<Integer, Cart>) session.getAttribute("cart");
        User user = userRepository.findByUname((String) session.getAttribute("uname"));
//        新增一個Order與裝全部Produst的List與OrderDetail
        Order order = new Order();
        List<Product> orderProductList = new ArrayList<>();
//        初始化訂單總金額
        Integer totalPrice = 0;
        for (Map.Entry<Integer, Cart> entry : map.entrySet()) {
            //取Product出來
            if(productRepository.findById(entry.getKey()).isEmpty()){
                service.setProductList(model,"","商品id"+entry.getKey()+"不存在","error");
                return "product";
            }
            Product product = productRepository.findById(entry.getKey()).get();
            //處理Order的ProductList與總金額
            orderProductList.add(product);
            totalPrice += entry.getValue().getTotalprice();
        }
        if (totalPrice == 0) {
            service.setProductList(model,"","總金額不得為0","warning");
            return "product";
        }
        if (totalPrice > user.getCoin()) {
            service.setProductList(model,"","購買總金額 " + totalPrice + " 大於您的剩餘金額 " + user.getCoin(),"warning");
            return "product";
        }
        if (totalPrice<0){
            service.setProductList(model,"","超出Integer負荷 " + totalPrice + " 為負數 ","error");
            return "product";
        }
//        處理Order
        orderService.addOrder(order,orderProductList,totalPrice,user);
//        處理User金額
        userService.setCoin(user,totalPrice);
//        處理Order_productsDetail與產品數量
        order_productDetailService.addDetail(map,order);
//        重整網頁的資訊
        service.setProductList(model,"", "已結帳","success");
        session.removeAttribute("cart");
        session.setAttribute("cartTotalPrice", 0);
        session.setAttribute("coin", user.getCoin());
        return "product";
    }

    @GetMapping("/showOrder")
    public String showOrder(Model model, HttpSession session, @RequestParam(defaultValue="0") Integer pageIndex,@RequestParam(defaultValue="3") Integer pageSize) {
        session.setAttribute("pageIndex",pageIndex);
        session.setAttribute("pageSize",pageSize);
        orderService.showOrder(model,session,pageIndex,pageSize);
        return "OrderPage";
    }
    @GetMapping("/showOrderDetail")
    public String showOrderDetail(Model model, HttpSession session,Integer oid) {
        order_productDetailService.showOrderDetail(model,session,oid);
        orderService.showOrder(model, session,(Integer)session.getAttribute("pageIndex"),(Integer)session.getAttribute("pageSize"));
        return "OrderPage";
    }

    @GetMapping("/reback")
    public String reback(Model model, HttpSession session) {
        service.setProductList(model,"",null,null);
        return "product";
    }




}

