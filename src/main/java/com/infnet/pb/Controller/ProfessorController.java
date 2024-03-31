package com.infnet.pb.Controller;

import com.infnet.pb.Model.Professor;
import com.infnet.pb.Model.Turma;
import com.infnet.pb.Repository.ProfessorRepository;
import com.infnet.pb.Repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

  @Autowired
  private ProfessorRepository professorRepository;

  @Autowired
  private TurmaRepository turmaRepository;

  @PostMapping("/turma/{idTurma}")
  public Professor createProfessor(@RequestBody Professor professor, @RequestParam Long turmaId) {
    Turma turma = turmaRepository.findById(turmaId).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    Professor professorAdicionado = new Professor(professor.getMatricula(), professor.getNome(), professor.getEndereco(), professor.getTelefone(), professor.getTitulo(), professor.getContratacao());
    professorAdicionado.setTurma(turma);
    Professor professorSalvo = professorRepository.save(professorAdicionado);
    turma.setProfessor(professorSalvo);
    turmaRepository.save(turma);
    return professorSalvo;

    //
  }
}