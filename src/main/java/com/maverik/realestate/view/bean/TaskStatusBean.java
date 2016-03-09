/**
 * 
 */
package com.maverik.realestate.view.bean;

import java.util.List;

/**
 * @author jorge
 *
 */
public class TaskStatusBean {

    private List<Long> completedTasksId;

    private List<Long> incompletedTasksId;

    public List<Long> getCompletedTasksId() {
	return completedTasksId;
    }

    public void setCompletedTasksId(List<Long> completedTasksId) {
	this.completedTasksId = completedTasksId;
    }

    public List<Long> getIncompletedTasksId() {
	return incompletedTasksId;
    }

    public void setIncompletedTasksId(List<Long> incompletedTasksId) {
	this.incompletedTasksId = incompletedTasksId;
    }

}
