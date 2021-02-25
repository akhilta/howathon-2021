package com.sapient.teamsApi.Controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Sftpservercode {
	private static final String CHANNEL_SFTP = "sftp";
	protected static final Logger log = LoggerFactory.getLogger(Sftpservercode.class);
	
	
	public void sftpgettingfile() throws JSchException, SftpException, IOException {
		
		Parameters parameters=new Parameters();
		
		parameters.setFilename("sftpfile1.txt");
		parameters.setHost("sftp.is.chrysler.com");
		parameters.setPassword("Bm4edSkmn87321V");
		parameters.setPath("/incoming/apac_configurator");
		parameters.setPort(22);
		parameters.setUsername("apaccode");
		
		
		
		
		
		JSch jsch = new JSch();

        log.info(
                "connecting to {}@{}:{}",
                new String[] {parameters.getUsername(), parameters.getHost(), String.valueOf(parameters.getPort()) });

        Session session = jsch.getSession(parameters.getUsername(), parameters.getHost(), parameters.getPort());
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(parameters.getPassword());
        session.connect();

        Channel channel = session.openChannel(CHANNEL_SFTP);
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;

        log.debug("change directory to {}", parameters.getPath());
        sftpChannel.cd(parameters.getPath());

        log.debug("get file {}", parameters.getFilename());
        InputStream stream = sftpChannel.get(parameters.getFilename());
        String theString = IOUtils.toString(stream, "UTF-8"); 
        System.out.println("--->"+theString);
    //    saveStreamToRepository(stream, resourceResolver, path, parameters.getFilename());

        sftpChannel.exit();
        session.disconnect();

        log.info("disconnecting from sFTP after retrieving file {}.", parameters.getFilename());

      //  return path + "/" + parameters.getFilename();
	}

	public static void main(String[] args) {
		Sftpservercode sftp1=new Sftpservercode();
		
		try {
			sftp1.sftpgettingfile();
			System.out.println("came here");
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			//System.out.println(e.printStackTrace());
		}
	}
}
