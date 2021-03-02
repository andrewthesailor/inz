package com.szczepanika.scheduler.model.parameters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_TIMESLOTS")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "S_TIMESLOT_ID", sequenceName = "S_TIMESLOT_ID")
@AttributeOverrides({
        @AttributeOverride(name = "auditCd", column = @Column(name = "TIMESLOT_AUDIT_CD", updatable = false, nullable = false)),
        @AttributeOverride(name = "auditMd", column = @Column(name = "TIMESLOT_AUDIT_MD")),
        @AttributeOverride(name = "auditRd", column = @Column(name = "TIMESLOT_AUDIT_RD"))
        })
@Filter(name = "removalTest", condition = "this.auditRd IS NULL")
public class Timeslot extends AbstractAuditing{

    @Id
    @GeneratedValue(generator = "S_TIMESLOT_ID")
    @Column(name = "TIMESLOT_ID")
    Long id;
    //weekday

    @Column(name = "TIMESLOT_DAY")
    private Integer day;
    //3hour exam slot 8-11 1, 11-14 2, 14-17 3, 17-20 4

    @Column(name = "TIMESLOT_SLOT")
    private Integer slot;

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(getId().equals(((Timeslot)obj).getId()))
            return true;
        if(this.getDay().intValue()==((Timeslot) obj).getDay().intValue()){
            return this.getSlot().intValue()==((Timeslot) obj).getSlot().intValue();
        }
        return false;
    }


}
