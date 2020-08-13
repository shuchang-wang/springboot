package com.alibaba.springboot.Listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("CommandLineRunner...run..."+ Arrays.asList(strings));
    }
}
