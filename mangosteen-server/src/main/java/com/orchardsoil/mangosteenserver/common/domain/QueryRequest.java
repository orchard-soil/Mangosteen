package com.orchardsoil.mangosteenserver.common.domain;

import java.io.Serializable;

public class QueryRequest  implements Serializable {
  private static final long serialVersionUID = -2664993486649673661L;
  private int pageSize = 10;
  private int pageNum = 1;

  private String sortField;
  private String sortOrder;
}
