package com.infnet.pb.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Turma {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int codTurma;
  private int qtdAlunos;
  private String nome;

  @ManyToMany
  @JoinTable(
    name = "aluno_turmas",
    joinColumns = @JoinColumn(name = "turma_id"),
    inverseJoinColumns = @JoinColumn(name = "aluno_id"))
  private List<Aluno> alunos = new ArrayList<>();

  @OneToOne
  @JsonBackReference
  private Professor professor;

  @OneToOne
  private Disciplina disciplina;

  public Turma(int codTurma, String nome) {
    this.codTurma = codTurma;
    this.nome = nome;
  }
  public Turma(){}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getCodTurma() {
    return codTurma;
  }

  public void setCodTurma(int codTurma) {
    this.codTurma = codTurma;
  }

  public int getQtdAlunos() {
    return qtdAlunos;
  }

  public void setQtdAlunos(int qtdAlunos) {
    this.qtdAlunos = qtdAlunos;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Aluno> getAlunos() {
    return alunos;
  }

  public void setAlunos(List<Aluno> alunos) {
    this.alunos = alunos;
  }

  public Professor getProfessor() {
    return professor;
  }

  public void setProfessor(Professor professor) {
    this.professor = professor;
  }

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }

  public void associaDisciplinaATurma(Disciplina disciplina) {
    this.disciplina = disciplina;
    disciplina.setTurma(this);
  }

  public void removeAluno(Aluno aluno) {
    this.alunos.remove(aluno);
  }
}
