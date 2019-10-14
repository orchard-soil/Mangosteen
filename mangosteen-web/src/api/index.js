/**
 * 和 后台 交互
 */

import _ajax from './_ajax'

// 登录
export const login = (params)=>_ajax('/login', params, 'POST');

// 注册
export const regist = (params)=>_ajax('/regist', params, 'POST');

// 保存用户信息


// 获取用户信息