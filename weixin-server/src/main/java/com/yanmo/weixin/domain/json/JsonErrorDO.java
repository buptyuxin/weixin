package com.yanmo.weixin.domain.json;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
@Deprecated
public class JsonErrorDO extends BaseJsonDO {
    private Long errcode;
    private String errmsg;

    public Long getErrcode() {
        return errcode;
    }

    public void setErrcode(Long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
