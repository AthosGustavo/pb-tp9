package com.infnet.pb.Model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
@Entity
public class Professor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String matricula;
  private String nome;
  private String endereco;
  private String telefone;
  private String titulo;
  private String contratacao;
  @OneToOne(mappedBy = "professor")
  @JsonManagedReference
  private Turma turma;
  @OneToOne
  private Disciplina disciplina;

  public Professor(String matricula, String nome, String endereco, String telefone, String titulo, String contratacao) {
    this.matricula = matricula;
    this.nome = nome;
    this.endereco = endereco;
    this.telefone = telefone;
    this.titulo = titulo;
    this.contratacao = contratacao;
  }

  public Professor(){}
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getContratacao() {
    return contratacao;
  }

  public void setContratacao(String contratacao) {
    this.contratacao = contratacao;
  }

  public Turma getTurma() {
    return turma;
  }

  public void setTurma(Turma turma) {
    this.turma = turma;
  }

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }

  public void associaProfessorADisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
    disciplina.setProfessor(this);
  }
}
