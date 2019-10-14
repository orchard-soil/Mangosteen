import Vue from 'vue'
import App from './App.vue'

import router from './router/index';

import { Menu, Icon, message} from 'ant-design-vue';
Vue.use(Menu, message);
Vue.component(Icon.name, Icon)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
