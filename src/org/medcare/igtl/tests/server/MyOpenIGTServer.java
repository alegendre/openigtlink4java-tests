package org.medcare.igtl.tests.server;

import org.medcare.igtl.network.MessageHandler;
import org.medcare.igtl.network.OpenIGTServer;
import org.medcare.igtl.network.ServerThread;
import org.medcare.igtl.util.ErrorManager;
import org.medcare.igtl.util.Header;

public class MyOpenIGTServer extends OpenIGTServer {

        public MyOpenIGTServer(int port, ErrorManager errorManager) throws Exception {
                super(port, errorManager);
        }

        @Override
        public MessageHandler getMessageHandler(Header header, byte[] bodyBuf,
                        ServerThread serverThread) {
                return new MyMessageHandler(header, bodyBuf, serverThread);
        }
}
