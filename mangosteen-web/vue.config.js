module.exports = {
    devServer: {
        proxy: 'http://localhost:8088' // 解决跨域
    },
    css: {
        loaderOptions: { // 向 CSS 相关的 loader 传递选项
            less: {
                javascriptEnabled: true
            }
        }
    }
}