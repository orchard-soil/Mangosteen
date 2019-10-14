import {
    SAVE_USER_INFO
} from './mutation-types'

export default{
    [SAVE_USER_INFO](state, {userInfo}){
        state.userInfo = userInfo;
    }
}