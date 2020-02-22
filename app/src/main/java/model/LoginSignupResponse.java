package model;

public class LoginSignupResponse {
    private boolean success;
    private String status;



    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public boolean getSuccess() {
        return success;
    }
}
