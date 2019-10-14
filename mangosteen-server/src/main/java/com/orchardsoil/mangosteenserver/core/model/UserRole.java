package com.orchardsoil.mangosteenserver.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_user_role")
@Data
public class UserRole implements Serializable {


  private static final long serialVersionUID = 6421790959863521309L;
  private Long userId;

  private Long roleId;

}