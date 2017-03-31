package gyb.henu.robot.ChatMessage;

import java.util.*;

/**
 * Created by 高逸博 on 2017/3/19.
 */

public class ChatMessage {

    private String name;
    private String msg;
    private Date date;
    private Type type;

    public enum Type{
        INCOMING,OUTCOMING
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
