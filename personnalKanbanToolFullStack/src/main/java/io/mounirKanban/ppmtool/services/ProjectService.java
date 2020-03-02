package io.mounirKanban.ppmtool.services;

import io.mounirKanban.ppmtool.domain.Project;
import io.mounirKanban.ppmtool.exceptions.ProjectIdException;
import io.mounirKanban.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try{
            project.setProjectIdentifier((project.getProjectIdentifier().toUpperCase()));
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("PROJECT ID '"+ project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId) {

        Project project =  projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null) {
            throw new ProjectIdException("PROJECT "+projectId+" ID does not exists");
        }
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null) {
            throw new ProjectIdException("PROJECT "+projectId+" ID does not exists, can not delete");
        }
        projectRepository.delete(project);
    }
}
