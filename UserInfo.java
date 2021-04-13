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
@Table(name="user_info")
public class UserInfo implements Serializable{
	
	@Id
	@Column
	private int user_id;
	@Column
	private String name;
	@Column
	private int age;
	@Column
	private String email;
	@Column
	private String password;
}
