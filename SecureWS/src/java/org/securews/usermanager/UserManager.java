/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.securews.usermanager;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.securews.dao.UserDao;
import org.securews.model.User;

/**
 *
 */
@WebService()
public class UserManager {

    private UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUserByName")
    public User getUserByName(@WebParam(name = "userId")
    String userId) {
        System.out.println("Entering getUserByName");

        List<User> users = userDao.findByUserId(userId);
        for(User mu : users)
        {
            return mu;
        }
        return null;

    }

}
