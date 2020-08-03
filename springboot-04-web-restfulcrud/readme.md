#SpringBoot对静态资源的映射规则:
##1、所有 /webjars/** ，都去 classpath:/META-INF/resources/webjars/ 找资源；

##2、"/**" 访问当前项目的任何资源，都去（静态资源的文件夹）找映射
```
    按以下顺序查找对应的资源，如果找到对应的资源将不再向下查找。优先级由高到低进行查找。
    "classpath:/META-INF/resources/", 
    "classpath:/resources/",
    "classpath:/static/", 
    "classpath:/public/" 
    "/"：当前项目的根路径
```
#3、欢迎页； 静态资源文件夹下的所有index.html页面；被"/**"映射
***加载顺序同上***
```
    按以下顺序查找对应的资源，如果找到对应的资源将不再向下查找。优先级由高到低进行查找。
    "classpath:/META-INF/resources/index.html", 
    "classpath:/resources/index.html",
    "classpath:/static/index.html", 
    "classpath:/public/index.html" 
    "/index.html"：当前项目的根路径
```
#4、所有的 **/favicon.ico  都是在静态资源文件下找
***加载顺序同上***