package com.buycar.tw.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orderform")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oid;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user")
    User user;

    private String status;
    private Integer totalprice;
    private Date ordertime;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "product_order",joinColumns = {@JoinColumn(name = "oid")},inverseJoinColumns = {@JoinColumn(name="pid")})
    private List<Product> products;

    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "order")
    private List<Order_productsDetail> order_productDetails;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order_productsDetail> getOrder_productDetails() {
        return order_productDetails;
    }

    public void setOrder_productDetails(List<Order_productsDetail> order_productDetails) {
        this.order_productDetails = order_productDetails;
    }
}
