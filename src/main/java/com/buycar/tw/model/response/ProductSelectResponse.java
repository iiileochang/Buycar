package com.buycar.tw.model.response;

import com.buycar.tw.model.entity.Product;

public class ProductSelectResponse {
    private Integer pid;
    private String pclass;
    private String pname;
    private Integer pquantity;
    private String pdetail;
    private Integer pprice;
    private Integer psort;

    private String errMsg;

    public ProductSelectResponse(){}
    public ProductSelectResponse(String errMsg){
        this.setErrMsg(errMsg);
    }
    public ProductSelectResponse(Product product){
        this.pclass=product.getPclass();
        this.pid=product.getPid();
        this.pname=product.getPname();
        this.pprice=product.getProductDetail().getPprice();
        this.pquantity=product.getPquantity();
        this.pdetail=product.getProductDetail().getPdetail();
        this.psort=product.getPsort();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getPsort() {
        return psort;
    }

    public void setPsort(Integer psort) {
        this.psort = psort;
    }
}
