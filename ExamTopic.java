package com.tyss.quizpro.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name="exam_info")
public class ExamTopic implements Serializable{
	@Id
	@Column
	private int exam_id;
	@Column
	private String exam_name;
	
}
