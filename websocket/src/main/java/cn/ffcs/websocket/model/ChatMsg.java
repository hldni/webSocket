package cn.ffcs.websocket.model;

import java.util.Date;

public class ChatMsg {
    private String from;
    private String to;
    private String content;
    //消息存放
    private Date date;
    private String fromNickname;
    private int type;//

    @Override
    public String toString() {
        return "ChatMsg{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", fromNickname='" + fromNickname + '\'' +
                ", type=" + type +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromNickname() {
        return fromNickname;
    }

    public void setFromNickname(String fromNickname) {
        this.fromNickname = fromNickname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
