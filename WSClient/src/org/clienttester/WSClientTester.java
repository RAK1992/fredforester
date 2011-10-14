/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.clienttester;

import org.wsusermanager.User;
import org.wsusermanager.UserManager;
import org.wsusermanager.UserManagerService;

/**
 *
 */
public class WSClientTester {

    public static void main(String[] args)
    {
        UserManagerService ums = new UserManagerService();
        UserManager um = ums.getUserManagerPort();
        User user = um.getUserByName("fforester");
        System.out.println("Got User " + user.getFirstName() + " " + user.getLastName());
    }

}
