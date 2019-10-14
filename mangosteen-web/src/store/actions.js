import {
    SAVE_USER_INFO
} from './mutation-types'

export default{

    // 保存用户信息
    saveUserInfo({commit}, userInfo){
        commit(SAVE_USER_INFO, {userInfo});
    }
}