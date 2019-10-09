<template>
    <div class="login">
        <div class="login-form">
            <h1>登录</h1>
            <div class="form-item">
                <span>用户名</span>
                <div class="form-input">
                    <icon-font type="mgs-user" style="font-size:18px;" />
                    <input type="text" name="userName" placeholder="请输入用户名" />
                </div>
            </div>
            <div class="form-item">
                <span>密码</span>
                <div class="form-input">
                    <icon-font type="mgs-password" style="font-size:18px;" />
                    <input type="text" name="userName" placeholder="请输入密码" />
                </div>
            </div>
            <div class="form-item">
                <span>验证码</span>
                <div class="form-input">
                    <!-- <icon-font type="mgs-password" style="font-size:18px;" /> -->
                    <input type="text" name="userName" placeholder="请输入验证码" />
                    <span class="vCode">1234</span>
                </div>
            </div>
            <button @click="login">登录</button>
        </div>
    </div>
</template>

<script>
import { Icon } from 'ant-design-vue'
const IconFont = Icon.createFromIconfontCN({
  //scriptUrl: '//at.alicdn.com/t/font_1414048_exhj1dzmsrg.js',
  scriptUrl: '//at.alicdn.com/t/font_1414048_h8oxqo8vuxg.js'
})
export default {
    name: "Login",
    components: {
        IconFont,
    },
    methods: {
        // 登录方法
        login(){
            // 判断 用户名 和 密码 | 逻辑判断

            // 调转
            //console.log('登录成功');
            this.$router.push('/admin');  // 跳转
        }
    }
}
</script>

<style lang="stylus" scoped>
.login
    position absolute
    width 100%
    height 100%
    background-color #ccc
    background-image linear-gradient(to right, #12c2e9, #c471ed, #f64f59)
    display flex
    justify-content center
    align-items center
    .login-form
        display flex
        flex-direction column
        width 500px
        height 600px
        background-color #fff
        align-items center
        border-radius 10px;
        h1
            font-size 39px
            //font-weight bolder
            margin 20px
        .form-item
            display flex
            flex-direction column
            width 80%
            margin-bottom 20px
            span
                font-size 14px
                font-weight bolder
                margin 15px 0px
            input
                border none
            .form-input
                display flex
                align-items center
                border-bottom 1px solid #ADADAF
                padding 0px 3px
                input
                    margin-left 10px
                    outline none
                    width 100%
                .vCode
                    margin 0
        button
            width 80%
            margin-top 20px
            background-image linear-gradient(to right, #12c2e9, #c471ed, #f64f59)
            background-size auto 200%
            background-position 0 100% 
            transition 0.5s
            border none
            outline none
            height 40px
            border-radius 5px
            color #fff
        button:hover
            background-position 0 0


</style>