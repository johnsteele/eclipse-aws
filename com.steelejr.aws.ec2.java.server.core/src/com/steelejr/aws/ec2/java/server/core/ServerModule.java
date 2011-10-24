package com.steelejr.aws.ec2.java.server.core;


/**
 * A ServerModule is a unit of deployment for a Server.
 */
public class ServerModule implements IServerModule {
	
	/**
	 * The name of this module.
	 */
	private String name;
	
	
	/**
	 * Creates  ServerModule with the specified values.
	 * 
	 * @param name The name of the module.
	 */
	public ServerModule (String name) {
		this.name = name;
	}

	
	@Override
	public String getName() {
		return name;
	}
}
