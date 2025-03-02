package com.rdlab.education.domain.entity.core;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.TimeZone;

@MappedSuperclass
public abstract class BusinessEntity<Id extends Serializable> implements CoreEntity<Id> {

    public final static String DEFAULT_USER = "system";

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date", nullable = false)
    private LocalDateTime lastUpdateDate;

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now(TimeZone.getDefault().toZoneId());
        this.lastUpdateDate = this.creationDate;

        if (StringUtils.isEmpty(this.createdBy)) {
            this.createdBy = findCurrentUser();
        }
        this.lastUpdatedBy = this.createdBy;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdateDate = LocalDateTime.now(TimeZone.getDefault().toZoneId());
        this.lastUpdatedBy = findCurrentUser();
    }

    private String findCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }
            return DEFAULT_USER;
        }
        return DEFAULT_USER;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
