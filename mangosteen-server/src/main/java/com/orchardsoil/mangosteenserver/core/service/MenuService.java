package com.orchardsoil.mangosteenserver.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.orchardsoil.mangosteenserver.core.model.Menu;

public interface MenuService extends IService<Menu> {
  void creatMenu(Menu menu);
}
