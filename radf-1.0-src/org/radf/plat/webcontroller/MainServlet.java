package org.radf.plat.webcontroller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radf.manage.param.common.OptionDictSelect;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.PagerUtil;
import org.radf.plat.commons.SystemConfigLoad;
import org.radf.plat.log.LogHelper;
import org.radf.plat.log.LogUtil;
import org.radf.plat.sieaf.FunctionCache;
import org.radf.plat.sieaf.RequestProcessor;
import org.radf.plat.sieaf.actionmapping.ActionMapping;
import org.radf.plat.sieaf.actionmapping.ActionmappingDAOFactory;
import org.radf.plat.sieaf.actionmapping.dao.ActionmappingDAO;
import org.radf.plat.sieaf.namemapping.NameMapping;
import org.radf.plat.sieaf.namemapping.NameMappingDAO;
import org.radf.plat.sieaf.trans.TransDefCache;
import org.radf.plat.util.FacadeFactory;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

/**
 * 框架的c/s/s模式以及b/s/s模式总控类，客户端XML交易的处理
 * 
 * @author Tony
 * @version 1.0
 */
public class MainServlet extends HttpServlet {
	private String version = "1.0(build 2009.3.28)";

	private ActionMapping actionMappings;

	private RequestProcessor processor = null;

	/*
	 * 初始化系统，内容包括log4j，mappings，ejb facade等
	 */
	public void init() {
        int errCode = 0;
        System.out.println("Rapid Application Development Framework(RADF)");
		System.out.println("RADF Version " + version);
        System.out.println("Begin RADF init......");
        try{
            // 系统运行参数
            errCode = 1;
            initConfig();
            
            // ServletContext初始化
            errCode = 2;
            initRequestProcessor();
            
            // 日志初始化
            errCode = 3;
            initLogUtil();
            
            // 数据库初始化
            errCode = 4;
            initDBUtil();
            
            // 获取Facade对应的IMP定义
            errCode = 5;
            initFacadeCache();
            
            // 获取Action定义        
            errCode = 6;
            initActionMappings();
            
            // 加载namemapping
            errCode = 7;
            initNameMapings();
            
            // 加载业务参数数据
            errCode = 8;
            initParaUtil();
            
            // 加载公共参数
            if (GlobalNames.PARAM_FLAG) {
                errCode = 9;
                OptionDictSelect.init();
            }
            
            if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
                System.out.println(">>>加载 SystemParameter....");
            }
            errCode = 10;
            SystemParameter.init();

        }catch(Exception e){
            String msg = buildErrMsg(errCode);
            System.out.println("系统启动时初始化环境变量出错："+msg);
            e.printStackTrace();
        }
        
		if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
			System.out.println("End RADF init......");
		}
	}

	/**
	 * 处理get请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doSIEAFProcess(request, response);
	}

	/**
	 * 处理post请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doSIEAFProcess(request, response);
	}

	/**
	 * SIEAF框架模式的客户端doGet和doPost请求处理
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void doSIEAFProcess(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		long inTime = 0;

		if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
			inTime = System.currentTimeMillis();
			System.out.println("******** System response time Start = "
					+ new java.sql.Date(inTime));
		}

		String result = processor.processRequest(request);

		// 处理中文字符集
		response.setContentType("text/html;charset=GBK");
		// 向客户端返回结果
		PrintWriter out = response.getWriter();
		out.println(result);

		if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
			System.out.println("this is xmlString to client:");
			System.out.println(result);
		}
		out.close();

		if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
			long outTime = System.currentTimeMillis();
			System.out.println("******** System response time EndTime = "
					+ new java.sql.Date(outTime) + "Interval = "
					+ (outTime - inTime));
		}
	}

	/**
	 * 初始化配置信息
	 */
	private void initConfig() {
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println("加载SystemConfig......");
        }

        // 获取系统配置文件路径
        GlobalNames.WEB_PATH = getServletContext().getRealPath("");
        // 加载配置文件信息
        SystemConfigLoad.load("/");

        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.print("当前应用标志：" + GlobalNames.PATH_CONFIG_INDEX);
            System.out.println("。应用所在的文件系统路径：" + GlobalNames.WEB_PATH);
        }
	}

	/**
	 * 初始化FunctionID与Action对应关系 其文件名称定义在lobalNames.FILE_MAPPINGS_URL中
	 */
	private void initActionMappings() {
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println(">>>加载ActionMappings....");
        }
		if (GlobalNames.SERVICE_ACTION_MAPING.equals("1")) {
			InputStream is = SystemConfigLoad.class
					.getResourceAsStream(GlobalNames.PATH_CONFIG_URL
							+ GlobalNames.FILE_MAPPINGS_URL);
			if (is != null) {
				ActionmappingDAO dao = ActionmappingDAOFactory.getDAO();
				actionMappings = dao.getActionMapping(is);
				getServletContext().setAttribute(GlobalNames.ACTION_MAPPINGS,
						actionMappings);

				TransDefCache transDefCache = new TransDefCache();
				transDefCache.init();
				getServletContext().setAttribute(
						GlobalNames.TRANSACTION_DEF_CACHE, transDefCache);
				if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
					System.out.println("trans init finish");
				}
				// 获取功能模块functionid的认证证书
				try {
					FunctionCache functionCache = new FunctionCache();
					getServletContext().setAttribute(
							GlobalNames.FUNCTION_CACHE, functionCache);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * 加载NameMapings
	 */
	private void initNameMapings() {
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println(">>>加载NameMapping....");
        }
		InputStream is = SystemConfigLoad.class
				.getResourceAsStream(GlobalNames.PATH_CONFIG_URL
						+ GlobalNames.FILE_NAMEMAP_URL);
		if (is != null) {
			try {
				NameMappingDAO nmdao = new NameMappingDAO();
				NameMapping nameMapping = nmdao.getNameMapping(is);
				getServletContext().setAttribute("NAME_MAPPINGS", nameMapping);
			} catch (Exception e) {
				LogHelper log = new LogHelper(this.getClass().getName());
				log.log(null, 300001, e.getMessage());
			}
		}
	}

	/**
	 * 初始化Facade和IMP映射关系
	 */
	private void initFacadeCache() {
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println(">>>加载FacadeCache....");
        }
		try {
			FacadeFactory.init();
		} catch (AppException ne) {
			LogHelper log = new LogHelper(this.getClass().getName());
			log.log(null, 300001, ne.getMessage());
		}
	}

	private void initRequestProcessor() {
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println(">>>加载RequestProcessor....");
        }
		processor = new RequestProcessor();
		processor.init(getServletContext());
	}

	/**
	 * 日志初始化
	 */
	private void initLogUtil() {
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println(">>>加载 LogUtil....");
        }
		LogUtil.init();
	}

	/**
	 * 数据库初始化
	 */
	private void initDBUtil() {
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println(">>>加载DBUtil....");
        }
		DBUtil.init();// db = null;
	}

	/**
	 * 加载系统公共的业务参数
	 */
	private void initParaUtil() {
		// 公共权限参数
		Vector list = new Vector();
		list.add("F00");
		GlobalNames.ROLE_PUBLIC_ALL = list;
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println(">>>加载PagerUtil......");
        }
        //
        PagerUtil.init();
	}
    private String buildErrMsg(int errCode){
        String msg = null;
        switch(errCode){
            case 1:
                msg = "加载initConfig出错";
                break;
            case 2:
                msg = "加载initRequestProcessor出错";
                break;
            case 3:
                msg = "加载initLogUtil出错";
                break;
            case 4:
                msg = "加载initDBUtil出错";
                break;
            case 5:
                msg = "加载initFacadeCache出错";
                break;
            case 6:
                msg = "加载initActionMappings出错";
                break;
            case 7:
                msg = "加载initFacadeCache出错";
                break;
            case 8:
                msg = "加载initParaUtil出错";
                break;
            case 9:
                msg = "加载OptionDictSelect出错";
                break;
            case 10:
                msg = "加载SystemParameter出错";
                break;
            default:
                msg = "未发现的异常错误";
        }
        return msg;
    }
}
