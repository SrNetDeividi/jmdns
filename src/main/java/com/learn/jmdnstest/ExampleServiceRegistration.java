
package com.learn.jmdnstest;

import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

// This code is adapted from https://github.com/jmdns/jmdns
public class ExampleServiceRegistration {

    public static void main(String[] args) throws InterruptedException {

        try {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			System.out.println("Registration: InetAddress.getLocalHost():" + InetAddress.getLocalHost());

			//start a service
		    HTTPServer.startMe(); 
			   
            // Register a service
            ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "example", 8000, "path=index.html");
            jmdns.registerService(serviceInfo);
            System.out.println("Registered: :" + InetAddress.getLocalHost() + serviceInfo.getPort());
             
            
            // Wait a bit
            Thread.sleep(2000);
         
            HTTPServer2.startMe();
            
            // Register a service
            ServiceInfo serviceInfo2 = ServiceInfo.create("_http._tcp.local.", "example2", 8001, "path=index.html");
            jmdns.registerService(serviceInfo2);
            System.out.println("Registered: :" + InetAddress.getLocalHost() + serviceInfo2.getPort());
      
            
            // Unregister all services
            //jmdns.unregisterAllServices();
            

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
