package com.buycar.tw.controller;

import com.buycar.tw.model.request.UserInsertRequest;
import com.buycar.tw.model.request.UserLoginRequest;
import com.buycar.tw.model.response.UserInsertResponse;
import com.buycar.tw.model.response.UserLoginResponse;
import com.buycar.tw.repository.UserRepository;
import com.buycar.tw.service.ProductService;
import com.buycar.tw.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Controller
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    ProductService productService;
    @Autowired
    UserRepository repository;


    Logger logger = LogManager.getLogger(getClass());

    @RequestMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/BackToProductAdmin")
    public String BackToProductAdmin(Model model, HttpSession session) {
        productService.setProductList(model, "", null,null);
        if(session.getAttribute("uname")!=null){
            if((Integer)session.getAttribute("isAdmin")==1)return "product";
            return "productAdmin";
        }
        return null;
    }

    @PostMapping("/Login")
    public String Login(UserLoginRequest userLoginRequest , Model model, HttpSession session) {
        if (userLoginRequest.getUname().equals("")||userLoginRequest.getUname().isEmpty()){
            model.addAttribute("ErrMsg","帳號不可為空");
            return "index";
        }if (userLoginRequest.getPassword().equals("")||userLoginRequest.getPassword().isEmpty()){
            model.addAttribute("ErrMsg","密碼不可為空");
            return "index";
        }
        session.setAttribute("cartTotalPrice",0);
        UserLoginResponse response=service.login(userLoginRequest);
        if(response.getErrMsg()!=null){
            model.addAttribute("ErrMsg",response.getErrMsg());
            return "index";
        }
        productService.setProductList(model, "", null,null);
        session.setAttribute("uname",response.getUname());
        session.setAttribute("isAdmin",response.getIsadmin());
        session.setAttribute("coin",response.getCoin());
        logger.info(new Date()+ "  Account: "+response.getUname()+" Login Success");
        if(response.getUname().equals("test"))return "productBackup";
        if(response.getIsadmin()==1)return "product";
        return "productAdmin";
    }
    @GetMapping("/logoutController")
    public String Logout(Model model, HttpSession session) {
        logger.info(new Date()+ "  Account: "+session.getAttribute("uname")+" Logout Success");
        session.invalidate();
        return "index";
    }
    @GetMapping("/addUsers")
    public String addUsers(Model model, HttpSession session,@Valid UserInsertRequest userInsertRequest) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        userInsertRequest.setPassword(passwordEncoder.encode(userInsertRequest.getPassword()));
        UserInsertResponse response=service.addUser(userInsertRequest);
        if(response.getErrMsg()!=null){
            model.addAttribute("ErrMsg",response.getErrMsg());
            return "index";
        }
        model.addAttribute("ErrMsg",response.getUname()+"新增成功");
        return "index";
    }

}