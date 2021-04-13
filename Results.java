package com.tyss.quizpro.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="result_info")
public class Results implements Serializable{
	@Id
	@Column
	private int result_id;
	@Column
	private int user_id;
	@Column
	private int score;
	@Column
	private String exam_name;
	@Column
	private Date date; 
}
