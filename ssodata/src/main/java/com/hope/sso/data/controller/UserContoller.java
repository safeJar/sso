package com.hope.sso.data.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.hope.sso.data.pojo.SsoDataJSONResult;
import com.hope.sso.data.pojo.SsoUser;
import com.hope.sso.data.service.SsoUserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserContoller {

	@Autowired
	private SsoUserService ssoUserService;

	@Autowired
	private Sid sid;

	/**
	 * 检验用户名是否已存在
	 * @param username
	 * @return
     */
	@RequestMapping("validateUsername")
	public SsoDataJSONResult validateUsername(String username){

		SsoUser ssoUser = new SsoUser();
		ssoUser.setUsername(username);
		List<SsoUser> ssoUserList = ssoUserService.getSsoUserList(ssoUser,0,10);
		boolean exists = false;
		if(CollectionUtils.isEmpty(ssoUserList)){
			exists=true;
		}
		return SsoDataJSONResult.ok(exists);
	}


	@PostMapping("register")
	public SsoDataJSONResult register(SsoUser ssoUser){
		ssoUser.setUserId(sid.nextShort());
		ssoUser.setCreateTime(new Date());
		ssoUserService.save(ssoUser);
		return SsoDataJSONResult.ok("success");
	}


	@RequestMapping("/getUserByUserId")
	public SsoUser getUser(String userId) {
		SsoUser ssoUser = ssoUserService.getSsoUserByUserId(userId);
		return ssoUser;
	}
	
	@PostMapping("/getUserList")
	public SsoDataJSONResult getUserList(SsoUser ssoUser,Integer pageNumber,Integer pageSize) {

		List<SsoUser> ssoUserList = ssoUserService.getSsoUserList(ssoUser,pageNumber,pageSize);
		
		return SsoDataJSONResult.ok(ssoUserList);
	}
}
