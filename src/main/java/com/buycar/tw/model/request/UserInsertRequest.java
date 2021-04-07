package com.buycar.tw.model.request;

import javax.validation.constraints.NotBlank;

public class UserInsertRequest {
    @NotBlank(message = "請輸入使用者名稱")
    private String uname;
    @NotBlank(message = "請輸入密碼")
    private String password;
    private Integer isadmin;
    private Integer coin;


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
