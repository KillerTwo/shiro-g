package org.lwt.entity;

import javax.persistence.*;

import org.lwt.typehandler.PasswordTypeHandler;

import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "`users`")
public class Users {
    @Id
    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`username`")
    private String username;

    @Column(name = "`password`")
    @ColumnType(typeHandler=PasswordTypeHandler.class)
    private String password;

    @Column(name = "`salt`")
    private String salt;

    @Column(name = "`isLock`")
    private Integer islock;

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * @return isLock
     */
    public Integer getIslock() {
        return islock;
    }

    /**
     * @param islock
     */
    public void setIslock(Integer islock) {
        this.islock = islock;
    }
}