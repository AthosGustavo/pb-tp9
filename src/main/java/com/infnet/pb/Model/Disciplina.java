package com.infnet.pb.Model;

import jakarta.persistence.*;


@Entity
public class Disciplina {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int codDisciplina;
  private String nome;
  private int cargaHoraria;
  private int creditos;

  @OneToOne(mappedBy = "disciplina")
  private Turma turma;

  @OneToOne(mappedBy = "disciplina")
  private Professor professor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getCodDisciplina() {
    return codDisciplina;
  }

  public void setCodDisciplina(int codDisciplina) {
    this.codDisciplina = codDisciplina;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getCargaHoraria() {
    return cargaHoraria;
  }

  public void setCargaHoraria(int cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
  }

  public int getCreditos() {
    return creditos;
  }

  public void setCreditos(int creditos) {
    this.creditos = creditos;
  }

  public Turma getTurma() {
    return turma;
  }

  public void setTurma(Turma turma) {
    this.turma = turma;
  }

  public Professor getProfessor() {
    return professor;
  }

  public void setProfessor(Professor professor) {
    this.professor = professor;
  }
}
