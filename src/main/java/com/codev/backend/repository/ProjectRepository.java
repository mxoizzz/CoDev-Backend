package com.codev.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codev.backend.entity.Project;
import com.codev.backend.enums.ProjectStatus;
import com.codev.backend.enums.ProjectVisibility;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByOwnerId(Long ownerId);
    List<Project> findAllByOwnerId(Long ownerId);

    List<Project> findByStatus(ProjectStatus status);

    List<Project> findByVisibility(ProjectVisibility visibility);

    List<Project> findByVisibilityAndDomainIgnoreCase(ProjectVisibility visibility, String domain);

    @Query("SELECT p FROM Project p WHERE p.visibility = :visibility AND " +
           "(LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Project> searchPublicProjects(@Param("visibility") ProjectVisibility visibility,
                                       @Param("keyword") String keyword);

    @Query("SELECT p FROM Project p WHERE p.visibility = :visibility AND " +
           "LOWER(:tech) IS NULL OR LOWER(p.techStack) LIKE LOWER(CONCAT('%', :tech, '%'))")
    List<Project> filterByTechStack(@Param("visibility") ProjectVisibility visibility,
                                    @Param("tech") String tech);

    @Query("SELECT p FROM Project p WHERE p.visibility = :visibility " +
           "AND (:domain IS NULL OR LOWER(p.domain) = LOWER(:domain)) " +
           "AND (:tech IS NULL OR LOWER(p.techStack) LIKE LOWER(CONCAT('%', :tech, '%'))) " +
           "AND (:status IS NULL OR p.status = :status) " +
           "AND (:keyword IS NULL OR (LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "                          OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))))")
    List<Project> filterAndSearch(@Param("visibility") ProjectVisibility visibility,
                                  @Param("domain") String domain,
                                  @Param("tech") String tech,
                                  @Param("status") ProjectStatus status,
                                  @Param("keyword") String keyword);
}
