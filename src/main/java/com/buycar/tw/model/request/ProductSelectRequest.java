package com.buycar.tw.model.request;

public class ProductSelectRequest {
    private String keyword;
    private Integer psort;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPsort() {
        return psort;
    }

    public void setPsort(Integer psort) {
        this.psort = psort;
    }

    @Override
    public String toString() {
        return "ProductSelectRequest{" +
                "keyword='" + keyword + '\'' +
                ", psort=" + psort +
                '}';
    }
}
