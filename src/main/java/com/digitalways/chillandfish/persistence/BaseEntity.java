package com.digitalways.chillandfish.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * @author huba
 */
@MappedSuperclass
public abstract class BaseEntity {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "created_on", nullable = false)
    @CreationTimestamp
    final private LocalDateTime createdDate = LocalDateTime.now();
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Column(name = "modified_on")
    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private Long createUserId;

    @Column(name = "modified_by", nullable = false)
    @LastModifiedBy
    private Long modifyUserId;

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

    public Long getCreateUserId() {
        return createUserId;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

}
