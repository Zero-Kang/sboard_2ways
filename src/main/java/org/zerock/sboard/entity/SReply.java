package org.zerock.sboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"replyer","board"})
public class SReply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    public void changeReplyText(String text) {
        this.replyText = text;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private SBoard board;

    public void setBoard(SBoard board){
        this.board = board;
    }
}
