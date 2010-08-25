package org.medcare.igtl.tests.client;

import org.medcare.igtl.messages.PositionMessage;
import org.medcare.igtl.network.RequestQueueManager;

public class Client {

        public static RequestQueueManager requestQueueManager;
		private static int sleep = 100;

		/**
         * @param args
         */
        public static void main(String[] args) {
                String host = "192.168.10.3"; //Default value for host name
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
                requestQueueManager = new RequestQueueManager(new MyOpenIGTClient(host, port));
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
        }
}
