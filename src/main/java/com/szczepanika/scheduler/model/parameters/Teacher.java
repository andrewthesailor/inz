package com.szczepanika.scheduler.model.parameters;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_TEACHERS")
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name = "S_TEACHER_ID", sequenceName = "S_TEACHER_ID", allocationSize = 50)
@AttributeOverrides({
        @AttributeOverride(name = "auditCd", column = @Column(name = "TEACHER_AUDIT_CD", updatable = false, nullable = false)),
        @AttributeOverride(name = "auditMd", column = @Column(name = "TEACHER_AUDIT_MD")),
        @AttributeOverride(name = "auditRd", column = @Column(name = "TEACHER_AUDIT_RD"))
})
@Filter(name = "removalTest", condition = "this.auditRd IS NULL")
public class Teacher extends AbstractAuditing implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_TEACHER_ID")
    @Column(name = "TEACHER_ID")
    private Integer id;

    @Column(name = "TEACHER_NAME")
    private String name;

    @Column(name = "TEACHER_SURNAME")
    private String surname;

    @Column(name = "TEACHER_EMAIL")
    private String email;

    @Column(name = "TEACHER_PASSWORD")
    private String password;

    @Column(name = "TEACHER_IS_ADMIN")
    private boolean isAdmin;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

    @ManyToMany
    @JoinTable(name = "T_TEACHER_TIMESLOTS" ,
    joinColumns = @JoinColumn(name = "TTIME_TEACHER_ID"),
    inverseJoinColumns = @JoinColumn(name = "TTIME_TIMESLOT_ID"))
    private List<Timeslot>availableTimeslots;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(isAdmin()){
            return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
        }
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
