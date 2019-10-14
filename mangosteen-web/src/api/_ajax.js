/**
 * 简单封装 axios
 */

import axios from 'axios'
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

export default function _ajax(url = '', param = {}, type='GET'){
    // 定义 promise 对象
    let promise;
    return new Promise((resolve, reject)=>{
        // 判断请求的方式
        if('GET' === type){
            // 拼接 GET 请求字符串
            let paramStr = '';
            Object.keys(param).forEach(key=>{
                paramStr += key + '=' +param[key] + '&';
            })

            // 过滤最后的 &
            if(paramStr !== ''){
                paramStr = paramStr.substr(0, paramStr.lastIndexOf('&'));
            }

            // 完整路径
            if(paramStr !== ''){
                url += '?' + paramStr;
            }

            // 发送 GET 请求
            promise = axios.get(url);
        }else if('POST' === type){
            promise = axios.post(url, param);
        }

        // 返回请求的结果
        promise.then((response)=>{
            resolve(response.data);
        }).catch(error=>{
            reject(error);
        })
    })
}