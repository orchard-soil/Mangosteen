import Vue from 'vue'
import App from './App.vue'
import { Icon } from 'ant-design-vue';

Vue.component(Icon.name, Icon)

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
