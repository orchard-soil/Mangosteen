package com.orchardsoil.mangosteenserver.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orchardsoil.mangosteenserver.core.model.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
  void creatMenu(Menu menu);

  List<Menu> findUserPermissions(String username);
}
