package com.buycar.tw.model.request;

import javax.validation.constraints.NotBlank;

public class UserLoginRequest {
    @NotBlank(message = "請輸入使用者名稱")
    private String uname;
    @NotBlank(message = "請輸入密碼")
    private String password;

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

    @Override
    public String toString() {
        return "UserInsertRequest{" +
                "uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
