package com.alibaba.interview.enums;

/**
 * @author: WSC
 * @Create 2020/8/30 23:38
 */
public enum CountryEnum {
    
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    private Integer retCode;
    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public static CountryEnum foreach_CountryEnum(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum element : values) {
            if (element.getRetCode().equals(index)) {
                return element;
            }
        }
        return null;
    }
}