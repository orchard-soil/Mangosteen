package com.orchardsoil.mangosteenserver.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.orchardsoil.mangosteenserver.common.converter.TimeConverter;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_log")
@Excel("系统日志")
public class Log implements Serializable {

    private static final long serialVersionUID = 8320426505300467175L;
    @TableId(value = "SYS_LOG_ID", type = IdType.AUTO)
    private Integer sysLogId;

    @Size(min = 4, max = 10, message = "{range}")
    @ExcelField(value = "用户名")
    private String username;

    // 用户操作
    @ExcelField(value = "用户操作")
    private String operation;

    // 响应时间
    @ExcelField(value = "响应时间")
    private String time;

    // 方法、
    @ExcelField(value = "方法")
    private String method;

    //请求参数

    private String params;

    // IP地址
    @ExcelField(value = "IP地址")
    private String ip;
    // IP地址
    @ExcelField(value = "IP地址")
    private String location;
    // 创建时间
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;
}
