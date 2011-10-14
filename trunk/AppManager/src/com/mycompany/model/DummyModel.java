/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.model;

import java.io.Serializable;


/**
 *
 * @author Fred Forester
 */
public class DummyModel extends BaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8451572530570568166L;
	private String dummyId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DummyModel other = (DummyModel) obj;
        if ((this.dummyId == null) ? (other.dummyId != null) : !this.dummyId.equals(other.dummyId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dummyId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.dummyId != null ? this.dummyId.hashCode() : 0);
        return hash;
    }
}
