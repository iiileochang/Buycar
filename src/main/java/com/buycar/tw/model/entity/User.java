package com.buycar.tw.model.entity;

import com.buycar.tw.model.request.UserInsertRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @OneToMany(cascade = CascadeType.MERGE,mappedBy = "user")
    private List<Order> order;

    private String uname;
    private String password;
    private Integer coin;
    private Integer isadmin;

    public User(){}
    public User(UserInsertRequest userInsertRequest){
        this.setUname(userInsertRequest.getUname());
        this.setPassword(userInsertRequest.getPassword());
        this.setIsadmin(userInsertRequest.getIsadmin());
        this.setCoin(userInsertRequest.getCoin());
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

}