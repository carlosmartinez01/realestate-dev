/**
 * 
 */
package com.maverik.realestate.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jorge
 *
 */
@Service
public class FileManagementServiceImpl implements FileManagementService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(FileManagementServiceImpl.class);

    @Value("${property.file}")
    private String propertyFileLocation;

    @Value("${property.loi.file}")
    private String propertyFileLOILocation;

    @Value("${property.lease.file}")
    private String propertyFileLeaseLocation;

    @Value("${property.purchase.file}")
    private String propertyFilePurchaseLocation;

    @Value("${property.permitting.file}")
    private String propertyFilePermitting;

    @Value("${webapp.dir}")
    private String webappDir;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadPictureFile()
     */
    @Override
    public String uploadPropertyFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadPictureFile({})", file);

	return webappDir + "/property/"
		+ uploadFile(file, propertyFileLocation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadFile(java.
     * lang.String, java.lang.String)
     */
    @Override
    public String uploadFile(MultipartFile source, String target)
	    throws IOException {
	LOGGER.info("uploadFile({},{})", source.getOriginalFilename(), target);

	File dir = new File(target);
	if (!dir.exists()) {
	    dir.mkdirs();
	}
	File localFile = new File(dir.getAbsolutePath() + File.separator
		+ System.currentTimeMillis() + "_"
		+ source.getOriginalFilename());
	byte[] bytes = source.getBytes();
	BufferedOutputStream stream = new BufferedOutputStream(
		new FileOutputStream(localFile));
	stream.write(bytes);
	stream.close();

	return localFile.getName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadLOIFile(org
     * .springframework.web.multipart.MultipartFile)
     */
    @Override
    public String uploadLOIFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadLOIFile({})", file);

	return uploadFile(file, propertyFileLOILocation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadLeaseFile(
     * org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public String uploadLeaseFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadLeaseFile({})", file);

	return uploadFile(file, propertyFileLeaseLocation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadPurchaseFile
     * (org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public String uploadPurchaseFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadPurchaseFile({})", file);

	return uploadFile(file, propertyFilePurchaseLocation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadPermittingFile
     * (org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public String uploadPermittingFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadPermittingFile({})", file);

	return uploadFile(file, propertyFilePermitting);
    }

}
