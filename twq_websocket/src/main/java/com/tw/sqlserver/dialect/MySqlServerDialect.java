package com.tw.sqlserver.dialect;

import org.hibernate.dialect.SQLServerDialect;

public class MySqlServerDialect extends SQLServerDialect{

	public MySqlServerDialect() {
		super();
		registerHibernateType(-9, "string");
//		registerHibernateType(-9, StandardBasicTypes.STRING.getName());
	}
}
