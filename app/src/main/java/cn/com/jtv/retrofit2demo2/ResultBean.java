package cn.com.jtv.retrofit2demo2;

/**
 * Created by fangdean on 2016/3/3.
 */
public class ResultBean {
    private int isSuccess;
    private String message;

    public ResultBean() {
    }

    public ResultBean(int isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}
