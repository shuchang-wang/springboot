package com.alibaba.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot11TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    //发送简单类型的邮件
    @Test
    public void test01() {
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("通知——今晚开会");
        //设置邮件内容
        message.setText("今晚23:00开股东大会！");
        //设置发送方，设置自己的邮箱账号
        message.setFrom("xxxxxxx@qq.com");
        //设置发送目的地方，设置接收方的邮箱账号
        message.setTo("xxxxxxx@163.com");
        //设置发送日期
        message.setSentDate(new Date());

        //开始发送消息
        mailSender.send(message);
    }

    //发送复杂类型的邮件
    @Test
    public void test02() throws MessagingException {
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //设置邮件标题
        helper.setSubject("通知——今晚开会");
        //设置邮件内容
        helper.setText("<b style='color:red'>今晚23:00开股东大会！</b>", true);
        //设置发送方，设置自己的邮箱账号
        helper.setFrom("xxxxxxx@qq.com");
        //设置发送目的地方，设置接收方的邮箱账号
        helper.setTo("xxxxxxx@163.com");
        //设置发送日期
        helper.setSentDate(new Date());

        //添加附件
        helper.addAttachment("1.jpg", new File("D:\\photo\\1.jpg"));
        helper.addAttachment("2.jpg", new File("D:\\photo\\2.jpg"));

        //开始发送消息
        mailSender.send(mimeMessage);
    }

}