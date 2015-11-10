package org.wmz7year.main;

import org.apache.ignite.Ignition;

/**
 *
 * @ClassName: Main
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月10日下午3:05:42
 */
public class Main {
	public static void main(String[] args) {
		Ignition.start();
		System.out.println("ModuleB start");
	}
}
