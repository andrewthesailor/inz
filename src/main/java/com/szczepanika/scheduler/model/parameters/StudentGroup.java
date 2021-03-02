package com.szczepanika.scheduler.model.parameters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Filter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "T_STUDENT_GROUPS")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "S_STUDENT_GROUP_ID", sequenceName = "S_STUDENT_GROUP_ID", allocationSize = 50)
@AttributeOverrides({
        @AttributeOverride(name = "auditCd", column = @Column(name = "STUDENT_GROUP_AUDIT_CD", updatable = false, nullable = false)),
        @AttributeOverride(name = "auditMd", column = @Column(name = "STUDENT_GROUP_AUDIT_MD")),
        @AttributeOverride(name = "auditRd", column = @Column(name = "STUDENT_GROUP_AUDIT_RD"))
})
@Filter(name = "removalTest", condition = "this.auditRd IS NULL")
public class StudentGroup extends AbstractAuditing{

    @Id
    @Column(name = "STUDENT_GROUP_ID")
    @GeneratedValue(generator = "S_STUDENT_GROUP_ID")
    private Long id;

    @Column(name = "STUDENT_GROUP_COURSE")
    private String course;

    @Column(name = "STUDENT_GROUP_SEMESTER")
    private Integer semester;

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(this.getId().equals(((StudentGroup)obj).getId()))
            return true;
        if(this.getCourse().equals(((StudentGroup) obj).getCourse())){
            return this.getSemester().intValue()==((StudentGroup) obj).getSemester().intValue();
        }
        return false;
    }

}
