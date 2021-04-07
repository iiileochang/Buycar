package com.buycar.tw.model.entity;

import com.buycar.tw.model.request.ProductInsertRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="dno",referencedColumnName = "dno")
    private ProductDetail productDetail;

    private String pclass;
    private String pname;
    private Integer pquantity;
    private Integer psort;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "product_order",joinColumns = {@JoinColumn(name = "pid")},inverseJoinColumns = {@JoinColumn(name="oid")})
    private List<Order> orders;

    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "product")
    private List<Order_productsDetail> order_productDetails;

    public Product(){}
    public Product(ProductInsertRequest request){
        this.setPname(request.getPname());
        this.setPclass(request.getPclass());
        this.setPquantity(request.getPquantity());
        this.setPsort(request.getPsort());
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public String getPclass() {
        return pclass;
    }

    public void setPclass(String pclass) {
        this.pclass = pclass;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPquantity() {
        return pquantity;
    }

    public void setPquantity(Integer pquantity) {
        this.pquantity = pquantity;
    }

    public Integer getPsort() {
        return psort;
    }

    public void setPsort(Integer psort) {
        this.psort = psort;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order_productsDetail> getOrder_productDetails() {
        return order_productDetails;
    }

    public void setOrder_productDetails(List<Order_productsDetail> order_productDetails) {
        this.order_productDetails = order_productDetails;
    }
}
