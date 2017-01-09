package com.open.digital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author pradyroy
 * @version 1.0
 * @since 2016-11-11
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class NotifyStart {

	public static void main(String[] args) {
		SpringApplication.run(NotifyStart.class, args);
	}

}