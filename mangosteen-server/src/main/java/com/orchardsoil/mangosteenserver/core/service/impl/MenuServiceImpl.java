package com.orchardsoil.mangosteenserver.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orchardsoil.mangosteenserver.core.mapper.MenuMapper;
import com.orchardsoil.mangosteenserver.core.model.Menu;
import com.orchardsoil.mangosteenserver.core.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("MenuService")
public class MenuServiceImpl  extends ServiceImpl<MenuMapper, Menu> implements MenuService {
  @Override
  public void creatMenu(Menu menu) {
    // 创建时间
    menu.setCreateTime(new Date());
    // 修改时间
    menu.setModifyTime(new Date());
    //
    this.save(menu);
  }
}
