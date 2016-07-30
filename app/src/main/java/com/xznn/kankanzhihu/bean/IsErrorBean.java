package com.xznn.kankanzhihu.bean;

/**
 * @author MapleDev
 * @time 16/07/29  14:52
 * @desc ${TODD}
 */
public class IsErrorBean {

    /**
     * count : 10
     * error :
     */

    private int count;
    private String error;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
