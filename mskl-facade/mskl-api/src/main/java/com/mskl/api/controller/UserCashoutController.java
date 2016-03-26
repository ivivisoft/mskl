package com.mskl.api.controller;

import com.mskl.service.usercashout.UserCashoutService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userCashout")
public class UserCashoutController {

    @Resource(name = "userCashout.userCashoutService")
    private UserCashoutService userCashoutService;


}
