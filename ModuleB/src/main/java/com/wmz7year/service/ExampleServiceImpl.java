package com.wmz7year.service;

import javax.annotation.PostConstruct;

import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.wmz7year.service.ExampleService;

/**
 *
 * @ClassName: ExampleServiceImpl
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月13日上午9:22:36
 */
@org.springframework.stereotype.Service
public class ExampleServiceImpl implements ExampleService, Service {
	private static final long serialVersionUID = 1088776816924783180L;

	@Autowired
	private transient IgniteManager igniteManager;
	
	@Autowired
	private transient OtherService otherService;
	
	public ExampleServiceImpl(){
		System.out.println("初始化：" + this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ignite.services.Service#cancel(org.apache.ignite.services.
	 * ServiceContext)
	 */
	@Override
	public void cancel(ServiceContext ctx) {
		System.out.println("cancel");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ignite.services.Service#init(org.apache.ignite.services.
	 * ServiceContext)
	 */
	@Override
	public void init(ServiceContext ctx) throws Exception {
		System.out.println("init");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ignite.services.Service#execute(org.apache.ignite.services.
	 * ServiceContext)
	 */
	@Override
	public void execute(ServiceContext ctx) throws Exception {
		System.out.println("execute");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.wmz7year.service.ExampleService#methodA()
	 */
	@Override
	public void methodA() {
		System.out.println("methodA");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.wmz7year.service.ExampleService#methodB()
	 */
	@Override
	public Object methodB() {
		return "say hello" + otherService.sayWord();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.wmz7year.service.ExampleService#methodC()
	 */
	@Override
	public void methodC() {
		System.out.println("methodC");
	}

	
	@PostConstruct
	public void initialize() {
		System.err.println("other service :"+otherService);
		igniteManager.registerClusterService(this);
	}

}
