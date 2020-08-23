package com.alibaba.provider.service.impl;

import com.alibaba.provider.service.ITicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements ITicketService {

    @Override
    public String getTicket() {
        System.out.println("8001");
        return "《八佰》";
    }
}
