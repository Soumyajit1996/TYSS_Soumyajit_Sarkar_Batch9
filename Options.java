package com.tyss.quizpro.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="option_info")
public class Options implements Serializable{
	@Id
	@Column
	private int option_id;
	@Column
	private String options;
	@Column
	private int question_id;
}
