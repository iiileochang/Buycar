package com.buycar.tw.model.response;

import com.buycar.tw.model.entity.Order;
import com.buycar.tw.model.entity.Order_productsDetail;

import java.util.Date;

public class OrderSelectResponse {
    private String productname;
    private Integer buyquantity;
    private String status;
    private Integer totalprice;
    private Date ordertime;
    private Integer price;
    private Integer oid;

    public OrderSelectResponse(){}
    public OrderSelectResponse(Order_productsDetail order_productsDetail){
        this.productname=order_productsDetail.getProduct().getPname();
        this.buyquantity=order_productsDetail.getBuyquantity();
        this.status=order_productsDetail.getOrder().getStatus();
        this.totalprice=order_productsDetail.getOrder().getTotalprice();
        this.ordertime=order_productsDetail.getOrder().getOrdertime();
        this.price=order_productsDetail.getPrice();
    }
    public OrderSelectResponse(Order order){
        this.oid=order.getOid();
        this.ordertime=order.getOrdertime();
        this.status=order.getStatus();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Integer getBuyquantity() {
        return buyquantity;
    }

    public void setBuyquantity(Integer buyquantity) {
        this.buyquantity = buyquantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
}
