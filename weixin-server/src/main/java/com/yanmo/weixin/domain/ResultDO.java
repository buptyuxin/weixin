package com.yanmo.weixin.domain;

import java.util.List;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class ResultDO<T> {
    private T module;
    private boolean success;
    private List<Errors> errorList;

    public T getModule() {
        return module;
    }

    public void setModule(T module) {
        this.module = module;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Errors> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Errors> errorList) {
        this.errorList = errorList;
    }

    public void addError(Errors error) {
        errorList.add(error);
        if (success) {
            success = false;
        }
    }
}
