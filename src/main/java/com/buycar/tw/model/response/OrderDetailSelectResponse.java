package com.buycar.tw.model.response;

import com.buycar.tw.model.entity.Order_productsDetail;

public class OrderDetailSelectResponse {
    private Integer opid;
    private String pname;
    private Integer buyquantity;
    private Integer price;
    private Integer allPrice;

    public OrderDetailSelectResponse(){}
    public OrderDetailSelectResponse(Order_productsDetail order_productsDetail){
        this.opid=order_productsDetail.getOpid();
        this.pname=order_productsDetail.getProduct().getPname();
        this.buyquantity=order_productsDetail.getBuyquantity();
        this.price=order_productsDetail.getPrice();
        this.allPrice=order_productsDetail.getPrice()*order_productsDetail.getBuyquantity();
    }

    public Integer getOpid() {
        return opid;
    }

    public void setOpid(Integer opid) {
        this.opid = opid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getBuyquantity() {
        return buyquantity;
    }

    public void setBuyquantity(Integer buyquantity) {
        this.buyquantity = buyquantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Integer allPrice) {
        this.allPrice = allPrice;
    }
}
