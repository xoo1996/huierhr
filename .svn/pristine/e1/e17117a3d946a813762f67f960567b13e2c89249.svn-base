package org.radf.plat.util.exception;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Bea</p>
 * @author KentKong
 * @version 1.0
 */

public class SafeException extends WebException{

    public SafeException() {
        super();
    }

    public SafeException(String s){
        super(s);
    }

    public static String getDesc(int code){
        int i1 = 0x82040000, i2 = 0x82040002, i3 = 0x82040003, i4 = 0x82040004;
        int i5 = 0x82040010, i6 = 0x8204000F, i7 = 0x82040006, i8 = 0x82040012;
        int i9 = 0x82040015;
        String desc = "";
        if(code == i1)
            desc = "装载动态库出错";
        else if(code == i2)
            desc = "读写私钥设备失败";
        else if(code == i3)
            desc = "私钥密码错误";
        else if(code == i4)
            desc = "读写根证书设备失败";
        else if(code == i5)
            desc = "数字信封错误";
        else if(code == i6)
            desc = "证书无效";
        else if(code == i7)
            desc = "读证书失败";
        else if(code == i8)
            desc = "有效期外";
        else if(code == i9)
            desc = "非ca签发";
        return desc;

    }


}
