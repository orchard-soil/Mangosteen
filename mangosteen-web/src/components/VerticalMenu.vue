<template>
    <div class="vertical-menu">
        <a-menu
            style="width: 256px"
            :defaultSelectedKeys="['1']"
            mode="inline"
        >
			<a-menu-item key="home" class="home-title">
				<a href="" target="_blank" rel="noopener noreferrer">山竹</a>
			</a-menu-item>
			<a-sub-menu key="sub1">
				<span slot="title"><a-icon type="branches" /><span>组织机构</span></span>
				<a-menu-item-group key="g1">
					<template slot="title"><span>Item 1</span></template>
					<a-menu-item key="1">Option 1</a-menu-item>
					<a-menu-item key="2">Option 2</a-menu-item>
				</a-menu-item-group>
				<a-menu-item-group key="g2" title="Item 2">
					<a-menu-item key="3">Option 3</a-menu-item>
					<a-menu-item key="4">Option 4</a-menu-item>
				</a-menu-item-group>
			</a-sub-menu>
			<a-sub-menu key="sub2">
				<span slot="title"><a-icon type="team" /><span>用户管理</span></span>
				<a-menu-item key="5">Option 5</a-menu-item>
				<a-menu-item key="6">Option 6</a-menu-item>
				<a-sub-menu key="sub3" title="Submenu">
					<a-menu-item key="7">Option 7</a-menu-item>
					<a-menu-item key="8">Option 8</a-menu-item>
				</a-sub-menu>
			</a-sub-menu>
			<a-sub-menu key="sub4">
				<span slot="title"><a-icon type="safety" /><span>权限管理</span></span>
				<a-menu-item key="5">Option 5</a-menu-item>
				<a-menu-item key="6">Option 6</a-menu-item>
				<a-sub-menu key="sub5" title="Submenu">
					<a-menu-item key="7">Option 7</a-menu-item>
					<a-menu-item key="8">Option 8</a-menu-item>
				</a-sub-menu>
			</a-sub-menu>
			<a-sub-menu key="sub6">
				<span slot="title"><a-icon type="setting" /><span>设置</span></span>
				<a-menu-item key="9">Option 9</a-menu-item>
				<a-menu-item key="10">Option 10</a-menu-item>
				<a-menu-item key="11">Option 11</a-menu-item>
				<a-menu-item key="12">Option 12</a-menu-item>
			</a-sub-menu>
  		</a-menu>
    </div>
</template>

<script>
export default {
    name: "VerticalMenu",
}
</script>

<style lang="stylus" scoped>
.home-title
	font-size 16px
	font-weight bold
</style>