package com.alibaba.springboot.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.HashMap;
import java.util.Map;

//给容器中加入我们自己定义的ErrorAttribute
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    //返回值的map就是页面和和json能获取的所有字段
    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> attributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        attributes.put("company", "alibaba");
        //我们异常处理器携带的数据
        HashMap<String, Object> ext = (HashMap<String, Object>) requestAttributes.getAttribute("ext", 0);
        attributes.put("ext", ext);
        return attributes;
    }
}
