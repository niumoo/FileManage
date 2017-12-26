package me.imniu.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import me.imniu.po.ResultMap;
import me.imniu.po.WebFile;
import me.imniu.service.WebFileService;

/**
 * Description:文件管理控制类
 *
 * @author NiuJinpeng
 * @date   2017年12月25日上午11:58:34
 */

@Controller
@RequestMapping("/file")
public class WebFileController {
	
	@Autowired
	private WebFileService webFileService;
	
	/**
	 * 加载文件列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list", method= {RequestMethod.GET})
	public String getList(Model model,HttpServletRequest request){
		try {
			List<WebFile> webFileList = webFileService.selectList();
			model.addAttribute("webFileList", webFileList);
			return  "file/list";
		} catch (Exception e) {
			e.printStackTrace();
			return  "file/error";
		}
	}
	
	/**
	 * 文件上传
	 * @param uploadFile
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/upload", method= {RequestMethod.POST})
	@ResponseBody
	public ResultMap upload(MultipartFile uploadFile, HttpServletRequest request){
		try {
			int insertResult = webFileService.insert(uploadFile);
			if(insertResult == 1){
				return new ResultMap(1, "{}", "文件已上传");
			}
			return new ResultMap(0, "{}", "文件上传失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMap(0, "{}", "文件上传失败");
	}
	
	/**
	 * 文件下载
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/download/{id}", method= {RequestMethod.GET})
	public void download(@PathVariable("id") BigDecimal id,HttpServletRequest request,HttpServletResponse response){
		try {
			WebFile webFile = webFileService.selectById(id);
			// 设置相应类型
			response.setContentType(webFile.getContentType());
			// 设置下载时文件名
			String fileName = URLEncoder.encode(webFile.getFileName(),"UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="+fileName);
			// 输出缓冲区内容
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(webFile.getContent());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件预览
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/preview/{id}", method= {RequestMethod.GET})
	public void preview(@PathVariable("id") BigDecimal id,HttpServletRequest request,HttpServletResponse response){
		try {
			WebFile webFile = webFileService.selectById(id);
			// 设置相应类型
			response.setContentType(webFile.getContentType());
			// 设置下载时文件名
			//String fileName = URLEncoder.encode(webFile.getFileName(),"UTF-8");
			//response.setHeader("Content-Disposition", "attachment; filename="+fileName);
			// 输出缓冲区内容
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(webFile.getContent());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/delete/{id}", method= {RequestMethod.POST})
	@ResponseBody
	public ResultMap delete(@PathVariable("id")BigDecimal id){
		try {
			int deleteResult = webFileService.deleteById(id);
			if(deleteResult == 1){
				return new ResultMap(1, "{}", "文件已上传");
			}
			return new ResultMap(0, "{}", "文件上传失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMap(0, "{}", "文件上传失败");
	}
	/**
	 * 上传文件过大的异常处理
	 * @param e
	 * @param request
	 * @return
	 * @throws Exception
	 */
//	@ExceptionHandler(Exception.class)
//	public @ResponseBody ResultMap doException(Exception e, HttpServletRequest request) throws Exception {
//		  if (e instanceof MaxUploadSizeExceededException) {  
//			  return new ResultMap(0,"{}","文件过大，最大大小限制为1Mb");
//		  }
//		return null;
//	}
//	
	

}
