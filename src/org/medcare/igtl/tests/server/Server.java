package org.medcare.igtl.tests.server;

import java.io.IOException;


public class Server {

        public static MyOpenIGTServer openIGTServer;

		/**
         * @param args
         */
        public static void main(String[] args) {
                // TODO Auto-generated method stub
                int port = 8001; //Default value for port number
                if (args.length > 0) {
                      for (int index = 0; index < args.length; index++) {
                              String arg = args[index].trim();
                              if (index == 0)
                                      port = Integer.parseInt(arg);
                      }
                }
                try {
                	// OpenIGTServer listen on port
                	// For each client connection OpenIGTServer will start one ServerThread which start its MessageQueueManager
                	// which can use MessageQueueManager.addMessage to queue MessageHandler which will be performed
                	// MessageHandler.perform can a answer by using ServerThread.sendBytes in perform method of MessageHandler
                        openIGTServer = new MyOpenIGTServer(port);
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        System.out.println("Server on port : " + port + " Get an exception : ");
                        e.printStackTrace();
                }
        }
}
