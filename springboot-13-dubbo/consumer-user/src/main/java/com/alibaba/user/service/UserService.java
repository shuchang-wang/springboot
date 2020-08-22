package com.alibaba.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.ticket.service.ITicketService;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Reference
    ITicketService iTicketService;

    public void hello() {
        String ticket = iTicketService.getTicket();
        System.out.println("买到票了：" + ticket);
    }
}