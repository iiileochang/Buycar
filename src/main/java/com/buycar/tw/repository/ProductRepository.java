package com.buycar.tw.repository;

import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//JPQL LIKE寫法(呼叫不須加上% %)
//    @Query(value = "select p from Product p where p.pname like %?1% order by p.psort")
//    List<Product> findAllByPnameLike(String keyword);

    List<Product> findByPnameLikeOrderByPsortAsc(String Keyword);


    List<Product> findAll();

    @Modifying
    @Transactional
    void deleteByPsort(Integer i);

    Product findByPsort(Integer psort);
}
