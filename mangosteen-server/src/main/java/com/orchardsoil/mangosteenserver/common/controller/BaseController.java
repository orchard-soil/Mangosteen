package com.orchardsoil.mangosteenserver.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.orchardsoil.mangosteenserver.core.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;


public class BaseController {
  protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
    Map<String, Object> rspData = new HashMap<>();
    rspData.put("rows", pageInfo.getRecords());
    rspData.put("total", pageInfo.getTotal());
    return rspData;
  }
}
