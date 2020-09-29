package org.zerock.sboard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.sboard.entity.ClubMember;
import org.zerock.sboard.entity.SBoard;
import org.zerock.sboard.entity.SBoardAttach;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class SBoardRepositoryTests {

    @Autowired
    private SBoardRepository boardRepository;

    @Test
    public void insertSBoards(){

        ClubMember member = ClubMember.builder().email("user1@zerock.org").build();

        IntStream.rangeClosed(1,100).forEach( i -> {

            SBoard board = SBoard.builder()
                    .title("Title..."+i)
                    .content("Content...." + i)
                    .writer(member)
                    .attaches(new HashSet<>())
                    .replies(new HashSet<>())
                    .build();

            for(int j = 0; j < 3; j++){

                SBoardAttach attach = SBoardAttach.builder().fileName("test.jpg")
                        .uuid("11111")
                        .uploadPath("/test/aa")
                        .build();

                board.addAttach(attach);
            }

            boardRepository.save(board);

        });
    }

    @Test
    public void testSBoardList() {

        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "sno"));


        Page<SBoard> result = boardRepository.getList(pageRequest);

        System.out.println(result);

        System.out.println("-------------------------------------");
        result.forEach(sBoard -> {

            System.out.println(sBoard.getWriter());

            System.out.println(sBoard.getAttaches());

            System.out.println(sBoard.getReplies());

            System.out.println("-----------------------------------------");
        });
    }

    @Test
    public void updateSBoard() {


        SBoard board = boardRepository.findById(100L).get();

        board.changeTitle("100 Title.............");


        boardRepository.save(board);


        System.out.println(board.getReplies());
    }


}
