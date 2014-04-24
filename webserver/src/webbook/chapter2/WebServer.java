package webbook.chapter2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class WebServer {

	public static final int HTTP_PORT = 8080;
	private ServerSocket serverSocket;
	
	public void startServer(int port)
	{
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Web server startup on " + port);
			
			while(true)
			{
				Socket socket = serverSocket.accept();
				new Processor(socket).start();
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		WebServer server = new WebServer();
		if(args.length == 1)
		{
			server.startServer(Integer.parseInt(args[0]));
		}else
		{
			server.startServer(WebServer.HTTP_PORT);
		}
	}

}
