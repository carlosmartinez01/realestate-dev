/**
 * 
 */
package com.maverik.realestate.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.maverik.realestate.domain.entity.Filename;
import com.maverik.realestate.view.bean.FileBean;

/**
 * @author jorge
 *
 */
@Mapper(componentModel = "spring", uses = { PreConstructionSpecialMapper.class })
public interface FileMapper {

    Filename fileBeanToFile(FileBean fileBean);

    FileBean fileToFileBean(Filename file);

    List<Filename> filesBeanToFiles(List<FileBean> filesBean);

    List<FileBean> filesToFilesBean(List<Filename> files);

}
