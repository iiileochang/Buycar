package com.buycar.tw.service.Impl;

import com.buycar.tw.model.entity.Cart;
import com.buycar.tw.model.entity.Order;
import com.buycar.tw.model.entity.Order_productsDetail;
import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.response.OrderDetailSelectResponse;
import com.buycar.tw.repository.OrderRepository;
import com.buycar.tw.repository.Order_productsDetailRepository;
import com.buycar.tw.repository.ProductRepository;
import com.buycar.tw.service.Order_ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Order_ProductDetailServiceImpl implements Order_ProductDetailService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    Order_productsDetailRepository order_productsDetailRepository;



    @Override
    public void addDetail(Map<Integer, Cart> map, Order order) {
        for (Map.Entry<Integer, Cart> entry : map.entrySet()) {
            Order_productsDetail order_productsDetail = new Order_productsDetail();
            if(productRepository.findById(entry.getKey()).isPresent()){
                Product product = productRepository.findById(entry.getKey()).get();
                order_productsDetail.setProduct(product);
                order_productsDetail.setBuyquantity(entry.getValue().getQuantity());
                order_productsDetail.setOrder(order);
                order_productsDetail.setPrice(product.getProductDetail().getPprice());
                order_productsDetailRepository.save(order_productsDetail);
                product.setPquantity(product.getPquantity() - entry.getValue().getQuantity());
                productRepository.save(product);
            }

        }
    }

    @Override
    public void showOrderDetail(Model model, HttpSession session, Integer oid) {
        List<Order_productsDetail> orderDetailList = orderRepository.findAllByOid(oid).getOrder_productDetails();
        List<OrderDetailSelectResponse> orderDetailSelectResponsesList=new ArrayList<>();
        for (Order_productsDetail o:
                orderDetailList) {
            orderDetailSelectResponsesList.add(new OrderDetailSelectResponse(o));
        }
        model.addAttribute("orderAllPrice",orderRepository.findAllByOid(oid).getTotalprice());
        model.addAttribute("orderDetailList",orderDetailSelectResponsesList);
    }
}

// https://www.runoob.com/java/java8-streams.html 使用Stream 篩選
//    Order order = new Order();
//    // order.getDetail()=> derailList
//    List<Order_productsDetail> order_productsDetails=new ArrayList<>();
//    //假設有多筆detail
//    List<Integer> tmp = new ArrayList<>();
//        order_productsDetails.stream().filter(e->tmp.contains(e.getOpid())).collect(Collectors.toList());
//                order_productsDetails.stream().filter(e->e.getOpid()==1).findFirst();
//anymatch(Boolean)