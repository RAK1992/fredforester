/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simple.sts;

import com.sun.xml.wss.impl.callback.PasswordValidationCallback;
import org.springframework.context.ApplicationContext;
import org.sts.web.ApplicationContextProvider;

/**
 *
 */
public class PlainTextPasswordValidator implements PasswordValidationCallback.PasswordValidator {

    public boolean validate(PasswordValidationCallback.Request request)
            throws PasswordValidationCallback.PasswordValidationException {

        PasswordValidationCallback.PlainTextPasswordRequest plainTextRequest
            = (PasswordValidationCallback.PlainTextPasswordRequest) request;

        System.out.println("PlainTextPasswordValidator User " + plainTextRequest.getUsername());

        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        
        LDAPAuthenticator client = (LDAPAuthenticator)ctx.getBean("stsAuthenticator");

        return client.AuthenticateWSUser(plainTextRequest.getUsername(), plainTextRequest.getPassword());
        
        /*
        if ("alice".equals(plainTextRequest.getUsername())
                && "clarinet".equals(plainTextRequest.getPassword())) {
            return true;
        } else if ("bob".equals(plainTextRequest.getUsername())
                && "trombone".equals(plainTextRequest.getPassword())) {
            return true;
        }
        return false;
         * 
         */
    }

}
