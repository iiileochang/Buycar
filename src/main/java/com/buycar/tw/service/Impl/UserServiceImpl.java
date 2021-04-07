package com.buycar.tw.service.Impl;

import com.buycar.tw.model.entity.User;
import com.buycar.tw.model.request.UserInsertRequest;
import com.buycar.tw.model.request.UserLoginRequest;
import com.buycar.tw.model.response.UserInsertResponse;
import com.buycar.tw.model.response.UserLoginResponse;
import com.buycar.tw.repository.UserRepository;
import com.buycar.tw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        User user = repository.findByUname(request.getUname());
        if (user != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(request.getPassword(),user.getPassword())){
                return new UserLoginResponse(user, null);
               }
            return new UserLoginResponse(user, "密碼錯誤");
        }
        return new UserLoginResponse("使用者不存在");
    }

    @Override
    public void setCoin(User user, Integer totalPrice) {
        user.setCoin(user.getCoin() - totalPrice);
        repository.save(user);
    }

    @Override
    public UserInsertResponse addUser(UserInsertRequest userInsertRequest) {
        User oldUser = repository.findByUname(userInsertRequest.getUname());
        if (oldUser == null) {
            User user = new User(userInsertRequest);
            return new UserInsertResponse(repository.save(user),"新增成功");
        }
        return new UserInsertResponse("使用者已存在，請更換另一使用名稱");
    }
}
