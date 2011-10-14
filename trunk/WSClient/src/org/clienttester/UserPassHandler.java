/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clienttester;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 *
 */
public class UserPassHandler {

    public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                ((NameCallback)callbacks[i]).setName("libuser");
            } else if (callbacks[i] instanceof PasswordCallback) {
                ((PasswordCallback)callbacks[i]).setPassword("books".toCharArray());
            } else {
                throw new UnsupportedCallbackException(callbacks[i],
                    "Unsupported callback type");
            }
        }
    }
}
