package com.szczepanika.scheduler.model.parameters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
abstract class AbstractAuditing implements Serializable {
    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "AUDIT_CD", updatable = false)
    protected Date auditCd;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "AUDIT_MD")
    protected Date auditMd;

    @Column(name = "AUDIT_RD")
    protected Date auditRd;

}
