package org.zerock.sboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.sboard.entity.SBoard;

import java.util.Optional;

public interface SBoardRepository extends JpaRepository<SBoard, Long> {


    @EntityGraph(attributePaths = {"writer","attaches", "replies"},
            type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT s FROM SBoard s ")
    Page<SBoard> getList(PageRequest pageRequest);

    @EntityGraph(attributePaths = {"writer","attaches", "replies"},
            type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT s FROM SBoard s WHERE s.sno = :sno ")
    SBoard getBySno(Long sno);

    @EntityGraph(attributePaths = {"replies"})
    @Query("select s FROM SBoard s left join s.replies r where r.rno = :rno  ")
    Optional<SBoard> findByRno(Long rno);

}
