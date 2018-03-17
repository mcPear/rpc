package com.maciek.async;

import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

/**
 * Created by maciej on 04.03.18.
 */
public class DefaultAsyncCallback implements AsyncCallback {
    @Override
    public void handleResult(Object o, URL url, String s) {
        System.out.println("\nHandled result from " + url + " method: " + s + " result: " + o);
    }

    @Override
    public void handleError(Exception e, URL url, String s) {
        System.out.println("\nHandled error from " + url + " " + e + " " + s);
    }
}
