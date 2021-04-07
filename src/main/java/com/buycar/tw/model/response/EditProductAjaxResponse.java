package com.buycar.tw.model.response;

public class EditProductAjaxResponse {
    private String pclass;
    private String pname;
    private Integer pquantity;
    private String pdetail;
    private Integer pprice;
    private Integer psort;


    public EditProductAjaxResponse(){}
    public EditProductAjaxResponse(ProductSelectResponse productSelectResponse){
        this.pclass=productSelectResponse.getPclass();
        this.pname=productSelectResponse.getPname();
        this.pquantity=productSelectResponse.getPquantity();
        this.pdetail=productSelectResponse.getPdetail();
        this.pprice=productSelectResponse.getPprice();
        this.psort= productSelectResponse.getPsort();
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

    public Integer getPsort() {
        return psort;
    }

    public void setPsort(Integer psort) {
        this.psort = psort;
    }
}
