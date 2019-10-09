import Vue from 'vue'
import App from './App.vue'

import router from './router/index';

import { Menu } from 'ant-design-vue';
Vue.use(Menu);

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
