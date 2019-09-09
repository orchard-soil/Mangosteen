package com.orchardsoil.mangosteenserver.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_role_menu")
@Data
public class RoleMenu implements Serializable {

  private static final long serialVersionUID = 7730696628258044059L;
  private Long roleId;

  private Long menuId;
}