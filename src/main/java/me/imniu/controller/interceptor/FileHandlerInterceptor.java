package me.imniu.controller.interceptor;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import me.imniu.po.ResultMap;

/**
 * Description:文件大小拦截
 *
 * @author NiuJinpeng
 * @date 2017年12月22日上午10:46:18
 */
public class FileHandlerInterceptor implements HandlerInterceptor {
	
	private static Logger logger = Logger.getLogger(FileHandlerInterceptor.class);
	private long maxUploadSize;

	/**
	 * 进入 Handler方法之前执行 用于身份认证、身份授权 比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		logger.info("进入文件上传拦截器");

		// 获取请求路径
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		String url = uri.replace(contextPath, "");
		
		// 如果是文件上传
		if(request!=null && ServletFileUpload.isMultipartContent(request)) {
            ServletRequestContext ctx = new ServletRequestContext(request);
            long requestSize = ctx.contentLength();
            logger.info("当前上传的文件大小为："+requestSize);
            if (requestSize > maxUploadSize) {
            	ResultMap resultMap = new ResultMap(0,"{}","文件过大，最大大小限制为1Mb");
            	ObjectMapper mapper = new ObjectMapper();
            	String json = mapper.writeValueAsString(resultMap);
            	
            	response.setContentType("text/html;charset=UTF-8");
            	PrintWriter out = response.getWriter();
            	out.print(json);
            	//throw new MaxUploadSizeExceededException(maxUploadSize);
            	return false;
            }
        }
		return true;
		
	}

	// 进入Handler方法之后，返回modelAndView之前执行
	// 应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// 执行Handler完成执行此方法
	// 应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	public long getMaxUploadSize() {
		return maxUploadSize;
	}

	public void setMaxUploadSize(long maxUploadSize) {
		this.maxUploadSize = maxUploadSize;
	}
	
	

}
