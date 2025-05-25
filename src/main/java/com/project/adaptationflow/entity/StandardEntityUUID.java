package com.project.adaptationflow.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
abstract public class StandardEntityUUID extends AuditedEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    protected UUID id;

    @PrePersist
    public void prePersist() {
        generateUuid();
    }

    private void generateUuid() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !this.getClass().isAssignableFrom(o.getClass())) return false;
        StandardEntityUUID that = (StandardEntityUUID) o;
        return Objects.equals(id, that.getId());
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return super.hashCode();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
