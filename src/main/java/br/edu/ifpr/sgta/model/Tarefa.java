package br.edu.ifpr.sgta.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity(name = "TB_TAREFA")
public class Tarefa implements Serializable{

private static final long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "TITULO_TAREFA", length = 65, nullable = false)
	private String titulo;
	
	@Column(name = "DESCRICAO", length = 255, nullable = false)
	private String descricao;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "disciplina_id", foreignKey = @ForeignKey(name = "FK_TAREFA_DISC"), nullable =  false)
	private Disciplina disciplina;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "TB_TAREFAS_USUARIOS",
		joinColumns = @JoinColumn(name = "ID_TAREFA", foreignKey = @ForeignKey(name = "FK_TAREFA")),
		inverseJoinColumns = @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_USUARIO_TAREFA")))
	private List<Usuario> usuario;			
	
	@Column(name = "PRIORIDADE", length = 5, nullable = true)
	private String prioridade;
	
	@Column(name = "DT_CRIACAO_TAREFA", length = 10, nullable = false)
	private String dtCriacaoTarefa;
	
	@Column(name = "DT_FINALIZACAO_TAREFA", length = 10)
	private String dtFinalizacaoTarefa;
			
	@Column(name = "STATUS_TAREFA", length = 11, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTarefa statusTarefa;
	
	public static String convertData() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}	

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getDtCriacaoTarefa() {
		return dtCriacaoTarefa;
	}

	public void setDtCriacaoTarefa(String dtCriacaoTarefa) {
		this.dtCriacaoTarefa = dtCriacaoTarefa;
	}

	public String getDtFinalizacaoTarefa() {
		return dtFinalizacaoTarefa;
	}

	public void setDtFinalizacaoTarefa(String dtFinalizacaoTarefa) {
		this.dtFinalizacaoTarefa = dtFinalizacaoTarefa;
	}
	
	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
}
