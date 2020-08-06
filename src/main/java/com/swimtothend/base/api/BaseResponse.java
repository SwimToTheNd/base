package com.swimtothend.base.api;

/**
 * create by BloodFly at 2019/1/21
 */
public class BaseResponse extends BaseVO {
    private String resultCode;
    private String resultMessage;


    public BaseResponse() {
        setStatus(CommonResultCode.EXECUTE_SUCCESS);
    }

    public BaseResponse(ResultCodeable result) {
        setStatus(result.getCode(), result.getMessage());
    }


    public BaseResponse(String resultCode, String resultMessage) {
        setStatus(resultCode, resultMessage);
    }

    public void setStatus(String resultCode, String resultMessage) {
        setResultCode(resultCode);
        setResultMessage(resultMessage);
    }

    public void setStatus(ResultCodeable result) {
        setStatus(result.getCode(), result.getMessage());
    }

    public boolean isSuccess() {
        return isEqualsCode(CommonResultCode.EXECUTE_SUCCESS);
    }

    public boolean isEqualsCode(CommonResultCode result) {
        return resultCode != null && resultCode.equals(result.getCode());
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
