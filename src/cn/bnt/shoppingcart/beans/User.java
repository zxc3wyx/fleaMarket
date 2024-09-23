package cn.bnt.shoppingcart.beans;

public class User {

    private String uid;//表示用户编号
    private String userName;//用户名称
    private String loginPwd;//表示用户登录密码
    private String mobile;//表示用户的联系电话
    private String addr;//表示用户的家庭住址；

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User [uid=" + uid + ", userName=" + userName + ", loginPwd=" + loginPwd + ", mobile=" + mobile
                + ", addr=" + addr + "]";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }


}
