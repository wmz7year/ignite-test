package org.wmz7year.main;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCluster;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
import org.wmz7year.service.impl.Callable;
import org.wmz7year.service.impl.ExampleServiceImplA;

/**
 *
 * @ClassName: Main
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月10日下午3:05:42
 */
public class Main {
	public static void main(String[] args) {
		Ignite ignite = Ignition.start("ignite.xml");
		IgniteCluster cluster = ignite.cluster();
		ClusterGroup forRemotes = cluster.forRemotes();
		IgniteCompute compute = ignite.compute(forRemotes);

		Object result = compute.call(new Callable(new ExampleServiceImplA()));
		System.out.println("Module A call result:" + result);
	}
}
