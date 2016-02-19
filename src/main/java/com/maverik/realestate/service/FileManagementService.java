/**
 * 
 */
package com.maverik.realestate.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author jorge
 *
 */
public interface FileManagementService {

    public String uploadPropertyFile(MultipartFile file) throws IOException;

    public String uploadFile(MultipartFile source, String target)
	    throws IOException;

    public String uploadLOIFile(MultipartFile file) throws IOException;

    public String uploadLeaseFile(MultipartFile file) throws IOException;

    public String uploadPurchaseFile(MultipartFile file) throws IOException;

    public String uploadPermittingFile(MultipartFile file) throws IOException;

}
