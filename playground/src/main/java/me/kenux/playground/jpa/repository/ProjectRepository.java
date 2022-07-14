package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("select p from Project p")
    List<Project> findAllProject();

    @Query("update Project p set p.name = :name where p.id = :id")
    void update1(@Param("name") String name, @Param("id") Long id);


    @Query("update Project p set p.name = :name where p.id = :id")
    @Modifying
    void update2(@Param("name") String name, @Param("id") Long id);

    @Query("update Project p set p.name = :name where p.id = :id")
    @Modifying(clearAutomatically = true)
    void update3(@Param("name") String name, @Param("id") Long id);
}
