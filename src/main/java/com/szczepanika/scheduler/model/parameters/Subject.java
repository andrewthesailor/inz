package com.szczepanika.scheduler.model.parameters;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "T_SUBJECTS")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "S_SUBJECT_ID", sequenceName = "S_SUBJECT_ID", allocationSize = 50)
@AttributeOverrides({
        @AttributeOverride(name = "auditCd", column = @Column(name = "SUBJECT_AUDIT_CD", updatable = false, nullable = false)),
        @AttributeOverride(name = "auditMd", column = @Column(name = "SUBJECT_AUDIT_MD")),
        @AttributeOverride(name = "auditRd", column = @Column(name = "SUBJECT_AUDIT_RD"))
})
@Filter(name = "removalTest", condition = "this.auditRd IS NULL")
public class Subject extends AbstractAuditing{

    @Id
    @GeneratedValue(generator = "S_SUBJECT_ID")
    @Column(name = "SUBJECT_ID")
    private Integer id;

    @Column(name = "SUBJECT_NAME")
    private String name;

    @Column(name = "SUBJECT_STUDENT_COUNT")
    private Integer studentCount;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_STUD_GROUP_ID")
    private StudentGroup studentGroup;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_TEACHER_ID")
    private Teacher teacher;
}
