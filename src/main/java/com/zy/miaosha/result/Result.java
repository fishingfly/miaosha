package com.zy.miaosha.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;
    
    /**
     * 成功的时候调用
     * @param data
     * @return
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }
    
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }
    
    private Result(CodeMsg cm) {
        if (cm == null) return;
        this.setCode(cm.getCode());
        this.msg = cm.getMsg();
    }
    
    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    private Result(T data) {
        this.data = data;
    }
    
    
    
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
}
