package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @Column(name = "tournament_id", nullable = false)
    private Integer id;

    @Column(name = "startdate", nullable = false)
    private LocalDate startdate;

    @Column(name = "enddate", nullable = false)
    private LocalDate enddate;

    @OneToMany(mappedBy = "tournament")
    private Set<Group> groups = new LinkedHashSet<>();


    public Tournament() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

}