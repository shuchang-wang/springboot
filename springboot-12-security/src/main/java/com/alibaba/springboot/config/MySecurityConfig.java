package com.alibaba.springboot.config;

        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 1、引入SpringSecurity
 * 2、编写SpringSecurity的配置类：
 * 配置类需要继承自 XXSecurityConfig extends WebSecurityConfigurerAdapter,且在配置类上添加注解@EnableWebSecurity
 * 3、控制请求的访问权限：
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能，效果：如果没有登录，没有权限就会来到登录页面
        //1、/login来到登录页
        //2、重定向到/login?error表示登录失败
        //3、更多详细规则
        //4、默认post形式的 /login代表处理登录
        //5、一旦定制loginPage；nameloginPage的post请求就是登录
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");


        //开启自动配置的注销功能
        //1、访问 /logout 表示用户注销，清空session
        //2、注销成功返回 /login?logout页面
        //http.logout();
        http.logout().logoutSuccessUrl("/"); //注销成功之后返回首页

        //开启记住我功能
        //登录成功以后，将cookie发送给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie
        http.rememberMe().rememberMeParameter("remember");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.inMemoryAuthentication().withUser("zhangsan").password("123456").roles("VIP1")
                .and()
                .withUser("lisi").password("123456").roles("VIP1", "VIP2")
                .and()
                .withUser("wangwu").password("123456").roles("VIP1", "VIP2", "VIP3");
    }
}