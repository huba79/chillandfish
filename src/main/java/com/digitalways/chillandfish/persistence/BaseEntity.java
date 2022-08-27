/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digitalways.chillandfish.persistence;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.Id;

/**
 *
 * @author huba
 */
@MappedSuperclass
public abstract class BaseEntity {
    
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long Id;
    
    @Column(name="created_on", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
    
    @Column(name="modified_on", nullable = true)
    private LocalDateTime modifyDate = null;
    
    @Column(name="created_by", columnDefinition="BIGINT")
    private Long createUserId;
    
    @Column(name="modifieded_by", columnDefinition="BIGINT")    
    private Long modifyUserId = 0L;

    public BaseEntity(Long createUserId) {
        this.createUserId = createUserId;
    }

    public BaseEntity() {
    }
    
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }


    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
    
    
}
