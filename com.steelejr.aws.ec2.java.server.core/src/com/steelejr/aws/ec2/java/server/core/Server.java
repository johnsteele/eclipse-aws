package com.steelejr.aws.ec2.java.server.core;

/**
 * Represents a Server class.
 */
public class Server implements IServer {
	
	/**
	 * The name of this server.
	 */
	private String name;
	
	
	/**
	 * Creates a Server with the specified values.
	 * 
	 * @param name The name of the server.
	 */
	public Server (String name) {
		this.name = name;
	}
	
	
	@Override
	public String getName() {
		return name;
	}
}
