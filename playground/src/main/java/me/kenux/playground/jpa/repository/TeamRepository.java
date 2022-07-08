package me.kenux.playground.jpa.repository;

import me.kenux.playground.jpa.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select t from Team t join fetch t.members")
    List<Team> findAllTeamWithMembers();

    @Query("select distinct t from Team t join fetch t.members")
    List<Team> findAllTeamWithMembersUsingDistinct();
}
