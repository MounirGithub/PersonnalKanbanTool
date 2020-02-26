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
}
