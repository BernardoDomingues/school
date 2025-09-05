package views;

import models.*;
import java.util.*;

/**
 * View responsável pela interface do aluno
 */
public class AlunoView extends BaseView {
    
    /**
     * Exibe o menu principal do aluno
     */
    public void exibirMenuPrincipal(Aluno aluno) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("👨‍🎓 MENU ALUNO - " + aluno.getNome());
        System.out.println("📚 Curso: " + aluno.getCurso().getNome());
        System.out.println("🎓 Matrícula: " + aluno.getMatricula());
        System.out.println("=".repeat(50));
        System.out.println("1. 📋 Ver Disciplinas Disponíveis");
        System.out.println("2. 📋 Ver Minhas Matrículas");
        System.out.println("3. 📊 Ver Estatísticas Pessoais");
        System.out.println("4. 🔍 Buscar Disciplina");
        System.out.println("0. 🔙 Voltar ao Menu Principal");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Exibe disciplinas disponíveis
     */
    public void exibirDisciplinas(List<Disciplina> disciplinas) {
        System.out.println("-".repeat(50));
        
        for (Disciplina disciplina : disciplinas) {
            System.out.println("• " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
            System.out.println("  Professor: " + disciplina.getProfessor().getNome());
            System.out.println("  Créditos: " + disciplina.getCreditos());
            System.out.println("  Tipo: " + (disciplina.isObrigatoria() ? "Obrigatória" : "Optativa"));
            System.out.println("  Status: " + (disciplina.isAtiva() ? "Ativa" : "Inativa"));
            System.out.println("  Vagas: " + disciplina.getVagasDisponiveis() + "/" + disciplina.getVagasTotais());
            System.out.println();
        }
    }
    
    /**
     * Exibe matrículas do aluno
     */
    public void exibirMatriculas(List<Matricula> matriculas) {
        System.out.println("-".repeat(50));
        
        for (Matricula matricula : matriculas) {
            System.out.println("• " + matricula.getDisciplina().getNome());
            System.out.println("  Professor: " + matricula.getDisciplina().getProfessor().getNome());
            System.out.println("  Tipo: " + (matricula.isObrigatoria() ? "Obrigatória" : "Optativa"));
            System.out.println("  Semestre: " + matricula.getSemestre());
            System.out.println("  Status: " + (matricula.isAtiva() ? "Ativa" : "Inativa"));
            System.out.println();
        }
    }
    
    /**
     * Exibe estatísticas pessoais
     */
    public void exibirEstatisticas(Aluno aluno) {
        System.out.println("-".repeat(50));
        System.out.println("🎓 Matrícula: " + aluno.getMatricula());
        System.out.println("📚 Curso: " + aluno.getCurso().getNome());
        System.out.println("📝 Total de Matrículas: " + aluno.getMatriculas().size());
        System.out.println("📚 Matrículas Obrigatórias: " + aluno.getQuantidadeMatriculasObrigatorias() + "/4");
        System.out.println("🎯 Matrículas Optativas: " + aluno.getQuantidadeMatriculasOptativas() + "/2");
        System.out.println("💯 Créditos Cursados: " + aluno.getCreditosCursados());
    }
    
    /**
     * Lê termo de busca
     */
    public String lerTermoBusca() {
        return lerString("Digite o nome ou código da disciplina");
    }
    
    /**
     * Exibe resultados da busca
     */
    public void exibirResultadosBusca(List<Disciplina> resultados) {
        System.out.println("\n📚 RESULTADOS DA BUSCA:");
        System.out.println("-".repeat(50));
        
        for (Disciplina disciplina : resultados) {
            System.out.println("• " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
            System.out.println("  Professor: " + disciplina.getProfessor().getNome());
            System.out.println("  Curso: " + disciplina.getCurso().getNome());
            System.out.println("  Créditos: " + disciplina.getCreditos());
            System.out.println("  Tipo: " + (disciplina.isObrigatoria() ? "Obrigatória" : "Optativa"));
            System.out.println("  Status: " + (disciplina.isAtiva() ? "Ativa" : "Inativa"));
            System.out.println();
        }
    }
    
    /**
     * Exibe mensagem quando não há disciplinas
     */
    public void exibirNenhumaDisciplina() {
        System.out.println("Nenhuma disciplina disponível para seu curso.");
    }
    
    /**
     * Exibe mensagem quando não há matrículas
     */
    public void exibirNenhumaMatricula() {
        System.out.println("Você não possui matrículas.");
    }
    
    /**
     * Exibe mensagem quando não há resultados na busca
     */
    public void exibirNenhumResultado(String termo) {
        System.out.println("Nenhuma disciplina encontrada com o termo: " + termo);
    }
    
    /**
     * Exibe opção inválida
     */
    public void exibirOpcaoInvalida() {
        exibirErro("Opção inválida!");
    }
}
