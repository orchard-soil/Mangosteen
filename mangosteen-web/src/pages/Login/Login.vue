<template>
    <div class="login">
        <div class="login-form">
            <h1>{{ h1Title }}</h1>
            <div class="form-item">
                <span>用户名</span>
                <div class="form-input">
                    <icon-font type="mgs-user" style="font-size:18px;" />
                    <input type="text" name="userName" v-model="userName" placeholder="请输入用户名" />
                </div>
            </div>
            <div class="form-item">
                <span>密码</span>
                <div class="form-input">
                    <icon-font type="mgs-password" style="font-size:18px;" />
                    <input type="text" name="userName" v-model="passWord" placeholder="请输入密码" />
                </div>
            </div>
            <div class="form-item" v-show="isLogin">
                <span>验证码</span>
                <div class="form-input">
                    <a-icon type="safety-certificate" style="font-size:18px;" />
                    <input type="text" name="vCode" v-model="vCode"  placeholder="请输入验证码" />
                    <img ref="vcode" @click="getVCode" src="http://localhost:8088/images/captcha" alt="" height="30">
                </div>
            </div>
            <button @click="login" v-show="isLogin">登录</button>
            <button @click="regist" v-show="!isLogin">注册</button>
            <a @click="switchToLogin">{{ aTitle }}</a>
        </div>
    </div>
</template>

<script>
import { Icon, message } from 'ant-design-vue'
const IconFont = Icon.createFromIconfontCN({
  //scriptUrl: '//at.alicdn.com/t/font_1414048_exhj1dzmsrg.js',
  scriptUrl: '//at.alicdn.com/t/font_1414048_h8oxqo8vuxg.js'
})
import { login, regist } from './../../api/index'
import { mapActions } from 'vuex'

export default {
    name: "Login",
    components: {
        IconFont,
    },
    data(){
        return {
            userName: '', // 用户名
            passWord: '', // 密码
            vCode: '', // 验证码
            isLogin: true, // 登录或者注册
            h1Title: "登录",
            aTitle: "去注册",
            userInfo: {} // 用户信息
        }
    },
    methods: {
        ...mapActions(['saveUserInfo']),
        // 获取 验证码
        getVCode(){
            this.$refs.vcode.src = 'http://localhost:8088/images/captcha?time=' + new Date();
        },

        // 登录
        async login(){
            this._verify();
            // 请求数据
            let params = new URLSearchParams();
            params.append('username', this.userName);       //你要传给后台的参数值 key/value
            params.append('password', this.passWord);
            params.append('vrifyCode', this.vCode);

            let result = await login(params);
            console.log(result);
            if(result.code !== 200){
                message.error(result.message);
                return;
            }
            // 调转
            console.log('登录成功');
            this.userInfo = result.data.user;

            this.saveUserInfo(this.userInfo);

            this.$router.push('/admin');  // 跳转
        },

        // 注册
        async regist(){
            this._verify();
            // 请求数据
            let params = new URLSearchParams();
            params.append('username', this.userName);       //你要传给后台的参数值 key/value
            params.append('password', this.passWord);
            params.append('vrifyCode', this.vCode);

            let result = await regist(params);
            console.log(result);
            if(result.code !== 200){
                message.error(result.message);
                return;
            }
            // 跳转
            console.log('登录成功');
            this.saveUserInfo(result.data.user);

            this.$router.push('/admin');  // 跳转
        },

        _verify(){
            // 判断 用户名 和 密码 | 逻辑判断
            if(this.userName === ''){
                message.error('用户名不能为空');
                return;
            }

            if(this.passWord === ''){
                message.error('密码不能为空');
                return;
            }

            if(this.isLogin && this.vCode === ''){
                message.error('请输入验证码');
                return;
            }
        },

        // 注册 登录 切换
        switchToLogin(){
            this.isLogin = !this.isLogin;
            if(this.h1Title === "登录"){
                this.h1Title = "注册";
                this.aTitle = "去登录"
            }else{
                this.h1Title = "登录";
                this.aTitle = "去注册";
            }
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
                img
                    margin 0 -3px -1px 0
        button
            width 80%
            margin 20px 0 10px
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