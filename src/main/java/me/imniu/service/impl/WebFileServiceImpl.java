package me.imniu.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import me.imniu.mapper.WebFileMapper;
import me.imniu.po.WebFile;
import me.imniu.service.WebFileService;

/**
 * Description:
 *
 * @author NiuJinpeng
 * @date   2017年12月25日上午11:52:23
 */
@Service
public class WebFileServiceImpl implements WebFileService {
	
	private static Logger logger = Logger.getLogger(WebFileServiceImpl.class);
	
	@Autowired
	private WebFileMapper webFileMapper;

	/**
	 * 还在所有文件列表
	 */
	@Override
	public List<WebFile> selectList() throws Exception {
		return webFileMapper.selectList();
	}

	/**
	 * 根据文件ID查询文件内容，用于下载
	 */
	@Override
	public WebFile selectById(BigDecimal id) throws Exception {
		return webFileMapper.selectById(id);
	}

	/**
	 * 插入文件信息，用于上传
	 */
	@Override
	public int insert(MultipartFile uploadFile) throws Exception {
		// 判断是否 为空
		if(uploadFile.isEmpty() || uploadFile == null){
			return 0;
		}
		
		// 获取字节数组
		byte[] bytes = uploadFile.getBytes();
		
		// 获取大小
		long size = uploadFile.getSize();
		logger.info("The file size uploaded is :"+size);
		
		// 获取类型
		String contentType = uploadFile.getContentType();
		logger.info("The file content type is :"+contentType);
		
		// 获取文件名称
		String fileName = uploadFile.getOriginalFilename();
		
		// 创建上传实体类
		WebFile webFile = new WebFile();
		webFile.setFileName(fileName);
		webFile.setContentType(contentType);
		webFile.setContent(bytes);
		webFile.setFileSize(new BigDecimal(size));
		webFile.setUploadTime(new Date());
		webFile.setDownload(new BigDecimal(0));
		
		return webFileMapper.insert(webFile);
	}

	/**
	 * 根据ID删除文件
	 */
	@Override
	public int deleteById(BigDecimal id) throws Exception {
		return webFileMapper.deleteById(id);
	}

}
