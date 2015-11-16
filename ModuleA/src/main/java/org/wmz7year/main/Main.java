package org.wmz7year.main;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.resources.ServiceResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.wmz7year.main.service.IgniteManager;
import org.wmz7year.service.ExampleService;

/**
 *
 * @ClassName: Main
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月10日下午3:05:42
 */
@Configuration
@ComponentScan
public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);
		IgniteManager igniteManager = applicationContext.getBean(IgniteManager.class);
		Ignite ignite = igniteManager.getIgnite();
		ClusterGroup remotes = ignite.cluster().forRemotes();
		IgniteCompute compute = ignite.compute(remotes);

		Object result = compute.call(new IgniteCallable<Object>() {
			private static final long serialVersionUID = -6558673806566005180L;
			@ServiceResource(serviceName = "testService", proxyInterface = ExampleService.class)
			private transient ExampleService service;

			@Override
			public Object call() throws Exception {
				return service.methodB();
			}
		});
		System.out.println("result:" + result);

		// ExampleService service =
		// ignite.services(remotes).serviceProxy("testService",
		// ExampleService.class, false);
		// System.out.println(service.methodB());
	}
}
