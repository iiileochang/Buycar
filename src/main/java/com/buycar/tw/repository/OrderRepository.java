package com.buycar.tw.repository;

import com.buycar.tw.model.entity.Order;
import com.buycar.tw.model.entity.Product;
import com.buycar.tw.model.response.OrderSelectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findAllByOid(Integer oid);

    @Query(value = "select o from Order o where o.user.uname = ?1")
    Page<OrderSelectResponse> findAllByUname(String uname, PageRequest page);

}
