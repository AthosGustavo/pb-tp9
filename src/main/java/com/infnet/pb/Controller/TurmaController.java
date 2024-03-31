package com.infnet.pb.Controller;

import com.infnet.pb.Model.Aluno;
import com.infnet.pb.Model.Turma;
import com.infnet.pb.Repository.AlunoRepository;
import com.infnet.pb.Repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/turma")
public class TurmaController {


  @Autowired
  private TurmaRepository turmaRepository;

  @Autowired
  private AlunoRepository alunoRepository;


  @PostMapping
  public Turma createTurma(@RequestBody Turma turma) {
    return turmaRepository.save(new Turma(turma.getCodTurma(), turma.getNome()));
  }

  @Transactional
  @PostMapping("/{idTurma}/{idAluno}")
  public Turma addAlunoToTurma(@PathVariable Long idTurma, @PathVariable Long idAluno) {
    Turma turma = turmaRepository.findById(idTurma)
      .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    Aluno aluno = alunoRepository.findById(idAluno)
      .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

    turma.getAlunos().add(aluno);
    aluno.getTurmas().add(turma);
    turmaRepository.save(turma);
    alunoRepository.save(aluno);
    return turma;
  }

  @GetMapping("/abrirTurma/{idTurma}")
  public ResponseEntity<String> abriTurma(@PathVariable Long idTurma) {
    Turma turma = turmaRepository.findById(idTurma)
      .orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    int quantidadeAlunos = turma.getAlunos().size();

    if (quantidadeAlunos < 2) {
      return new ResponseEntity<>("A turma deve ter no mínimo 2 alunos para ser aberta. Quantidade atual de alunos: " + quantidadeAlunos, HttpStatus.BAD_REQUEST);
    } else if (quantidadeAlunos > 10) {
      return new ResponseEntity<>("A turma deve possuir no máximo 10 alunos. Quantidade atual de alunos: " + quantidadeAlunos, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>("Turma aberta com sucesso. Quantidade atual de alunos: " + quantidadeAlunos, HttpStatus.OK);
    }
  }

  @GetMapping("/gerarPauta/{idTurma}")
  public ResponseEntity<String> gerarPauta(@PathVariable Long idTurma) {
    Turma turma = turmaRepository.findById(idTurma)
      .orElseThrow(() -> new RuntimeException("Turma não encontrada"));

    String disciplina = turma.getDisciplina() != null ? turma.getDisciplina().getNome() : "sem disciplina";
    String professor = turma.getProfessor() != null ? turma.getProfessor().getNome() : "sem professor";
    String alunos = turma.getAlunos().stream().map(Aluno::getNome).collect(Collectors.joining(", "));

    return new ResponseEntity<>("Código da turma: " + turma.getCodTurma() + ", Disciplina: " + disciplina + ", Professor: " + professor + ", Alunos: " + alunos, HttpStatus.OK);
  }
}