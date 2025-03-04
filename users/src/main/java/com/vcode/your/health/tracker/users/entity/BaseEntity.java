package com.vcode.your.health.tracker.users.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@ToString
public class BaseEntity {
	@CreatedBy
	@Column(updatable = false)
	private String created_by;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime created_on;
	@LastModifiedBy
	@Column(insertable = false)
	private String updated_by;
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime updated_on;
}
