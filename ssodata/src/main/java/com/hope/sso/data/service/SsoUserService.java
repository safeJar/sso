package com.hope.sso.data.service;

import com.hope.sso.data.pojo.SsoUser;

import java.util.List;

public interface SsoUserService {
    int save(SsoUser ssoUser);
    void update(SsoUser ssoUser);
    SsoUser getSsoUserByUserId(String userId);
    List<SsoUser> getSsoUserList(SsoUser ssoUser,Integer pageNumber,Integer pageSize);


}