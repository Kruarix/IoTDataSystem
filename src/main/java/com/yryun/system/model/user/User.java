package com.yryun.system.model.user;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;



@Accessors(chain = true)    //这个注解告诉 Lombok 自动生成支持链式调用的 setter 方法。它允许你在一个语句中连续调用多个 setter 方法。
@TableName("sys_user")      //这个注解指定了数据库表名，这对于与数据库交互的框架（例如 MyBatis-Plus）非常有用。它告诉框架这个实体类对应的数据库表的名称。
@Getter                     //这两个注解分别自动生成属性的 getter 和 setter 方法
@Setter
@FieldNameConstants         //这个注解可以生成一个常量类，包含了类中所有属性的名称作为常量。这在避免硬编码属性名称时很有用。
public class User implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 所属医院
     */
    private String hospital;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 是否已开展
     */
    private char isDevelop;

    /**
     * 开展例数
     */
    private Integer developNum;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户状态（0正常 1停用）
     */
    private char status;

    /**
     * 用户性别（0男，1女，2未知）
     */
    private char sex;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 用户类型（0管理员，1普通用户）
     */
    private char userType;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者ID
     */
    private Long createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者ID
     */
    private Long updateBy;

    /**
     * 删除标志（0 代表未删除，1 代表已删除）
     */
    private char delFlag;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

