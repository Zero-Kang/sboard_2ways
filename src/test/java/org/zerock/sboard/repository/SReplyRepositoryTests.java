package org.zerock.sboard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.sboard.entity.ClubMember;
import org.zerock.sboard.entity.SBoard;
import org.zerock.sboard.entity.SReply;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class SReplyRepositoryTests {


    @Autowired
    private SBoardRepository boardRepository;

    @Autowired
    private SReplyRepository replyRepository;

    @Test
    @Transactional
    public void insertReplies() {

        for(int i = 0; i < 100; i++){

            Long sno  = (long)( (Math.random()* 100) +1 );

            int emailNo = (int)(Math.random()* 100) + 1;

            SReply reply =SReply.builder()
                    .replyText("reply........."+sno+"........")
                    .board(SBoard.builder().sno(sno).build())
                    .replyer(ClubMember.builder().email("user"+emailNo+"@zerock.org").build())
                    .build();


            SBoard board  = boardRepository.findById(sno).get();
            board.addOrUpdateReply(reply);

            boardRepository.save(board);

        }//end for
    }

    @Test
    public void insertReplyOne() {

        String email ="user10@zerock.org";
        Long sno = 100L;

        ClubMember member = ClubMember.builder().email(email).build();
        SBoard board = boardRepository.getBySno(sno);

        SReply reply = SReply.builder().replyer(member).replyText("Test....").build();

        board.addOrUpdateReply(reply);

        replyRepository.save(reply);

        System.out.println("======================================================");
        System.out.println(boardRepository.getBySno(sno).getReplies());
    }

    @Test
    public void readReplyWithSBoard(){

        Long sno = 100L;
//
//        List<SReply> replies = replyRepository.getListBySno(sno);
//
//        replies.forEach(r -> {
//            System.out.println(r);
//            System.out.println(r.getReplyer());
//        });
    }

    @Test
    public void updateReply() {

        Long rno = 103L;

        SBoard sBoard = boardRepository.findByRno(rno).get();

        //System.out.println(sBoard.getReplies());

        ClubMember replyer = ClubMember.builder().email("user10@zerock.org").build();

        SReply reply = SReply.builder().rno(rno).board(sBoard)
                .replyText("UpdateText...............")
                .replyer(replyer)
                .build();
        sBoard.addOrUpdateReply(reply);
        boardRepository.save(sBoard);

        System.out.println(sBoard.getReplies());
    }


    @Test
    public void deleteReply(){

        Long rno = 101L;

        SBoard sBoard = boardRepository.findByRno(rno).get();

        System.out.println(sBoard.getReplies());

        SReply reply = SReply.builder().rno(rno).build();

        sBoard.deleteReply(reply);

        boardRepository.save(sBoard);

    }
}
