package aop.dto;

import lombok.Getter;

@Getter
public class User {

    private String id;
    private String pw;
    private int email;

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", email=" + email +
                '}';
    }
}
