package com.ia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="dtl_pills")
public class PillsCounterVO {

	@Id
//	@Column(name="id")
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private int id;

}
