package com.alibaba.springbootconfig.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 *
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 *          prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
 * 只有这个组件是容器中的组件，才能使用容器提供的@ConfiguationProperties功能
 */
@Component
@ConfigurationProperties(prefix = "person")
//@Validated
@PropertySource(value = {"classpath:person.properties"})
public class Person {
    /**
     * <bean id="person" class="com.alibaba.springbootconfig.bean.person">
     *         <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}"></>property
     *  </bean>
     */

//    @Value("${person.lastName}")
//    @Email
    private String lastName;

//    @Value("#{11*2}")
    private int age;

//    @Value("true")
    private boolean bosss;

    private Date birth;
    private Map<String, Object> maps;
    private List<String> lists;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", bosss=" + bosss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBosss() {
        return bosss;
    }

    public void setBosss(boolean bosss) {
        this.bosss = bosss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<String> getLists() {
        return lists;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

}
