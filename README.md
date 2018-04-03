OkHttpTools
===========================

|Author|lifuzhe|
|---|---
|E-mail|3307732694@qq.com
|WeChat|lifuzhe15010936604




## OkHttpTools演示
![image](https://github.com/lifuzhe/OkHttpTools/blob/master/OkHttpTools_Demo/gif/demo.gif)


## OkHttpTools简介
----------
一个对OkHttp封装的简单易用型HTTP请求和文件下载管理框架
* 简化OkHttp使用
* 支持GET,POST,PUT,DELETE,HEAD,PATCH
* 支持文件下载
* 支持返回bean对象
* 支持返回json String数据
* 支持返回JsonObject对象
* 支持https请求
* 支持文件上传
* 其他

使用方法
----------
将HttpTools作为依赖库导入Eclipse工程或者Studio工程
```
fastjson-1.1.46.android.jar
okhttp-3.2.0.jar
OkHttpTools.jar
okio-1.6.0.jar
ToolsFinal-1.1.5-release.jar
```
在你的Application中初始化OkHttpTools
```
OkHttpApp.init();
```

Method of use
--------
Here is a description of the use method.
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
} 
```

Thanks
--------
* [Other items 1](http://www.baidu.com)
* [Other items 2](http://www.baidu.com)

