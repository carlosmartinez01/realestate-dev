/**
 * 
 */
package com.maverik.realestate.view.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.maverik.realestate.exception.GenericException;
import com.maverik.realestate.response.FileResponse;
import com.maverik.realestate.response.GenericResponse;
import com.maverik.realestate.service.FileManagementService;
import com.maverik.realestate.service.ProjectManagementService;
import com.maverik.realestate.service.PropertyManagementService;
import com.maverik.realestate.view.bean.FileBean;

/**
 * @author jorge
 *
 */

@Controller
@RequestMapping(value = "/filemanager")
public class FileManagerController {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(FileManagerController.class);

    private static final String SUCCESS_MESSAGE = "You successfully uploaded ";

    private static final String FAILED_MESSAGE = "Unable to upload file ";

    @Autowired
    private FileManagementService fileService;

    @Autowired
    private PropertyManagementService propertyManagementService;

    @Autowired
    private ProjectManagementService projectManagementService;

    @RequestMapping(value = "/property/picture/{propertyId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPropertyPicture(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long propertyId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading property picture {} to server", file);

	FileBean fileBean = new FileBean();
	FileResponse response = new FileResponse();
	try {
	    fileBean = fileService.uploadPropertyFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    propertyManagementService.uploadPropertyPicture(fileBean,
		    propertyId);
	    response.setFilename(fileBean.getAbsolutePath());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/property/recso/{contractId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPropertyContractRecsoFile(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long contractId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading property contract RECSO file {} to server", file);

	FileBean fileBean = new FileBean();
	FileResponse response = new FileResponse();
	try {
	    fileBean = fileService.uploadPropertyFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    propertyManagementService.addRECSOFile(fileBean, contractId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/property/titleCommitment/{contractId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPropertyContractTitleCommFile(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long contractId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info(
		"Uploading property contract Title Commitment file {} to server",
		file);

	FileBean fileBean = new FileBean();
	FileResponse response = new FileResponse();
	try {
	    fileBean = fileService.uploadPropertyFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    propertyManagementService.addTitleCommitmentFile(fileBean,
		    contractId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/property/titlePolicy/{contractId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPropertyContractTitlePolicyFile(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long contractId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info(
		"Uploading property contract Title Policy file {} to server",
		file);

	FileBean fileBean = new FileBean();
	FileResponse response = new FileResponse();
	try {
	    fileBean = fileService.uploadPropertyFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    propertyManagementService.addTitlePolicyFile(fileBean, contractId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/property/settlement/{contractId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPropertySettlementFile(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long contractId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading property contract Settlement file {} to server",
		file);

	FileBean fileBean = new FileBean();
	FileResponse response = new FileResponse();
	try {
	    fileBean = fileService.uploadPropertyFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    propertyManagementService.addSettlementFile(fileBean, contractId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/property/{propertyId}/loi/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPropertyLOIFile(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long propertyId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading property lOI file {} to server", file);

	FileBean fileBean = new FileBean();
	GenericResponse response = new GenericResponse();
	try {
	    fileBean = fileService.uploadLOIFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    propertyManagementService.createLOIAndAddFile(fileBean, propertyId);
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/property/{propertyId}/lease/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPropertyLeaseFile(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long propertyId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading property lease file {} to server", file);

	FileBean fileBean = new FileBean();
	GenericResponse response = new GenericResponse();
	try {
	    fileBean = fileService.uploadLeaseFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    response.setSuccessMessage("You successfully uploaded Lease file "
		    + fileBean.getName() + "!");
	    propertyManagementService.createLeaseAndAddFile(fileBean,
		    propertyId);
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/property/{propertyId}/purchase/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPropertyPurchaseFile(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long propertyId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading property purchase file {} to server", file);

	FileBean fileBean = new FileBean();
	GenericResponse response = new GenericResponse();
	try {
	    fileBean = fileService.uploadPurchaseFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    response.setSuccessMessage("You successfully uploaded Purchase file "
		    + fileBean.getName() + "!");
	    propertyManagementService.createPurchaseAndAddFile(fileBean,
		    propertyId);
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/permitting/commitment/{permittingId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPermittingCommitmentFiles(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long permittingId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading permitting commitment file {} to server", file);

	FileResponse response = new FileResponse();
	FileBean fileBean = null;
	try {
	    fileBean = uploadPermittingFiles(file);
	    propertyManagementService.addPermittingCommitmentFile(fileBean,
		    permittingId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/permitting/exception/{permittingId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPermittingExceptionFiles(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long permittingId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading permitting exception file {} to server", file);

	FileResponse response = new FileResponse();
	FileBean fileBean = null;
	try {
	    fileBean = uploadPermittingFiles(file);
	    propertyManagementService.addPermittingExceptionFile(fileBean,
		    permittingId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/permitting/survey/{permittingId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPermittingSurveyFiles(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long permittingId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading permitting survey file {} to server", file);

	FileResponse response = new FileResponse();
	FileBean fileBean = null;
	try {
	    fileBean = uploadPermittingFiles(file);
	    propertyManagementService.addPermittingSurveryFile(fileBean,
		    permittingId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/permitting/sitePlan/{permittingId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPermittingSitePlanFiles(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long permittingId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading permitting site plan file {} to server", file);

	FileResponse response = new FileResponse();
	FileBean fileBean = null;
	try {
	    fileBean = uploadPermittingFiles(file);
	    propertyManagementService.addPermittingSitePlanFile(fileBean,
		    permittingId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/permitting/geotechnical/{permittingId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPermittingGeotechnicalFiles(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long permittingId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading permitting geotechnical file {} to server", file);

	FileResponse response = new FileResponse();
	FileBean fileBean = null;
	try {
	    fileBean = uploadPermittingFiles(file);
	    propertyManagementService.addPermittingGeotechnicalFile(fileBean,
		    permittingId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    @RequestMapping(value = "/permitting/traffic/{permittingId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse uploadPermittingTrafficFiles(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long permittingId)
	    throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading permitting traffic study file {} to server",
		file);

	FileResponse response = new FileResponse();
	FileBean fileBean = null;
	try {
	    fileBean = uploadPermittingFiles(file);
	    propertyManagementService.addPermittingTrafficFile(fileBean,
		    permittingId);
	    response.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	    response.setFilename(fileBean.getName());
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    response.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return response;
    }

    private FileBean uploadPermittingFiles(MultipartFile file)
	    throws IOException {
	LOGGER.info("uploadPermittingFiles({})", file.getContentType());

	FileBean fileBean = new FileBean();
	fileBean = fileService.uploadPermittingFile(file);
	fileBean.setName(file.getOriginalFilename());

	return fileBean;
    }

    @RequestMapping(value = "/preconstruction/{detailId}/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileBean uploadPreConstructionDetailFiles(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long detailId) throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading preconsturction detail file {} to server", file);

	FileBean fileBean = new FileBean();
	try {
	    fileBean = fileService.uploadPreConstructionDetailFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    fileBean = projectManagementService.addPreConstructionDetailFile(
		    fileBean, detailId);
	    fileBean.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    fileBean.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return fileBean;
    }

    @RequestMapping(value = "/preconstruction/{detailId}/permit/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileBean uploadPreConstructionFile(
	    @RequestParam("file") MultipartFile file,
	    @PathVariable Long detailId) throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading preconsturction permit file {} to server", file);

	FileBean fileBean = new FileBean();
	try {
	    fileBean = fileService.uploadPreConstructionPermitFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    fileBean = projectManagementService.addPreConstructionPermitFile(
		    fileBean, detailId);
	    fileBean.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    fileBean.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return fileBean;
    }

    @RequestMapping(value = "/rfi/{projectId}/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileBean uploadProjectRFIFile(
	    @RequestParam("file") MultipartFile file,
	    @RequestParam(value = "rfiId") Long rfiId,
	    @PathVariable Long projectId) throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading Project RFI file {} to server", file);

	FileBean fileBean = new FileBean();
	try {
	    fileBean = fileService.uploadRFIFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    fileBean = projectManagementService.addRFIFile(fileBean, rfiId,
		    projectId);
	    fileBean.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    fileBean.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return fileBean;
    }

    @RequestMapping(value = "/asi/{projectId}/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileBean uploadProjectASIFile(
	    @RequestParam("file") MultipartFile file,
	    @RequestParam(value = "asiId") Long asiId,
	    @PathVariable Long projectId) throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading Project ASI file {} to server", file);

	FileBean fileBean = new FileBean();
	try {
	    fileBean = fileService.uploadASIFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    fileBean = projectManagementService.addASIFile(fileBean, asiId,
		    projectId);
	    fileBean.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    fileBean.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return fileBean;
    }

    @RequestMapping(value = "/management/budget/{projectId}/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileBean uploadManagementBudgetFile(
	    @RequestParam("file") MultipartFile file,
	    @RequestParam(value = "managementId") Long managementId,
	    @PathVariable Long projectId) throws MaxUploadSizeExceededException {
	LOGGER.info("Uploading Project Management Budget file {} to server",
		file);

	FileBean fileBean = new FileBean();
	try {
	    fileBean = fileService.uploadManagementBudgetFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    fileBean = projectManagementService.addConstructionBudgetFile(
		    fileBean, managementId, projectId);
	    fileBean.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    fileBean.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return fileBean;
    }

    @RequestMapping(value = "/close-out/redlines/{projectId}/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileBean uploadRedlinesFile(
	    @RequestParam("file") MultipartFile file,
	    @RequestParam(value = "closeOutId") Long closeOutId,
	    @PathVariable Long projectId) throws IOException, GenericException,
	    MaxUploadSizeExceededException {
	LOGGER.info("Uploading Project Close Out Red lines file {} to server",
		file);

	FileBean fileBean = new FileBean();
	try {
	    fileBean = fileService.uploadCloseOutFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    fileBean = projectManagementService.addRedlinesFile(fileBean,
		    closeOutId, projectId);
	    fileBean.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    fileBean.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return fileBean;
    }

    @RequestMapping(value = "/close-out/contractor/{projectId}/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileBean uploadGeneralContractorFile(
	    @RequestParam("file") MultipartFile file,
	    @RequestParam(value = "closeOutId") Long closeOutId,
	    @PathVariable Long projectId) throws IOException, GenericException,
	    MaxUploadSizeExceededException {
	LOGGER.info(
		"Uploading Project Close Out General Contractor file {} to server",
		file);

	FileBean fileBean = new FileBean();
	try {
	    fileBean = fileService.uploadCloseOutFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    fileBean = projectManagementService.addGeneralContractorFile(
		    fileBean, closeOutId, projectId);
	    fileBean.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    fileBean.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return fileBean;
    }

    @RequestMapping(value = "/close-out/punch-list/{projectId}/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public FileBean uploadPunchListItemsFile(
	    @RequestParam("file") MultipartFile file,
	    @RequestParam(value = "closeOutId") Long closeOutId,
	    @PathVariable Long projectId) throws IOException, GenericException,
	    MaxUploadSizeExceededException {
	LOGGER.info(
		"Uploading Project Close Out Punch List Items file {} to server",
		file);

	FileBean fileBean = new FileBean();
	try {
	    fileBean = fileService.uploadCloseOutFile(file);
	    fileBean.setName(file.getOriginalFilename());
	    fileBean = projectManagementService.addPunchListItemsFile(fileBean,
		    closeOutId, projectId);
	    fileBean.setSuccessMessage(SUCCESS_MESSAGE + fileBean.getName()
		    + "!");
	} catch (IOException | GenericException e) {
	    LOGGER.info("" + e);
	    fileBean.setErrorMessage(FAILED_MESSAGE + fileBean.getName()
		    + " => " + e.getMessage());
	}

	return fileBean;
    }
}