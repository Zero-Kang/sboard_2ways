package org.zerock.sboard.entity;

//https://techrocking.com/cascade-in-jpa-and-hibernate/

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"writer","attaches", "replies"})
public class SBoard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember writer;

    private boolean deleted;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "board",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<SBoardAttach> attaches;



    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "board",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<SReply> replies;

    public void addOrUpdateReply(SReply reply){
        if(replies == null){
            replies = new HashSet<>();
        }
        reply.setBoard(this);
        replies.removeIf(r -> r.getRno() == reply.getRno());

        replies.add(reply);
    }

    public void deleteReply(SReply reply){

        reply.setBoard(null);
        replies.removeIf(r -> r.getRno() == reply.getRno());

    }

    public void addAttach(SBoardAttach attach){
        attach.setBoard(this);
        attaches.add(attach);
    }

    public void clearAttaches() {

        attaches.forEach(attach -> attach.setBoard(null));

        attaches.clear();
    }

}
