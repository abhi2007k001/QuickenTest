package com.dlc.utilities;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import com.aventstack.extentreports.Status;
import com.dlc.core.BaseUI;
import com.dlc.core.ReadConfig;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class sshUtils extends BaseUI {

	public void runSsh() {
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session = jsch.getSession(ReadConfig.properties.getProperty("userName"),
					ReadConfig.properties.getProperty("host"), 22);
			session.setPassword(ReadConfig.properties.getProperty("password"));
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");

			Channel channel = session.openChannel("shell");
			OutputStream ops = channel.getOutputStream();
			PrintStream ps = new PrintStream(ops, true);

			channel.connect();
			ps.println("sudo su");
			ps.println("cd ../../");
			ps.println("cd root/Deployments_GOI/swarm_deployment/optimized-batch-jobs-v1_new/");
			Thread.sleep(3000);
			ps.println("bash new_run.sh");
			logger.log(Status.INFO, ">>>>>Batch run intiated<<<<<");
			Thread.sleep(10000);
			// channel.disconnect();
			// session.disconnect();
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runSshAndLogs() {
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session1 = jsch.getSession("root", "10.63.110.62", 22);
			session1.setPassword("Password@123");
			session1.setConfig(config);
			session1.connect();
			System.out.println("SSH Connected");

			Channel channel1 = session1.openChannel("shell");
			OutputStream ops1 = channel1.getOutputStream();
			PrintStream ps1 = new PrintStream(ops1, true);

			channel1.connect();
			ps1.println("sudo su");
			ps1.println("cd ../../");
			ps1.println("cd root/Deployments_GOI/swarm_deployment/optimized-batch-jobs-v1_new/");
			ps1.println("tail -f dashboard_batch.log");
			InputStream in = channel1.getInputStream();
			String x = "";
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					x = new String(tmp, 0, i);
					System.out.print(new String(tmp, 0, i));
				}
				if (channel1.isClosed()) {
					System.out.println("exit-status: " + channel1.getExitStatus());
					break;
				} else if (x.contains("Finished Job 6") || x.contains("Jobs Ended")) {

					logger.log(Status.INFO, ">>>>>Batch run completed<<<<<");
					System.out.println(">>>>>Batch run completed<<<<<");
					break;

				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
				}
			}
			channel1.disconnect();
			session1.disconnect();
			System.out.println("SSH Disconnected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runBatchRun() {
		runSsh();
		runSshAndLogs();

	}

}
