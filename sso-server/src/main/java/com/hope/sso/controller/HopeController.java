package com.hope.sso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class HopeController {

    @GetMapping("show")
    public String show(){

        return "hello cas customer";
    }

}