
package com.digitalways.chillandfish.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;


/**
 *
 * @author huba
 */
@MappedSuperclass
public abstract class BaseEntity {
    
    private @Id @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name="created_on", nullable = false)
    final private LocalDateTime createdDate = LocalDateTime.now();
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name="modified_on")
    private LocalDateTime modifyDate = null;
    
    @Column(name="created_by",nullable = false)
    private Long createUserId;
    
    @Column(name="modified_by",nullable=false)
    private Long modifyUserId = null;

    public BaseEntity(Long createUserId) {
        this.createUserId = createUserId;
    }

    public BaseEntity() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = Id;
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
