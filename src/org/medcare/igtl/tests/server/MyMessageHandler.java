package org.medcare.igtl.tests.server;

import org.medcare.igtl.messages.GetCapabilityMessage;
import org.medcare.igtl.messages.ImageMessage;
import org.medcare.igtl.messages.OpenIGTMessage;
import org.medcare.igtl.messages.PositionMessage;
import org.medcare.igtl.messages.StatusMessage;
import org.medcare.igtl.messages.TransformMessage;
import org.medcare.igtl.network.MessageHandler;
import org.medcare.igtl.network.ServerThread;
import org.medcare.igtl.util.Header;
//GET_CAPABIL, GET_IMAGE, GET_IMGMETA, GET_LBMETA, GET_STATUS, GET_TRAJ, CAPABILITY, COLORTABLE, IMAGE, IMGMETA, POINT, POSITION, STATUS, STP_TDATA, STT_TDATA, TDATA, TRAJ, TRANSFORM

public class MyMessageHandler extends MessageHandler {
        public OpenIGTMessage openIGTMessage;

                public MyMessageHandler(Header header, byte[] body,
                        ServerThread serverThread) {
                super(header, body, serverThread);
                capabilityList.add("GET_CAPABIL");
                capabilityList.add("TRANSFORM");
                capabilityList.add("POSITION");
                capabilityList.add("IMAGE");
                capabilityList.add("STATUS");
        }

        @Override
        public void manageError (String message, Exception exception, int errorCode) {
                serverThread.errorManager.error(message, exception, errorCode);
        }

        @Override
        public boolean perform(String messageType) throws Exception {
                System.out.println("perform messageType : " + messageType);
                if (messageType.equals("GET_CAPABIL")) {
                        openIGTMessage = new GetCapabilityMessage(header, body);
                } else if  (messageType.equals("TRANSFORM")) {
                        openIGTMessage = new TransformMessage(header, body);
                } else if (messageType.equals("POSITION")) {
                        System.out.println("perform trouve POSITION");
                        openIGTMessage= new PositionMessage(header, body);
                } else if (messageType.equals("IMAGE")) {
                        openIGTMessage = new ImageMessage(header, body);
                } else if (messageType.equals("STATUS")) {
                        openIGTMessage = new StatusMessage(header, body);
                } else {
                        System.out.println("Perform messageType : " + messageType + " not implemented");
                        return false;
                }
                System.out.println("Perform messageType : " + messageType + " content : " + openIGTMessage.toString());
                PositionMessage positionMessage = new PositionMessage("Traker");
                double[] quaternion = {0.0, 0.6666666666, 0.577350269189626, 0.6666666666};
                int quaternionSize = PositionMessage.ALL;
                double[] position = {0.0, 50.0, 50.0};
                positionMessage.setPositionData(position, quaternion, quaternionSize);
                System.out.println("perform messageType SendBytes");
                serverThread.sendBytes(positionMessage.PackBody());
                System.out.println("perform messageType SendBytes done");
                return true;
        }
}
