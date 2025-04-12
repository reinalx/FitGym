
package com.fitGym.backend.model.entities;

import jakarta.persistence.*;

import java.util.Date;


//ESTO SE HARA EN UN FUTURO; NO PRIORITARIO
@Entity
public class Messages {
    public Long id;
    public String msg;
    public Date date;
    public User toUser;
    public User fromUser;

    public Messages() {}

    public Messages( String msg, Date date, User toUser, User fromUser) {
        this.msg = msg;
        this.date = date;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @ManyToOne
    @JoinColumn(name = "toUserId")
    public User getToUser() {
        return toUser;
    }
    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    @ManyToOne
    @JoinColumn(name = "fromUserId")
    public User getFromUser() {
        return fromUser;
    }
    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}