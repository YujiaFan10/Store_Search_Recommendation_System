package com.project.search.common;

public class CommonRes {

    /**
     *  Status: "success" or "fail"
      */
    private String status;

    /**
     *  data: json data / error code
     */
    private Object data;

    public static CommonRes create(Object result){
        return CommonRes.create(result, "success");
    }

    public static CommonRes create(Object result, String status){
        CommonRes commonRes = new CommonRes();
        commonRes.setData(result);
        commonRes.setStatus(status);
        return commonRes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
