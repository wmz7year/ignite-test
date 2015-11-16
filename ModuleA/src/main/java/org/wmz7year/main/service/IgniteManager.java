package org.wmz7year.main.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.IgniteSpring;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.marshaller.optimized.OptimizedMarshaller;
import org.apache.ignite.services.Service;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.zk.TcpDiscoveryZookeeperIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Ignite管理模块<br>
 * 负责维护缓存的启动、缓存的维护等等操作
 * 
 * @ClassName: IgniteManager
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月13日下午12:48:29
 */
@Component
@Scope("singleton")
public class IgniteManager {
	private Logger logger = LoggerFactory.getLogger(IgniteManager.class);

	/**
	 * ignite配置文件
	 */
	private String igniteConfigFile = "ignite.xml";

	@Autowired
	private ApplicationContext context;

	/**
	 * ignite对象
	 */
	private Ignite ignite;
	
	public Ignite getIgnite(){
		return ignite;
	}

	/**
	 * ignite服务对象
	 */
	private IgniteServices igniteServices;

	/**
	 * 注册ignite分布式服务的方法
	 * 
	 * @param service
	 *            需要注册的服务
	 */
	public void registerClusterService(Service service) {
		Class<? extends Service> serviceClass = service.getClass();
		logger.info("准备注册分布式服务：" + serviceClass.getName());
		Class<?>[] implementInterfacess = serviceClass.getInterfaces();
		String deployServiceName = "testService";
		for (Class<?> implementInterfaces : implementInterfacess) {
			String interfaceName = implementInterfaces.getName();
			if (interfaceName.startsWith("com.laimiya.push.service.")) {
				deployServiceName = interfaceName;
				break;
			}
		}
		igniteServices.deployMultiple(deployServiceName, service, Runtime.getRuntime().availableProcessors(), 0);
		logger.info("注册分布式服务：" + deployServiceName + " 成功");
	}

	/*
	 * @see com.laimiya.push.module.Module#initialize()
	 */
	@PostConstruct
	public void initialize() {
		logger.info("准备启动ignite 配置文件：" + igniteConfigFile);
		try {
			IgniteConfiguration config = new IgniteConfiguration();
			config.setPeerClassLoadingEnabled(true);
			OptimizedMarshaller marsh = new OptimizedMarshaller();
			marsh.setRequireSerializable(true);
			config.setMarshaller(marsh);
			TcpDiscoveryZookeeperIpFinder ipFinder = new TcpDiscoveryZookeeperIpFinder();
			ipFinder.setZkConnectionString("127.0.0.1:2181");
			TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
			discoverySpi.setIpFinder(ipFinder);
			config.setDiscoverySpi(discoverySpi);
			ignite = IgniteSpring.start(config, context);
		} catch (IgniteCheckedException e) {
			e.printStackTrace();
		}
		// ignite = Ignition.start(igniteConfigFile);
		igniteServices = ignite.services();
		logger.info("ignite启动完成");
	}

	/*
	 * @see com.laimiya.push.module.Module#destroy()
	 */
	@PreDestroy
	public void destroy() {
		logger.info("准备停止ignite");
		if (ignite != null) {
			ignite.close();
			logger.info("ignite停止成功");
		} else {
			logger.info("ignite未启动 无法停止");
		}
	}
}
