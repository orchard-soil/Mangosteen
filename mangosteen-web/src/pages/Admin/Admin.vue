<template>
    <div>
        <div class="admin" v-if="userInfo.userId">
            <div class="vertical-menu">
                <VerticalMenu />
            </div>
            <div class="horizontal-menu">
                <HorizontalMenu /> 
            </div>
        </div>

        <Login v-else />
    </div>
</template>

<script>
import HorizontalMenu from '../../components/HorizontalMenu'
import VerticalMenu from '../../components/VerticalMenu'
import Login from './../Login/Login'
import {mapState} from 'vuex'

export default {
    name: "Admin",
    components: {
        HorizontalMenu,
        VerticalMenu,
        Login
    },
    computed:{
        ...mapState(['userInfo'])
    }
}
</script>

<style lang="stylus" scoped>
.admin
    display flex
    flex-direction row
    width 100%
    .horizontal-menu
        width 100%
</style>