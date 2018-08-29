package com.hope.sso.data.service.impl;

import com.hope.sso.data.mapper.SsoUserMapper;
import com.hope.sso.data.pojo.SsoUser;
import com.hope.sso.data.service.SsoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SsoUserServiceImpl implements SsoUserService {

    @Autowired
    private SsoUserMapper ssoUserMapper;

    @Override
    public int save(SsoUser ssoUser) {
        return ssoUserMapper.insert(ssoUser);
    }

    @Override
    public void update(SsoUser ssoUser) {

    }

    @Override
    public SsoUser getSsoUserByUserId(String userId) {

        return null;
    }

    @Override
    public List<SsoUser> getSsoUserList(SsoUser ssoUser, Integer pageNumber, Integer pageSize) {
        return null;
    }
}