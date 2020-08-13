package com.alibaba.springboot.entities;

import javax.persistence.*;

/**
 * 使用JPA注解配置映射关系
 *
 * @Entity注解：告诉JPA这是一个实体类（和数据表映射的类）
 * @Table注解：用来指定和哪个数据表对应；如果省略默认表名就是实体类名且首字母小写（user）
 */
@Entity
@Table(name = "tbl_user")
public class User {

    /**
     * @Id注解：表示这是一个主键属性
     * @GeneratedValue(strategy = GenerationType.IDENTITY)：自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * @Column(name = "last_name"):表示这是和数据表对应的一个列
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * @Column：省略默认列名就是属性名
     */
    @Column
    private String email;

    public User() {
    }

    public User(Integer id, String lastName, String email) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null) return false;
        return getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
