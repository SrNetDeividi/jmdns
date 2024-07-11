package com.learn.jmdnstest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;


// This code is adapted from https://github.com/jmdns/jmdns

public class ExampleServiceDiscovery {

	private static class SampleListener implements ServiceListener {
		
		
		public void serviceAdded(ServiceEvent event) {
			System.out.println("Service added: " + event.getInfo());
		}

		
		public void serviceRemoved(ServiceEvent event) {
			System.out.println("Service removed: " + event.getInfo());
		}

		
		public void serviceResolved(ServiceEvent event) {
			
					System.out.println("Service resolved: " + event.getInfo());
			
                    ServiceInfo info = event.getInfo();
                    int port = info.getPort();
                    String path = info.getNiceTextString().split("=")[1];
                   
                    String url =  "http://localhost:"+port+"/"+path;
                    System.out.println(" --- sending request to " + url);
                    GetRequest.request(url);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			
			// Create a JmDNS instance
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			System.out.println("Client: InetAddress.getLocalHost():" + InetAddress.getLocalHost());
			// Add a service listener
			jmdns.addServiceListener("_http._tcp.local.", new SampleListener());

			// Wait a bit
            Thread.sleep(2000);
			
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}