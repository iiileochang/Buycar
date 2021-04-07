package com.buycar.tw.service;

import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.request.ProductInsertRequest;
import com.buycar.tw.model.response.ProductInsertResponse;
import com.buycar.tw.model.response.ProductSelectResponse;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductSelectResponse> findAllByPnameLike(String keyword);
    void reSort(String newSort);
    ProductInsertResponse insertProduct(ProductInsertRequest request);
    String delProduct(Integer[] delItem);
    ProductSelectResponse findByPsort(Integer psort);
    Optional<Product> findByPid(Integer pid);
    ProductInsertResponse updateProduct(ProductInsertRequest request);
    void setProductList(Model model, String keyword, String errMsg,String icon);
    void setErrMsg(Model model,String errMsg,String icon);
}
