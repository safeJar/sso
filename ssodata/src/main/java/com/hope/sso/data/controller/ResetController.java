package com.hope.sso.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 找回密码
 */
@Controller
@RequestMapping("reset")
public class ResetController {

    @GetMapping("/index")
    public String index(){

        return "reset/index";
    }






}