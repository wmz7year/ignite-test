package com.wmz7year;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @ClassName: Main
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月13日上午9:16:04
 */
@Configuration
@ComponentScan
public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(Main.class);
	}
}
