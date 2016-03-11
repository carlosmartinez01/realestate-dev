package com.maverik.realestate.view.bean;

import java.io.Serializable;
import java.util.Objects;

public class ProjectCloseOutBean implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1929316304337277341L;

  private Long id;

  private Long project;

  private String redlines;

  private FileBean redlinesFile;

  private String generalContractorWarranties;

  private FileBean generalContractorFile;

  private String punchListComplete;

  private FileBean punchListItemsFile;

  private String utilitiesTurnover;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getProject() {
    return project;
  }

  public void setProject(Long project) {
    this.project = project;
  }

  public String getRedlines() {
    return redlines;
  }

  public void setRedlines(String redlines) {
    this.redlines = redlines;
  }

  public FileBean getRedlinesFile() {
    return redlinesFile;
  }

  public void setRedlinesFile(FileBean redlinesFile) {
    this.redlinesFile = redlinesFile;
  }

  public String getGeneralContractorWarranties() {
    return generalContractorWarranties;
  }

  public void setGeneralContractorWarranties(String generalContractorWarranties) {
    this.generalContractorWarranties = generalContractorWarranties;
  }

  public FileBean getGeneralContractorFile() {
    return generalContractorFile;
  }

  public void setGeneralContractorFile(FileBean generalContractorFile) {
    this.generalContractorFile = generalContractorFile;
  }

  public String getPunchListComplete() {
    return punchListComplete;
  }

  public void setPunchListComplete(String punchListComplete) {
    this.punchListComplete = punchListComplete;
  }

  public FileBean getPunchListItemsFile() {
    return punchListItemsFile;
  }

  public void setPunchListItemsFile(FileBean punchListItemsFile) {
    this.punchListItemsFile = punchListItemsFile;
  }

  public String getUtilitiesTurnover() {
    return utilitiesTurnover;
  }

  public void setUtilitiesTurnover(String utilitiesTurnover) {
    this.utilitiesTurnover = utilitiesTurnover;
  }

  @Override
  public String toString() {
    return "ProjectCloseOut [id=" + id + ", project=" + project + ", redlines=" + redlines
        + ", redlinesFile=" + redlinesFile + ", generalContractorWarranties="
        + generalContractorWarranties + ", generalContractorFile=" + generalContractorFile
        + ", punchListComplete=" + punchListComplete + ", punchListItemsFile=" + punchListItemsFile
        + ", utilitiesTurnover=" + utilitiesTurnover + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.project);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final ProjectCloseOutBean other = (ProjectCloseOutBean) obj;
    return Objects.equals(this.id, other.id) && Objects.equals(this.project, other.project);
  }

}
