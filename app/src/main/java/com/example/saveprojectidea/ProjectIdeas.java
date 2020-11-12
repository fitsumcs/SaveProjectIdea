package com.example.saveprojectidea;

public class ProjectIdeas {

    private  String projectTitle, projectDescription, projectDate, projectId;

    public ProjectIdeas() {
    }

    public ProjectIdeas(String projectTitle, String projectDescription, String projectDate, String projectId) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.projectDate = projectDate;
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public String getProjectDate() {
        return projectDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setProjectDate(String projectDate) {
        this.projectDate = projectDate;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
