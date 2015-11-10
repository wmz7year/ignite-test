package org.wmz7year.service.impl;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.wmz7year.entity.Entity;
import org.wmz7year.service.ExampleService;
import org.wmz7year.service.util.ExternalizableUtil;

/**
 *
 * @ClassName: ExampleServiceImpl
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月10日下午3:00:38
 */
public class ExampleServiceImpl implements ExampleService {

	private Entity entity;

	public ExampleServiceImpl() {
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
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.wmz7year.service.ExampleService#methodC()
	 */
	@Override
	public void methodC() {
		System.out.println("method C");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		ExternalizableUtil.getInstance().writeSerializable(out, entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		entity = (Entity) ExternalizableUtil.getInstance().readSerializable(in);
	}

}
