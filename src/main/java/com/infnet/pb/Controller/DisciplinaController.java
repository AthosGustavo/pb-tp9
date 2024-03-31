package com.infnet.pb.Controller;

import com.infnet.pb.Model.Disciplina;
import com.infnet.pb.Model.Professor;
import com.infnet.pb.Model.Turma;
import com.infnet.pb.Repository.DisciplinaRepository;
import com.infnet.pb.Repository.ProfessorRepository;
import com.infnet.pb.Repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

  @Autowired
  private DisciplinaRepository disciplinaRepository;
  @Autowired
  private TurmaRepository turmaRepository;
  @Autowired
  private ProfessorRepository professorRepository;
  @PostMapping("/{idTurma}/{idProfessor}")
  public Disciplina createDisciplina(@RequestBody Disciplina disciplina, @PathVariable Long idTurma, @PathVariable Long idProfessor) {
    Turma turma = turmaRepository.findById(idTurma)
      .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    Professor professor = professorRepository.findById(idProfessor)
      .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

    disciplina.setTurma(turma);
    disciplina.setProfessor(professor);

    turma.associaDisciplinaATurma(disciplina);
    professor.associaProfessorADisciplina(disciplina);

    return disciplinaRepository.save(disciplina);
  }
}