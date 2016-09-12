package org.rehab.app.interfaces;

/**
 * Interface is used for common purpose in Application.
 *
 * @author and15031989
 */
public interface IASCommon {
    /**
     * Method for getting the type and data.
     *
     * @param type request type
     * @param data Actual data
     */
    void onResponse(Object type, Object data);

}
