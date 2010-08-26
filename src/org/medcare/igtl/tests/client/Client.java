package org.medcare.igtl.tests.client;

import java.io.IOException;
import java.net.UnknownHostException;

import org.medcare.igtl.messages.PositionMessage;
import org.medcare.igtl.network.RequestQueueManager;
import org.medcare.igtl.util.ErrorManager;

public class Client {

        public static RequestQueueManager requestQueueManager;
        private static int sleep = 100;
		private static MyErrorManager errorManager;

        /**
         * @param args
         */
        public static void main(String[] args) {
                String host = "localhost"; //Default value for host name
                int port = 8001; //Default value for port number
                if (args.length > 0) {
                        for (int index = 0; index < args.length; index++) {
                                String arg = args[index].trim();
                                if (index == 0)
                                        port = Integer.parseInt(arg);
                                if (index == 1)
                                        sleep  = Integer.parseInt(arg);
                        }
                }
                PositionMessage positionMessage = new PositionMessage("Client");
                //start RequestQueueManager which start OpenIGTClient which start ResponseQueueManager
                errorManager = new MyErrorManager();
                try {
                        requestQueueManager = new RequestQueueManager(new MyOpenIGTClient(host, port, errorManager));
                        requestQueueManager.start();
                        while (true) {
                                double[] quaternion = {0.0, 0.6666666666, 0.577350269189626, 0.6666666666};
                                int quaternionSize = PositionMessage.ALL;
                                double[] position = {0.0, 50.0, 50.0};
                                positionMessage.setPositionData(position, quaternion, quaternionSize);
                                requestQueueManager.addRequest(positionMessage.PackBody());
                                try {
                                        Thread.sleep(sleep);
                                } catch (InterruptedException e) {
                                        errorManager.error("Client thread InterruptedException", e, ErrorManager.APPLICATION_EXCEPTION);
                               }
                }
                } catch (UnknownHostException e1) {
                        errorManager.error("Client UnknownHostException", e1, ErrorManager.APPLICATION_UNKNOWNHOST_EXCEPTION);
                } catch (IOException e1) {
                        errorManager.error("Client IOException", e1, ErrorManager.APPLICATION_IO_EXCEPTION);
                } catch (Exception e1) {
                        System.out.println("Client Exception");
                        errorManager.error("Client Exception", e1, ErrorManager.APPLICATION_EXCEPTION);
                }
        }
}
