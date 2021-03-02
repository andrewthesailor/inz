package com.szczepanika.scheduler.model.parameters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "T_ROOM")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "S_ROOM_ID", sequenceName = "S_ROOM_ID", allocationSize = 50)
@AttributeOverrides({
        @AttributeOverride(name = "auditCd", column = @Column(name = "ROOM_AUDIT_CD", updatable = false, nullable = false)),
        @AttributeOverride(name = "auditMd", column = @Column(name = "ROOM_AUDIT_MD")),
        @AttributeOverride(name = "auditRd", column = @Column(name = "ROOM_AUDIT_RD"))
})
@Filter(name = "removalTest", condition = "this.auditRd IS NULL")
public class Room extends AbstractAuditing {

    @Id
    @GeneratedValue(generator = "S_ROOM_ID")
    @Column(name = "ROOM_ID")
    private Integer id;

    @Column(name= "ROOM_NAME")
    private String name;

    @Column(name = "ROOM_SIZE")
    private Integer size;

}
