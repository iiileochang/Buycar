package com.buycar.tw.model.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "order_productsDetail")
public class Order_productsDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer opid;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "orderid")
    private Order order;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "productid")
    private Product product;
    private Integer buyquantity;
    private Integer price;

    public Integer getOpid() {
        return opid;
    }

    public void setOpid(Integer opid) {
        this.opid = opid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}