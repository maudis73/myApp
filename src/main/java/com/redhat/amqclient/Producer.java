package com.redhat.amqclient;

import com.mycompany.app.MyExceptionListener;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

class Producer {
    public static final int PRODUCE_TYPE = 0;
    public static final int PRODUCE_BATCH_SIZE = 1000;
    {
        try {
            // The configuration for the Qpid InitialContextFactory has been supplied in
            // a jndi.properties file in the classpath, which results in it being picked
            // up automatically by the InitialContext constructor.
            Context context = new InitialContext();

            ConnectionFactory factory = (ConnectionFactory) context.lookup("myFactoryLookup");
            Destination queue = (Destination) context.lookup("myQueueLookup");

            Connection connection = factory.createConnection(System.getProperty("USER"),
                    System.getProperty("PASSWORD"));
            connection.setExceptionListener(new MyExceptionListener());
            while (true) {

                connection.start();

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(queue);

                TextMessage message = session.createTextMessage("Hello world!");

                for (int i=0; i<1000; i++) {
                messageProducer.send(message, DeliveryMode.NON_PERSISTENT, Message.DEFAULT_PRIORITY,
                        Message.DEFAULT_TIME_TO_LIVE);
                }

                connection.close();
            }
        } catch (Exception exp) {
            System.out.println("Caught exception, exiting.");
            exp.printStackTrace(System.out);
            System.exit(1);
        }

    }
}
