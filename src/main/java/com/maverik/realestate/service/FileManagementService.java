/**
 * 
 */
package com.maverik.realestate.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.maverik.realestate.view.bean.FileBean;

/**
 * @author jorge
 *
 */
public interface FileManagementService {

    public FileBean uploadPropertyFile(MultipartFile file) throws IOException;

    public FileBean uploadFile(MultipartFile source, String target,
	    String outputDir) throws IOException;

    public FileBean uploadLOIFile(MultipartFile file) throws IOException;

    public FileBean uploadLeaseFile(MultipartFile file) throws IOException;

    public FileBean uploadPurchaseFile(MultipartFile file) throws IOException;

    public FileBean uploadPermittingFile(MultipartFile file) throws IOException;

    public FileBean uploadPreConstructionDetailFile(MultipartFile file)
	    throws IOException;

    public FileBean uploadPreConstructionPermitFile(MultipartFile file)
	    throws IOException;

    public FileBean uploadRFIFile(MultipartFile file) throws IOException;

    public FileBean uploadASIFile(MultipartFile file) throws IOException;

    public FileBean uploadManagementBudgetFile(MultipartFile file)
	    throws IOException;

    public FileBean uploadCloseOutFile(MultipartFile file) throws IOException;
}
