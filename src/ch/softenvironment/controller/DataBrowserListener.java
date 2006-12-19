package ch.softenvironment.controller;

/* 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */

/**
 * Listener for DataBrowser.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.1 $ $Date: 2006-12-19 09:58:03 $
 */
public interface DataBrowserListener {
    /**
     * Called when currentObject is to be removed.
     * @param object Object to be removed
     * @return veto
     */
    boolean removeObject(Object object);
    /**
     * Called before currentObject will be changed
     * to make sure any changes on still current Object
     * can be done.
     * @param param java.lang.Object Object to be invalidated
     * @return the saved object
     */
    Object saveChanges(Object object);
    /**
     * Called when currentObject becomes another one.
     * @param object New current Object
     * @see #saveChanges(Object)
     */
    void setCurrentObject(Object object);
}
