package org.radf.plat.sieaf.trans;

/**
 * <p>Title: </p>
 * <p>Description: this interface define all variables used by org.radf.plat.trans package</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: LBS</p>
 * @author lsq
 * @version 1.0
 */

public interface TransUtil {

    /**
     * Transaction begins.
     */
    String BEGIN = "01";


    /**
     * Transaction ends. The db has been updated, but the EJB's result doesn't
     * return to the client side yet.
     */
    String DB_UPDATED = "02";

    /**
     * EJB's result has returned to the client side.
     */
    String RESULT_RETURN = "03";

    /**
     * Transaction fails.
     */
    String FAILED = "04";

    /**
     * Transaction timeouts.
     */
    String TIMEOUT = "05";

    /**
     * Transaction has been redoed successfully
     */
    String REDO_SUCCESS = "06";
}