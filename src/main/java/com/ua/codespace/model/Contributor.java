package com.ua.codespace.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Contributor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @NotNull
    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "contributor_organization",
            joinColumns = @JoinColumn(name = "contributor_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id"))
    Set<Organization> clients = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Organization> getClients() {
        return Collections.unmodifiableSet(clients);
    }

    private void setClients(Set<Organization> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contributor that = (Contributor) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (!name.equals(that.name)) return false;
        return clients != null ? clients.equals(that.clients) : that.clients == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (clients != null ? clients.hashCode() : 0);
        return result;
    }
}
