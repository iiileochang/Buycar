package com.buycar.tw.service.Impl;

import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.entity.ProductDetail;
import com.buycar.tw.model.request.ProductInsertRequest;
import com.buycar.tw.model.response.ProductInsertResponse;
import com.buycar.tw.model.response.ProductSelectResponse;
import com.buycar.tw.repository.ProductRepository;
import com.buycar.tw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;

    @Override
    public List<ProductSelectResponse> findAllByPnameLike(String keyword) {
        List<ProductSelectResponse> selectResponseList = new ArrayList<>();
        List<Product> byPnameLike = repository.findByPnameLikeOrderByPsortAsc("%"+keyword+"%");
        if (byPnameLike == null || byPnameLike.isEmpty()) {
            return null;
        }
        for (Product p :
                byPnameLike) {
            selectResponseList.add(new ProductSelectResponse(p));
        }
        return selectResponseList;
    }

    @Override
    public void reSort(String newSort) {
        String[] AfterSplit = newSort.split(",");
        List<Product> oldList = repository.findAll(Sort.by(Sort.Direction.ASC,"psort"));
        Integer newI = null;
        for (int oldI = 0; oldI < oldList.size(); oldI++) {
            newI = Integer.parseInt(AfterSplit[oldI]);

            Product sortedProduct = oldList.get(newI);
            if (sortedProduct.getPsort() != oldI) {
                sortedProduct.setPsort(oldI);
                repository.save(sortedProduct);
            }
        }
    }

    @Override
    public ProductInsertResponse insertProduct(ProductInsertRequest request) {
        request.setPsort(repository.findAll().size());
        Product product = new Product(request);
        product.setProductDetail(new ProductDetail(request));

        repository.save(product);
        return new ProductInsertResponse(product, "InsertSuccess");
    }

    @Override
    public String delProduct(Integer[] keepArray) {
        List<Product> oldList = repository.findAll(Sort.by(Sort.Direction.ASC,"psort"));
        List<Integer> delList = new ArrayList<>();
        for (int i = 0; i < oldList.size(); i++) {
            delList.add(i);
        }
        delList.removeAll(Arrays.asList(keepArray));
        for (Integer i:
             delList) {
            repository.deleteByPsort(i);
        }
        List<Product> newList = repository.findAll(Sort.by(Sort.Direction.ASC,"psort"));
        int indexP = 0;
        for (Product p:
             newList) {
            p.setPsort(indexP);
            indexP+=1;
            repository.save(p);
        }
        return null;
    }

    @Override
    public ProductSelectResponse findByPsort(Integer psort) {
        return new ProductSelectResponse(repository.findByPsort(psort));
    }

    @Override
    public Optional<Product> findByPid(Integer pid){
        return repository.findById(pid);
    }
    @Override
    public ProductInsertResponse updateProduct(ProductInsertRequest request) {
        Product product = repository.findByPsort(request.getPsort());
        product.setPname(request.getPname());
        product.setPclass(request.getPclass());
        product.setPquantity(request.getPquantity());
        product.getProductDetail().setPprice(request.getPprice());
        product.getProductDetail().setPdetail(request.getPdetail());
        repository.save(product);
        return new ProductInsertResponse("OK");
    }

    @Override
    public void setProductList(Model model, String keyword, String errMsg,String icon) {
        List<ProductSelectResponse> productSelectResponses = findAllByPnameLike(keyword);
        model.addAttribute("productList", productSelectResponses);
        if (errMsg != null) model.addAttribute("errMsg", errMsg);
        icon=(icon==null)?"info":icon;
        model.addAttribute("iconType",icon);
    }

    @Override
    public void setErrMsg(Model model, String errMsg, String icon) {
        if (errMsg != null) model.addAttribute("errMsg", errMsg);
        icon=(icon==null)?"info":icon;
        model.addAttribute("iconType",icon);
    }


}
