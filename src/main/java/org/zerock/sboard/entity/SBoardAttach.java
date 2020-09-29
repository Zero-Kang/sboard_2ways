package org.zerock.sboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class SBoardAttach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ano;

    private String uuid;

    private String fileName;

    private String uploadPath;

    @ManyToOne(fetch = FetchType.LAZY)
    private SBoard board;

    public void setBoard(SBoard board){
        this.board = board;
    }

}
