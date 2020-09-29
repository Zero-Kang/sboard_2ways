package org.zerock.sboard.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.sboard.entity.SBoard;
import org.zerock.sboard.entity.SReply;

import java.util.List;

public interface SReplyRepository extends JpaRepository<SReply, Long> {


    @EntityGraph(attributePaths = {"replyer"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM SReply r WHERE r.board.sno = :sno order by r.rno asc ")
    List<SReply> getListBySno(Long sno);
}
