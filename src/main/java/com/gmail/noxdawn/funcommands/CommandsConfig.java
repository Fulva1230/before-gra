package com.gmail.noxdawn.funcommands;

import com.gmail.noxdawn.PluginCommandExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandsConfig {
    @Bean("echofuck")
    public PluginCommandExecutor echoFuckExecutor() {
        return new EchoFuckExecutor();
    }
}