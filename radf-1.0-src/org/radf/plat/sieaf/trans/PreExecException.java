package org.radf.plat.sieaf.trans;
import org.radf.plat.util.exception.WebException;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Bea</p>
 * @author KentKong
 * @version 1.0
 */

public class PreExecException extends WebException{

    public PreExecException(String s) {
        super(s);
    }
    public PreExecException(){
        super();
    }
}