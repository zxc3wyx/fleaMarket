package cn.bnt.shoppingcart.beans;

public class User {

    private String uid;//��ʾ�û����
    private String userName;//�û�����
    private String loginPwd;//��ʾ�û���¼����
    private String mobile;//��ʾ�û�����ϵ�绰
    private String addr;//��ʾ�û��ļ�ͥסַ��

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
