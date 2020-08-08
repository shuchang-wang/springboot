package com.alibaba.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.Attribute;
import com.test.Path;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kindeditor")
public class KindeditorController {

    @PostMapping("/upload")
    @ResponseBody
    public Object uploadJson(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        StringBuffer json = new StringBuffer();
        byte[] tempbytes = new byte[102400];
        int byteread = 0;
        while ((byteread = inputStream.read(tempbytes)) != -1) {
            json.append(new String(tempbytes, 0, byteread));
        }
        JSONObject jsonObject = JSONObject.parseObject(json.toString());
        Map<String, Object> innerMap = jsonObject.getInnerMap();
        List<Path> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (String key : innerMap.keySet()) {
            String value = innerMap.get(key).toString();
            List<Attribute> attributes = JSON.parseArray(value, Attribute.class);
            List uniqueList = attributes.stream().distinct().collect(Collectors.toList());
            list.add(new Path(key, uniqueList));
        }
        return list;
    }
}
