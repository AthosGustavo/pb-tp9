package com.infnet.pb.Controller;

import com.infnet.pb.Model.Aluno;
import com.infnet.pb.Model.Turma;
import com.infnet.pb.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

  @Autowired
  private AlunoRepository alunoRepository;
  @GetMapping("/exibirAlunos")
  public List<Aluno> getAllAlunos() {
    return alunoRepository.findAll();
  }
  @PostMapping
  public Aluno createAluno(@RequestBody Aluno aluno) {
    return alunoRepository.save(new Aluno(aluno.getMatricula(), aluno.getNome(),aluno.getIdade(), aluno.getCpf(), aluno.getEmail()));
  }

  @GetMapping("/exibirTurma/{idAluno}")
  public ResponseEntity<String> exibirTurma(@PathVariable Long idAluno) {
    Aluno aluno = alunoRepository.findById(idAluno)
      .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

    List<Turma> turmas = aluno.getTurmas();
    if (!turmas.isEmpty()) {
      StringBuilder turmasStr = new StringBuilder();
      for (Turma turma : turmas) {
        turmasStr.append(turma.getNome()).append(", ");
      }
      // Remove the last comma and space
      turmasStr.setLength(turmasStr.length() - 2);
      return new ResponseEntity<>("Turmas do aluno: " + turmasStr.toString(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("O aluno não está associado a nenhuma turma", HttpStatus.OK);
    }
  }
  @DeleteMapping("/remover/{idAluno}")
  public ResponseEntity<?> deleteAluno(@PathVariable Long idAluno) {
    Aluno aluno = alunoRepository.findById(idAluno)
      .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

    List<Turma> turmas = aluno.getTurmas();
    for (Turma turma : turmas) {
      turma.removeAluno(aluno);
    }

    alunoRepository.delete(aluno); // Deleta o aluno

    return ResponseEntity.ok().build();
  }

  @PutMapping("/editar/{idAluno}")
  public ResponseEntity<Aluno> updateAluno(@PathVariable Long idAluno, @RequestBody Aluno alunoDetails) {
    Aluno aluno = alunoRepository.findById(idAluno)
      .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    if (alunoDetails.getNome() != null) {
      aluno.setNome(alunoDetails.getNome());
    }
    if (alunoDetails.getIdade() < 17) {
      aluno.setIdade(alunoDetails.getIdade());
    }
    if (alunoDetails.getCpf() != null) {
      aluno.setCpf(alunoDetails.getCpf());
    }
    if (alunoDetails.getEmail() != null) {
      aluno.setEmail(alunoDetails.getEmail());
    }

    Aluno updatedAluno = alunoRepository.save(aluno);
    return ResponseEntity.ok(updatedAluno);
  }
}