package com.baeldung.listvsset.eager.set.lazy.moderatedomain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.Set;

@Entity(name = "interest_group")
@Table(name = "interest_group")
public class Group {

    @Id
    private Long id;

    private String name;

    @ManyToMany
    private Set<User> members;

    public Group() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Set<User> getMembers() {
        return this.members;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(Set<User> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Group group = (Group) o;

        return Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String toString() {
        return "Group(id=" + this.getId() + ", name=" + this.getName() + ", members=" + this.getMembers() + ")";
    }
}
