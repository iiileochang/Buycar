package com.buycar.tw.model.entity;

import com.buycar.tw.model.request.ProductInsertRequest;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "productdetail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dno;
    @OneToOne(mappedBy = "productDetail")
    private Product product;
    private String pdetail;
    private Integer pprice;
    private Date pdate;

    public ProductDetail(){}
    public ProductDetail(ProductInsertRequest request){
        this.setPdate(new Date());
        this.setPdetail(request.getPdetail());
        this.setPprice(request.getPprice());
    }
    public int getDno() {
        return dno;
    }

    public void setDno(int dno) {
        this.dno = dno;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPdetail() {
        return pdetail;
    }

    public void setPdetail(String pdetail) {
        this.pdetail = pdetail;
    }

    public Integer getPprice() {
        return pprice;
    }

    public void setPprice(Integer pprice) {
        this.pprice = pprice;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }
}
