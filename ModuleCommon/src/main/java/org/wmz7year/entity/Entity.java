package org.wmz7year.entity;

import java.io.Externalizable;

/**
 *
 * @ClassName: Entity
 * @author Jiang Wei(jiangwei@1318.com)
 * @date 2015年11月10日下午3:17:15
 */
public interface Entity extends Externalizable {

	public String getStringField();

	public int getIntField();
}
