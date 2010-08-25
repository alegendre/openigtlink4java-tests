package org.medcare.igtl.tests.server;

import java.io.IOException;

import org.medcare.igtl.network.MessageHandler;
import org.medcare.igtl.network.OpenIGTServer;
import org.medcare.igtl.network.ServerThread;
import org.medcare.igtl.util.Header;

public class MyOpenIGTServer extends OpenIGTServer {

        public MyOpenIGTServer(int port) throws IOException {
                super(port);
                // TODO Auto-generated constructor stub
        }

        @Override
        public MessageHandler getMessageHandler(Header header, byte[] bodyBuf,
                        ServerThread serverThread) {
                return new MyMessageHandler(header, bodyBuf, serverThread);
        }
}
