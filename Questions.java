package com.tyss.quizpro.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@Entity
@Table(name="question_info")
public class Questions implements Serializable{
	@Id
	@Column
	private int question_id;
	@Column
	private String question;
	@Column
	private String answer;
	@Column
	private int exam_id;
}
