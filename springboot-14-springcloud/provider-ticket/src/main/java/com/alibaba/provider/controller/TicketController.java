package com.alibaba.provider.controller;

import com.alibaba.provider.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    @Autowired
    ITicketService iTicketService;

    @GetMapping("/ticket")
    public String getTicket() {
        String ticket = iTicketService.getTicket();
        return ticket;
    }

}