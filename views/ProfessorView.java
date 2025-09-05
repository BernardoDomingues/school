package views;

import models.*;
import java.util.*;

/**
 * View responsável pela interface do professor
 */
public class ProfessorView extends BaseView {
    
    /**
     * Exibe o menu principal do professor
     */
    public void exibirMenuPrincipal(Professor professor) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("👨‍🏫 MENU PROFESSOR - " + professor.getNome());
        System.out.println("🎯 Área: " + professor.getAreaEspecializacao());
        System.out.println("=".repeat(50));
        System.out.println("1. 📋 Ver Minhas Disciplinas");
        System.out.println("2. 👥 Ver Alunos Matriculados");
        System.out.println("3. 📊 Ver Estatísticas das Disciplinas");
        System.out.println("0. 🔙 Voltar ao Menu Principal");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Exibe disciplinas do professor
     */
    public void exibirDisciplinas(List<Disciplina> disciplinas) {
        System.out.println("-".repeat(50));
        
        for (Disciplina disciplina : disciplinas) {
            System.out.println("• " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
            System.out.println("  Curso: " + disciplina.getCurso().getNome());
            System.out.println("  Créditos: " + disciplina.getCreditos());
            System.out.println("  Tipo: " + (disciplina.isObrigatoria() ? "Obrigatória" : "Optativa"));
            System.out.println("  Status: " + (disciplina.isAtiva() ? "Ativa" : "Inativa"));
            System.out.println("  Alunos Matriculados: " + disciplina.getQuantidadeAlunosMatriculados());
            System.out.println();
        }
    }
    
    /**
     * Exibe disciplinas para escolha
     */
    public void exibirDisciplinasParaEscolha(List<Disciplina> disciplinas) {
        System.out.println("Suas disciplinas:");
        for (int i = 0; i < disciplinas.size(); i++) {
            System.out.println((i+1) + ". " + disciplinas.get(i).getNome());
        }
    }
    
    /**
     * Lê índice de uma lista
     */
    public int lerIndice(String tipo) {
        return lerInteiro("Escolha a " + tipo + " (número)") - 1;
    }
    
    /**
     * Exibe alunos matriculados
     */
    public void exibirAlunosMatriculados(List<Aluno> alunos, Disciplina disciplina) {
        System.out.println("\n👥 Alunos matriculados em " + disciplina.getNome() + ":");
        for (Aluno aluno : alunos) {
            System.out.println("• " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
            System.out.println("  Email: " + aluno.getEmail());
            System.out.println("  Curso: " + aluno.getCurso().getNome());
            System.out.println();
        }
    }
    
    /**
     * Exibe estatísticas das disciplinas
     */
    public void exibirEstatisticas(List<Disciplina> disciplinas) {
        System.out.println("-".repeat(50));
        
        int totalAlunos = 0;
        int disciplinasAtivas = 0;
        
        for (Disciplina disciplina : disciplinas) {
            totalAlunos += disciplina.getQuantidadeAlunosMatriculados();
            if (disciplina.isAtiva()) {
                disciplinasAtivas++;
            }
        }
        
        System.out.println("📚 Total de Disciplinas: " + disciplinas.size());
        System.out.println("🟢 Disciplinas Ativas: " + disciplinasAtivas);
        System.out.println("👥 Total de Alunos: " + totalAlunos);
        System.out.println("📊 Média de Alunos por Disciplina: " + 
                         (disciplinas.size() > 0 ? totalAlunos / disciplinas.size() : 0));
    }
    
    /**
     * Exibe mensagem quando não há disciplinas
     */
    public void exibirNenhumaDisciplina() {
        System.out.println("Você não possui disciplinas atribuídas.");
    }
    
    /**
     * Exibe mensagem quando não há alunos matriculados
     */
    public void exibirNenhumAlunoMatriculado(Disciplina disciplina) {
        System.out.println("Nenhum aluno matriculado em " + disciplina.getNome() + ".");
    }
    
    /**
     * Exibe opção inválida
     */
    public void exibirOpcaoInvalida() {
        exibirErro("Opção inválida!");
    }
}
