package org.example.bookmanager.domain;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class MyEntityListener {
    @PrePersist
    public void prePersist(Object entity) {
        if(entity instanceof Auditable){
            ((Auditable) entity).setCreatedAt(LocalDateTime.now());
            ((Auditable) entity).setUpdatedAt(LocalDateTime.now());

        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if(entity instanceof Auditable){
            ((Auditable) entity).setUpdatedAt(LocalDateTime.now());
        }
    }
}
