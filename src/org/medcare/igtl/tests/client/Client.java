package org.medcare.igtl.tests.client;

import java.io.IOException;
import java.net.UnknownHostException;

import org.medcare.igtl.messages.PositionMessage;
import org.medcare.igtl.network.RequestQueueManager;

public class Client {

        public static RequestQueueManager requestQueueManager;
                private static int sleep = 100;

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
                try {
                        requestQueueManager = new RequestQueueManager(new MyOpenIGTClient(host, port));
                } catch (UnknownHostException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }
                requestQueueManager.start();
                while (true) {
                        double[] quaternion = {0.0, 0.6666666666, 0.577350269189626, 0.6666666666};
                                        int quaternionSize = PositionMessage.ALL;
                                        double[] position = {0.0, 50.0, 50.0};
                                        positionMessage.setPositionData(position, quaternion, quaternionSize);
                                        positionMessage.PackBody();
                        requestQueueManager.addRequest(positionMessage.getBytes());
                        try {
                                Thread.sleep(sleep);
                        } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                       }
                }
        }
}
