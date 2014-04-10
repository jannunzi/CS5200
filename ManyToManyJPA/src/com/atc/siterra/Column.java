package com.atc.siterra;

import javax.persistence.*;

@Entity
public class Column {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String name;
	String type;
	Sheet sheet;
}
