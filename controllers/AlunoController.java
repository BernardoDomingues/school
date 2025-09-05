package controllers;

import models.*;
import views.*;
import java.util.*;

/**
 * Controller responsável pelas operações do aluno
 */
public class AlunoController extends BaseController {
    private Aluno aluno;
    private AlunoView alunoView;
    
    public AlunoController(Aluno aluno) {
        super();
        this.aluno = aluno;
        this.alunoView = new AlunoView();
    }
    
    /**
     * Menu principal do aluno
     */
    public void menuAluno() {
        while (true) {
            alunoView.exibirMenuPrincipal(aluno);
            int opcao = alunoView.lerOpcao();
            
            switch (opcao) {
                case 1: verDisciplinasDisponiveis(); break;
                case 2: verMinhasMatriculas(); break;
                case 3: verEstatisticasPessoais(); break;
                case 4: buscarDisciplina(); break;
                case 0: return;
                default: alunoView.exibirOpcaoInvalida();
            }
        }
    }
    
    /**
     * Exibe disciplinas disponíveis para o aluno
     */
    private void verDisciplinasDisponiveis() {
        alunoView.exibirTitulo("DISCIPLINAS DISPONÍVEIS");
        
        List<Disciplina> disciplinasCurso = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getCurso().equals(aluno.getCurso())) {
                disciplinasCurso.add(disciplina);
            }
        }
        
        if (disciplinasCurso.isEmpty()) {
            alunoView.exibirNenhumaDisciplina();
        } else {
            alunoView.exibirDisciplinas(disciplinasCurso);
        }
    }
    
    /**
     * Exibe as matrículas do aluno
     */
    private void verMinhasMatriculas() {
        alunoView.exibirTitulo("MINHAS MATRÍCULAS");
        
        if (aluno.getMatriculas().isEmpty()) {
            alunoView.exibirNenhumaMatricula();
        } else {
            alunoView.exibirMatriculas(aluno.getMatriculas());
        }
    }
    
    /**
     * Exibe estatísticas pessoais do aluno
     */
    private void verEstatisticasPessoais() {
        alunoView.exibirTitulo("ESTATÍSTICAS PESSOAIS");
        alunoView.exibirEstatisticas(aluno);
    }
    
    /**
     * Busca disciplinas por nome ou código
     */
    private void buscarDisciplina() {
        alunoView.exibirTitulo("BUSCAR DISCIPLINA");
        String busca = alunoView.lerTermoBusca();
        
        List<Disciplina> resultados = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getNome().toLowerCase().contains(busca.toLowerCase()) || 
                disciplina.getCodigo().toLowerCase().contains(busca.toLowerCase())) {
                resultados.add(disciplina);
            }
        }
        
        if (resultados.isEmpty()) {
            alunoView.exibirNenhumResultado(busca);
        } else {
            alunoView.exibirResultadosBusca(resultados);
        }
    }
}
