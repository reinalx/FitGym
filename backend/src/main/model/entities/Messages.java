
package main.model.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//ESTO SE HARA EN UN FUTURO; NO PRIORITARIO
@Entity
public class Messages {
    public long id;
    public String msg;
    public Date date;
    public User toUser;
    public User fromUser;

    public Messages() {}

    public Messages(long id, String msg, Date date, User toUser, User fromUser) {
        this.id = id;
        this.msg = msg;
        this.date = date;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
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
    public User getToUser() {
        return toUser;
    }
    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
    public User getFromUser() {
        return fromUser;
    }
    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}