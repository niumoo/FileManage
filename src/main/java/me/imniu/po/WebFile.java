package me.imniu.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Description:文件实体类
 *
 * @author NiuJinpeng
 * @date   2017年12月25日上午11:43:51
 */
public class WebFile {
	
    private BigDecimal id;

    private String fileName;

    private Date uploadTime;

    private BigDecimal download;

    private String contentType;

    private BigDecimal fileSize;

    private byte[] content;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public BigDecimal getDownload() {
        return download;
    }

    public void setDownload(BigDecimal download) {
        this.download = download;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}