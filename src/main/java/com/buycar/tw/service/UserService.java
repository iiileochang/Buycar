package com.buycar.tw.service;

import com.buycar.tw.model.entity.User;
import com.buycar.tw.model.request.UserInsertRequest;
import com.buycar.tw.model.request.UserLoginRequest;
import com.buycar.tw.model.response.UserInsertResponse;
import com.buycar.tw.model.response.UserLoginResponse;

public interface UserService {
    UserLoginResponse login(UserLoginRequest request);
    void setCoin(User user, Integer totalPrice);
    UserInsertResponse addUser(UserInsertRequest userInsertRequest);
}
