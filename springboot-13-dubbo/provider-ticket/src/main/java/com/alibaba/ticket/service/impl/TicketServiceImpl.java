package com.alibaba.ticket.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.ticket.service.ITicketService;
import org.springframework.stereotype.Component;

@Component
@Service//dubbo包下的注解；将服务发布出去com.alibaba.dubbo.config.annotation.Service
public class TicketServiceImpl implements ITicketService {

    @Override
    public String getTicket() {
        return "《八佰》";
    }

}