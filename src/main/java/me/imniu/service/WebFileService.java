package me.imniu.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import me.imniu.po.WebFile;

/**
 * Description:文件管理的业务逻辑接口
 *
 * @author NiuJinpeng
 * @date   2017年12月25日上午11:51:28
 */
public interface WebFileService {
	
	/**
	 * 查询所有上传的文件的信息，不加载文件内容
	 * @return
	 * @throws Exception
	 */
	List<WebFile> selectList()throws Exception;
	
	/**
	 * 根据Id查询文件信息，加载所有信息，可用于文件下载
	 * @return
	 * @throws Exception
	 */
	WebFile selectById(BigDecimal id)throws Exception;

	/**
	 * 插入文件信息，用于上传保存
	 * @param record
	 * @return
	 */
	int insert(MultipartFile uploadFile)throws Exception;
	
	/**
	 * 根据ID删除文件信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(BigDecimal id)throws Exception;
}
