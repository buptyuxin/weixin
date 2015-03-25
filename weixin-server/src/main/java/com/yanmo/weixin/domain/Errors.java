package com.yanmo.weixin.domain;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public enum Errors {
        ACCESS_TOKEN_ERROR("ACCESS_TOKEN_ERROR", "access token错误");

        private String code;
        private String name;

        Errors(String code, String name) {
                this.code = code;
                this.name = name;
        }

        public String getCode() {
                return code;
        }

        public void setCode(String code) {
                this.code = code;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public static String getNameByCode(String code) {
                Errors[] errors = Errors.values();
                for (int i = 0; i < errors.length; i++) {
                        if (errors[i].getCode().equals(code)) {
                                return errors[i].getName();
                        }
                }
                return null;
        }
}
