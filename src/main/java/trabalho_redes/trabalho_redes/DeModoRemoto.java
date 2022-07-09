package trabalho_redes.trabalho_redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class DeModoRemoto {
	
	
	public static void main(String[] args) throws JSchException, IOException {
		
		String host = "000.000.00.000";
		String user = "root";
		String password = "root";
		int port = 22;
		
		JSch jsch = new JSch();
		
		Session session = jsch.getSession(user, host, port);
		session.setPassword(password);
		
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		
		session.setConfig(config);
		session.connect();
		
		ChannelExec channel = (ChannelExec) session.openChannel("exec");
				
		channel.setCommand("ps -aux");
		
		channel.setErrStream(System.err);
		
		channel.connect();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
		
		String line;
		
		while((line = reader.readLine()) != null) {
			
			System.out.println(line);
			
		}
		
		channel.disconnect();
		session.disconnect();
		
		 System.out.println("Exit code: " + channel.getExitStatus());
		
	}
	

}
