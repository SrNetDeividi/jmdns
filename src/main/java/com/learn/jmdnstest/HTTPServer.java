package com.learn.jmdnstest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

// http://stackoverflow.com/questions/3732109/simple-http-server-in-java-using-only-java-se-api
public class HTTPServer {

    public static void main(String[] args) throws Exception {
    	
        startMe();
    }

	public static void startMe() throws IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        server.createContext("/index.html", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Service1 is running at" + server.getAddress() + "/index.html");
	}

    static class MyHandler implements HttpHandler {
    	
       
        public void handle(HttpExchange t) throws IOException {
            
        	String response = "This is the response from Service1 at " + LocalDateTime.now();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            
        }
    }

}
