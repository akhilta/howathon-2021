package com.sapient.teamsApi.Controller;

public class Parameters{

	 String username;
	
	 String host;
	
	 
	 String password;
	 
	 String path;
	 
	 String filename;
	 public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	int port;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	 
	 
	
}


/*
 *   log.info(
                "connecting to {}@{}:{}",
                new String[] {parameters.getUsername(), parameters.getHost(), String.valueOf(parameters.getPort()) });

 */
