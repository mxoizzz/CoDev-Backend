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

    // Updated: domain keyword search
    @Query("SELECT p FROM Project p WHERE p.visibility = :visibility " +
           "AND LOWER(p.domain) LIKE LOWER(CONCAT('%', :domainKeyword, '%'))")
    List<Project> filterByDomainKeyword(@Param("visibility") ProjectVisibility visibility,
                                        @Param("domainKeyword") String domainKeyword);

    @Query("SELECT p FROM Project p WHERE p.visibility = :visibility AND " +
           "(LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')))") 
    List<Project> searchPublicProjects(@Param("visibility") ProjectVisibility visibility,
                                       @Param("keyword") String keyword);

    // Corrected filter by tech stack
    @Query("SELECT DISTINCT p FROM Project p JOIN p.techStack t " +
           "WHERE p.visibility = :visibility " +
           "AND (:tech IS NULL OR LOWER(t) LIKE LOWER(CONCAT('%', :tech, '%')))") 
    List<Project> filterByTechStack(@Param("visibility") ProjectVisibility visibility,
                                    @Param("tech") String tech);

    // Corrected combined filter + search
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN p.techStack t " +
           "WHERE p.visibility = :visibility " +
           "AND (:domain IS NULL OR LOWER(p.domain) LIKE LOWER(CONCAT('%', :domain, '%'))) " +
           "AND (:tech IS NULL OR LOWER(t) LIKE LOWER(CONCAT('%', :tech, '%'))) " +
           "AND (:status IS NULL OR p.status = :status) " +
           "AND (:keyword IS NULL OR (LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "                          OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))))")
    List<Project> filterAndSearch(@Param("visibility") ProjectVisibility visibility,
                                  @Param("domain") String domain,
                                  @Param("tech") String tech,
                                  @Param("status") ProjectStatus status,
                                  @Param("keyword") String keyword);
}