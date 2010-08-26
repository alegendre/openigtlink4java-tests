package org.medcare.igtl.tests.server;

import java.io.IOException;

import org.medcare.igtl.tests.client.MyErrorManager;
import org.medcare.igtl.util.ErrorManager;


public class Server {

        public static MyOpenIGTServer openIGTServer;
		private static MyErrorManager errorManager;

        /**
         * @param args
         */
        public static void main(String[] args) {
                int port = 8001; //Default value for port number
                if (args.length > 0) {
                      for (int index = 0; index < args.length; index++) {
                              String arg = args[index].trim();
                              if (index == 0)
                                      port = Integer.parseInt(arg);
                      }
                }
                errorManager = new MyErrorManager();
                try {
                        // OpenIGTServer listen on port
                        // For each client connection OpenIGTServer will start one ServerThread which start its MessageQueueManager
                        // which can use MessageQueueManager.addMessage to queue MessageHandler which will be performed
                        // MessageHandler.perform can a answer by using ServerThread.sendBytes in perform method of MessageHandler
                        openIGTServer = new MyOpenIGTServer(port, errorManager);
                } catch (IOException e) {
                        errorManager.error("Server on port : " + port + " Get an ioException : ", e, ErrorManager.APPLICATION_IO_EXCEPTION);
                } catch (Exception e) {
                        errorManager.error("Server on port : " + port + " Get an exception", e, ErrorManager.APPLICATION_EXCEPTION);
                }
        }
}
