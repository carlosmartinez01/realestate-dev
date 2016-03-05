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

import com.maverik.realestate.view.bean.FileBean;

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

    @Value("${preconstruction.detail.file}")
    private String preConstructionDetail;

    @Value("${preconstruction.permit.file}")
    private String preConstructionPermit;

    @Value("${webapp.dir}")
    private String webappDir;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadPictureFile()
     */
    @Override
    public FileBean uploadPropertyFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadPictureFile({})", file);

	FileBean bean = uploadFile(file, propertyFileLocation, "/property/");
	bean.setAbsolutePath(bean.getAbsolutePath());
	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadFile(java.
     * lang.String, java.lang.String)
     */
    @Override
    public FileBean uploadFile(MultipartFile source, String target,
	    String outputDir) throws IOException {
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
	FileBean fileBean = new FileBean();
	fileBean.setAbsolutePath(webappDir + outputDir + localFile.getName());

	return fileBean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadLOIFile(org
     * .springframework.web.multipart.MultipartFile)
     */
    @Override
    public FileBean uploadLOIFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadLOIFile({})", file);

	return uploadFile(file, propertyFileLOILocation, "/property/loi/");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadLeaseFile(
     * org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public FileBean uploadLeaseFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadLeaseFile({})", file);

	return uploadFile(file, propertyFileLeaseLocation, "/property/lease/");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadPurchaseFile
     * (org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public FileBean uploadPurchaseFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadPurchaseFile({})", file);

	return uploadFile(file, propertyFilePurchaseLocation,
		"/property/purchase/");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.maverik.realestate.service.FileManagementService#uploadPermittingFile
     * (org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public FileBean uploadPermittingFile(MultipartFile file) throws IOException {
	LOGGER.info("uploadPermittingFile({})", file);

	return uploadFile(file, propertyFilePermitting, "/property/permitting/");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.FileManagementService#
     * uploadPreConstructionDetailFile
     * (org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public FileBean uploadPreConstructionDetailFile(MultipartFile file)
	    throws IOException {
	LOGGER.info("uploadPreConstructionDetailFile({})", file);

	FileBean bean = uploadFile(file, preConstructionDetail,
		"/project/preconstruction/detail/");
	bean.setAbsolutePath(bean.getAbsolutePath());

	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.maverik.realestate.service.FileManagementService#
     * uploadPreConstructionPermitFile
     * (org.springframework.web.multipart.MultipartFile)
     */
    @Override
    public FileBean uploadPreConstructionPermitFile(MultipartFile file)
	    throws IOException {
	LOGGER.info("uploadPreConstructionDetailFile({})", file);

	FileBean bean = uploadFile(file, preConstructionPermit,
		"/project/preconstruction/permit/");
	bean.setAbsolutePath(bean.getAbsolutePath());

	return bean;
    }
}