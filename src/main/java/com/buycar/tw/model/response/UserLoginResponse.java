package com.buycar.tw.model.response;

import com.buycar.tw.model.entity.User;

public class UserLoginResponse {
    private String uname;
    private Integer isadmin;
    private String ErrMsg;
    private Integer coin;

    public UserLoginResponse(){}
    public UserLoginResponse(String errMsg){
        this.setErrMsg(errMsg);
    }
    public UserLoginResponse(User user,String errMsg){
        this.uname=user.getUname();
        this.isadmin=user.getIsadmin();
        this.coin=user.getCoin();
        this.ErrMsg=errMsg;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }
}
