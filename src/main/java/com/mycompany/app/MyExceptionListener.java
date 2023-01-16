package com.mycompany.app;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

public class MyExceptionListener implements ExceptionListener {
    @Override
    public void onException(JMSException exception) {
        System.out.println("Connection ExceptionListener fired, exiting.");
        exception.printStackTrace(System.out);
        System.exit(1);
    }
}