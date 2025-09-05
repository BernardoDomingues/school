package controllers;

import models.*;
import views.*;
import java.util.*;

/**
 * Controller responsável pelas operações do professor
 */
public class ProfessorController extends BaseController {
    private Professor professor;
    private ProfessorView professorView;
    
    public ProfessorController(Professor professor) {
        super();
        this.professor = professor;
        this.professorView = new ProfessorView();
    }
    
    /**
     * Menu principal do professor
     */
    public void menuProfessor() {
        while (true) {
            professorView.exibirMenuPrincipal(professor);
            int opcao = professorView.lerOpcao();
            
            switch (opcao) {
                case 1: verMinhasDisciplinas(); break;
                case 2: verAlunosMatriculados(); break;
                case 3: verEstatisticasDisciplinas(); break;
                case 0: return;
                default: professorView.exibirOpcaoInvalida();
            }
        }
    }
    
    /**
     * Exibe as disciplinas ministradas pelo professor
     */
    private void verMinhasDisciplinas() {
        professorView.exibirTitulo("MINHAS DISCIPLINAS");
        
        List<Disciplina> minhasDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getProfessor().equals(professor)) {
                minhasDisciplinas.add(disciplina);
            }
        }
        
        if (minhasDisciplinas.isEmpty()) {
            professorView.exibirNenhumaDisciplina();
        } else {
            professorView.exibirDisciplinas(minhasDisciplinas);
        }
    }
    
    /**
     * Exibe alunos matriculados em uma disciplina específica
     */
    private void verAlunosMatriculados() {
        professorView.exibirTitulo("ALUNOS MATRICULADOS");
        
        List<Disciplina> minhasDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getProfessor().equals(professor)) {
                minhasDisciplinas.add(disciplina);
            }
        }
        
        if (minhasDisciplinas.isEmpty()) {
            professorView.exibirNenhumaDisciplina();
            return;
        }
        
        professorView.exibirDisciplinasParaEscolha(minhasDisciplinas);
        int indice = professorView.lerIndice("disciplina");
        
        if (indice >= 0 && indice < minhasDisciplinas.size()) {
            Disciplina disciplina = minhasDisciplinas.get(indice);
            List<Aluno> alunos = professor.getAlunosMatriculados(disciplina);
            
            if (alunos.isEmpty()) {
                professorView.exibirNenhumAlunoMatriculado(disciplina);
            } else {
                professorView.exibirAlunosMatriculados(alunos, disciplina);
            }
        } else {
            exibirErro("Disciplina inválida!");
        }
    }
    
    /**
     * Exibe estatísticas das disciplinas do professor
     */
    private void verEstatisticasDisciplinas() {
        professorView.exibirTitulo("ESTATÍSTICAS DAS DISCIPLINAS");
        
        List<Disciplina> minhasDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getProfessor().equals(professor)) {
                minhasDisciplinas.add(disciplina);
            }
        }
        
        if (minhasDisciplinas.isEmpty()) {
            professorView.exibirNenhumaDisciplina();
        } else {
            professorView.exibirEstatisticas(minhasDisciplinas);
        }
    }
}
