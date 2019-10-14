<template>
    <div class="horizontal-menu">
        <a-menu mode="horizontal" class="a-menu">
            <a-menu-item>
                主页
            </a-menu-item>
            <a-menu-item>
                Navigation Two
            </a-menu-item>
            <a-sub-menu class="mine-a-sub-menu">
                <span slot="title" class="submenu-title-wrapper"><img src="./../assets/控制台.png" alt="" width="30">{{userInfo.username}}</span>
                <a-menu-item-group title="Item 1">
                    <a-menu-item key="setting:1">Option 1</a-menu-item>
                    <a-menu-item key="setting:2">Option 2</a-menu-item>
                </a-menu-item-group>
                <a-menu-item-group title="Item 2">
                    <a-menu-item key="setting:3">Option 3</a-menu-item>
                    <a-menu-item key="setting:4">Option 4</a-menu-item>
                </a-menu-item-group>
            </a-sub-menu>
        </a-menu>
    </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
    name: "HorizontalMenu",
    components: {

    },
    computed: {
        ...mapState(['userInfo'])
    }
}
</script>

<style lang="stylus" scoped>
.a-menu
    display flex
    .mine-a-sub-menu
        position fixed
        right 20px
        img
            margin-right 10px

</style>