package com.alibaba.springboot.config;

import com.alibaba.springboot.component.LoginHandlerInterceptor;
import com.alibaba.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMVCConfigureAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc //不要接管SpringMVC
@Configuration
public class MyMVCConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        //发送 /atalibaba 请求发送到success.html页面
        registry.addViewController("/atalibaba").setViewName("success");
    }

    //所有的WebMvcConfigurerAdapter组件都会使用
    @Bean   //将组件注册到容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return adapter;
    }

    //将国际化LocaleResolver装载进spring容器
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = null;
        //super.addInterceptors(registry);
        //静态资源 css/js/image     springboot已经做好静态资源映射了，无需进行处理
        //方式一：拦截器中没有依赖注入时使用该方式
        //registration = registry.addInterceptor(new LoginHandlerInterceptor());

        //方式二：拦截器中没有依赖注入时使用该方式
//        registration = registry.addInterceptor(handlerLoginHandlerInterceptorNotAutoWiredQuestion());
//        registration.addPathPatterns("/**").excludePathPatterns("/", "/index.html", "/user/login", "/user/logout","/kindeditor/upload");
    }

    /**
     * 解决拦截器中不能依赖注入的问题
     *
     * @return
     */
    @Bean
    public LoginHandlerInterceptor handlerLoginHandlerInterceptorNotAutoWiredQuestion() {
        return new LoginHandlerInterceptor();
    }
}
