package com.atc.siterra;

import java.util.List;
import javax.persistence.*;

@Entity
public class Database {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String name;
	List<Table> tables;
}
