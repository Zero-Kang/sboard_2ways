package org.zerock.sboard.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"roleSet"})
public class ClubMember extends BaseEntity {

    @Id
    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<ClubMemberRole> roleSet;

    public void addMemberRole(ClubMemberRole role){

        if(roleSet == null){
            roleSet = new HashSet<>();
        }
        roleSet.add(role);

    }
}
