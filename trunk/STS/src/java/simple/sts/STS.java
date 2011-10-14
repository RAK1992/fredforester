/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simple.sts;

import com.sun.xml.ws.security.trust.sts.BaseSTSImpl;
import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceProvider;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author manveen
 */
@WebServiceProvider(serviceName = "STSService", portName = "ISTSService_Port", targetNamespace = "http://tempuri.org/", wsdlLocation = "WEB-INF/wsdl/STS/STSService.wsdl")
@ServiceMode(value = Mode.PAYLOAD)
public class STS extends BaseSTSImpl implements Provider<Source> {
    @Resource
    WebServiceContext context;

    public Source invoke(Source rstElement) {
        return super.invoke(rstElement);
    }

    protected MessageContext getMessageContext() {
        MessageContext msgCtx = context.getMessageContext();
        return msgCtx;
    }

}
