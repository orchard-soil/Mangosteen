import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from './../pages/Login/Login'
import Admin from './../pages/Admin/Admin'

// 2. 声明使用
Vue.use(VueRouter);

// 3. 输出路由对象
export default new VueRouter({
    // 3.1 配置一级路由
    routes: [
        {
            path: '/login',
            component: Login,
        },
        {
            path: '/admin',
            component: Admin
        }
    ]
})

