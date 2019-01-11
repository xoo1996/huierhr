package org.radf.manage.util;

/**
 * <p>Title: 系统管理模块错误码定义</p>
 * <p>Description:
 *     100101XXX   System Error
 *     100102XXX   Function Error
 *     100103XXX   Sync Error
 *     100104XXX   Transaction Manage
 *     100105XXX   ACL Manage
 *     100106XXX   ROLE Manage
 * </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Bea</p>
 * @author Kent Kong
 * @version 1.0
 */

public class ManageErrorCode {

	public static int MODIFY = 100105005;
	public static int FIND = 100105004;
	public static int CREATROLEGROUP = 100105003;
	public static int CREATEACT = 100105002;
	public static int CREATEACL = 100105001;
	public static int DBERROR = 100101001;
	public static int NOSUCHRECORD = 100101002;
	public static int INPUTDATA_INVALID = 100101003;

	/**
	 * 企图删除有子功能的功能节点
	 */
	public static int HASCHILDFUNCTIONS = 100102001;

	public static int CREATECONTROL = 100102002;

	public static int CREATEFUNCTION = 100102003;

	public static int CREATEMAP = 100102004;

	public static int FINDCONTROLBYPK = 100102005;

	public static int FINDFUNCTIONBYPK = 100102006;

	public static int GETCHILDPAIRS = 100102007;

	public static int MODIFYCONTROL = 100102008;

	public static int MODIFYFUNCTION = 100102009;

	public static int REMOVECONTROL = 100102010;

	public static int REMOVEFUNCTION = 100102011;

	public static int REMOVEMAP = 100102012;

	public static int HASCHILDFUNCTIONS_P = 100102013;

	public static int CREATECONTROL_P = 100102013;

	public static int CREATEFUNCTION_P = 100102013;

	public static int CREATEMAP_P = 100102013;

	public static int FINDCONTROLBYPK_P = 100102013;

	public static int FINDFUNCTIONBYPK_P = 100102013;

	public static int GETCHILDPAIRS_P = 100102013;

	public static int MODIFYCONTROL_P = 100102013;

	public static int MODIFYFUNCTION_P = 100102013;

	public static int REMOVECONTROL_P = 100102013;

	public static int REMOVEFUNCTION_P = 100102013;

	public static int REMOVEMAP_P = 100102013;

	public static int FINDNEWVERSION = 100102025;

	public static int FINDNEWVERSION_P = 100102013;

	public static int CREATETRANS = 100102027;

	public static int CREATETRANS_P = 100102013;

	public static int FINDALLTRANS = 100102029;

	public static int FINDALLTRANS_P = 100102013;

	public static int FINDINOUTPUTBYPK = 100102031;

	public static int FINDINOUTPUTBYPK_P = 100102013;

	public static int FINDTRANSLOGBACK = 100102033;

	public static int FINDTRANSLOGBACK_P = 100102013;

	public static int FINDTRANSLOGBYPK = 100102035;

	public static int FINDTRANSLOGBYPK_P = 100102013;

	public static int FINDTRANSLOGFORWARD = 100102037;

	public static int FINDTRANSLOGFORWARD_P = 100102013;

	public static int MODIFYTRANS = 100102039;

	public static int MODIFYTRANS_P = 100102013;

	public static int REMOVETRANS = 100102041;

	public static int REMOVETRANS_P = 100102013;

	public static int FINDTRANSBYPK = 100102043;

	public static int FINDTRANSBYPK_P = 100102013;

	public static int FINDCONTROLBYPK_N = 100102045;

	public static int FINDFUNCTIONBYPK_N = 100102045;

	public static int FINDNEWVERSION_N = 100102045;

	public static int FINDINOUTPUTBYPK_N = 100102045;

	public static int FINDTRANSLOGBYPK_N = 100102045;

	public static int FINDTRANSBYPK_N = 100102045;

	public static int FINDTRANSLOGPERPAGE = 100102046;

	public static int FINDTRANSLOGPERPAGE_P = 100102013;

	/**
	* 日志错误代码
	*/
	public static int REMOVELOGERROR = 100110001;
	public static int FINDLOGERROR = 100110002;
	public static int FINDLOGBYFUNCTIONIDERROR = 100110003;
	public static int FINDLOGBYMESSAGEERROR = 100110004;
	public static int FINDLOGBYMSGDATEERROR = 100110005;
	public static int FINDLOGBYUSERIDERROR = 100110006;
	public static int SQLERROR = 100102047;
}
