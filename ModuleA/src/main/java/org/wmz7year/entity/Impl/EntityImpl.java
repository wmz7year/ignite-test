package org.wmz7year.entity.Impl;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.wmz7year.entity.Entity;
import org.wmz7year.service.util.ExternalizableUtil;

/**
 *
 * @ClassName: EntityImpl
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月10日下午3:18:28
 */
public class EntityImpl implements Entity {
	private String fieldString;

	private int fieldInt;

	public EntityImpl() {

	}

	public EntityImpl(String fieldString, int fieldInt) {
		this.fieldString = fieldString;
		this.fieldInt = fieldInt;
	}

	public String getStringField() {
		throw new UnsupportedOperationException();
	}

	public int getIntField() {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("writeExternal");
		ExternalizableUtil.getInstance().writeBoolean(out, fieldString != null);
		if (fieldString != null) {
			ExternalizableUtil.getInstance().writeSafeUTF(out, fieldString);
		}
		ExternalizableUtil.getInstance().writeInt(out, fieldInt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("readExternal");
		if (ExternalizableUtil.getInstance().readBoolean(in)) {
			fieldString = ExternalizableUtil.getInstance().readSafeUTF(in);
		}
		fieldInt = ExternalizableUtil.getInstance().readInt(in);
	}
}
