import Vue from 'vue'
import Vuex from 'vuex'

import state from './store'
import actions from './actions'
import mutations from './mutations'

// 使用 Vuex
Vue.use(Vuex);

export default new Vuex.Store({
    state,
    actions,
    mutations
})