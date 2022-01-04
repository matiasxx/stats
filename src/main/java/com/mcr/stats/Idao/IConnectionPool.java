package com.mcr.stats.Idao;

import java.sql.Connection;

public interface IConnectionPool {
	
	public abstract Connection getConnection();

}
