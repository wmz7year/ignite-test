package org.wmz7year.service.impl;

import org.apache.ignite.lang.IgniteCallable;
import org.wmz7year.service.ExampleService;

/**
 *
 * @ClassName: Callable
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月10日下午3:12:27
 */
public class Callable implements IgniteCallable<Object> {
	private static final long serialVersionUID = 5931460475588001435L;

	private ExampleService service;

	public Callable() {

	}

	public Callable(ExampleService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Object call() throws Exception {
		return service.methodB();
	}

}
