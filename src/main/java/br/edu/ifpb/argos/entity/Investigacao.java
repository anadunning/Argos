package br.edu.ifpb.argos.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_INVESTIGACAO")

public class Investigacao {
	
	@Id
	@Column(name = "ID_INVESTIGACAO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToMany(mappedBy="investigacoes")
	
	private List<Pessoa> pessoas;

}