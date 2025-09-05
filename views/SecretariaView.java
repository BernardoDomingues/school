package views;

import models.*;
import java.util.*;

/**
 * View responsável pela interface da secretaria
 */
public class SecretariaView extends BaseView {
    
    /**
     * Exibe o menu principal da secretaria
     */
    public void exibirMenuPrincipal(Secretaria secretaria) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("👨‍💼 MENU SECRETARIA - " + secretaria.getNome());
        System.out.println("=".repeat(50));
        System.out.println("📚 GESTÃO DE CURSOS E DISCIPLINAS:");
        System.out.println("1. ➕ Cadastrar Curso");
        System.out.println("2. ➕ Cadastrar Disciplina");
        System.out.println("3. ➕ Cadastrar Professor");
        System.out.println("4. ➕ Cadastrar Aluno");
        System.out.println("5. 📋 Listar Cursos");
        System.out.println("6. 📋 Listar Disciplinas");
        System.out.println("7. 📋 Listar Professores");
        System.out.println("8. 📋 Listar Alunos");
        System.out.println("");
        System.out.println("📝 GESTÃO DE MATRÍCULAS:");
        System.out.println("9. 📅 Criar Período de Matrícula");
        System.out.println("10. 🎯 Matricular Aluno");
        System.out.println("11. ❌ Cancelar Matrícula");
        System.out.println("12. 🔄 Ativar/Desativar Disciplinas");
        System.out.println("");
        System.out.println("📊 RELATÓRIOS:");
        System.out.println("13. 📈 Relatório Geral");
        System.out.println("14. 📈 Relatório de Matrículas");
        System.out.println("15. 📈 Relatório de Disciplinas");
        System.out.println("");
        System.out.println("💾 SISTEMA:");
        System.out.println("16. 💾 Salvar Dados");
        System.out.println("17. 📂 Carregar Dados");
        System.out.println("0. 🔙 Voltar ao Menu Principal");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Lê ID
     */
    public String lerId(String tipo) {
        return lerString("ID " + tipo);
    }
    
    /**
     * Lê nome
     */
    public String lerNome(String tipo) {
        return lerString("Nome " + tipo);
    }
    
    /**
     * Lê créditos
     */
    public int lerCreditos() {
        return lerInteiro("Créditos");
    }
    
    /**
     * Lê código da disciplina
     */
    public String lerCodigo() {
        return lerString("Código da Disciplina");
    }
    
    /**
     * Lê se é obrigatória
     */
    public boolean lerObrigatoria() {
        return lerBoolean("É obrigatória?");
    }
    
    /**
     * Lê semestre
     */
    public String lerSemestre() {
        return lerString("Semestre (ex: 2024.1)");
    }
    
    /**
     * Lê email
     */
    public String lerEmail() {
        return lerString("Email");
    }
    
    /**
     * Lê senha
     */
    public String lerSenha() {
        return lerString("Senha");
    }
    
    /**
     * Lê SIAPE
     */
    public String lerSiape() {
        return lerString("SIAPE");
    }
    
    /**
     * Lê área de especialização
     */
    public String lerAreaEspecializacao() {
        return lerString("Área de Especialização");
    }
    
    /**
     * Lê matrícula
     */
    public String lerMatricula() {
        return lerString("Número de Matrícula");
    }
    
    /**
     * Lê duração
     */
    public int lerDuracao() {
        return lerInteiro("Duração em dias");
    }
    
    /**
     * Lê índice de uma lista
     */
    public int lerIndice(String tipo) {
        return lerInteiro("Escolha o " + tipo + " (número)") - 1;
    }
    
    /**
     * Exibe professores disponíveis
     */
    public void exibirProfessoresDisponiveis(List<Professor> professores) {
        System.out.println("Professores disponíveis:");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println((i+1) + ". " + professores.get(i).getNome());
        }
    }
    
    /**
     * Exibe cursos disponíveis
     */
    public void exibirCursosDisponiveis(List<Curso> cursos) {
        System.out.println("Cursos disponíveis:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i+1) + ". " + cursos.get(i).getNome());
        }
    }
    
    /**
     * Exibe alunos disponíveis
     */
    public void exibirAlunosDisponiveis(List<Aluno> alunos) {
        System.out.println("Alunos disponíveis:");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println((i+1) + ". " + alunos.get(i).getNome());
        }
    }
    
    /**
     * Exibe disciplinas disponíveis
     */
    public void exibirDisciplinasDisponiveis(List<Disciplina> disciplinas) {
        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina disc = disciplinas.get(i);
            System.out.println((i+1) + ". " + disc.getNome() + " (" + (disc.isObrigatoria() ? "Obrigatória" : "Optativa") + ")");
        }
    }
    
    /**
     * Exibe matrículas do aluno
     */
    public void exibirMatriculasAluno(Aluno aluno) {
        System.out.println("Matrículas do aluno " + aluno.getNome() + ":");
        for (int i = 0; i < aluno.getMatriculas().size(); i++) {
            Matricula mat = aluno.getMatriculas().get(i);
            System.out.println((i+1) + ". " + mat.getDisciplina().getNome());
        }
    }
    
    /**
     * Exibe opções de gerenciamento
     */
    public void exibirOpcoesGerenciamento() {
        System.out.println("1. Ativar Disciplina");
        System.out.println("2. Desativar Disciplina");
        System.out.println("3. Verificar Disciplinas Ativas");
    }
    
    /**
     * Exibe lista de cursos
     */
    public void exibirCursos(List<Curso> cursos) {
        System.out.println("\n📚 CURSOS CADASTRADOS:");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            for (Curso curso : cursos) {
                System.out.println("• " + curso.getNome() + " (" + curso.getCreditosTotais() + " créditos)");
                System.out.println("  Coordenador: " + curso.getCoordenador().getNome());
            }
        }
    }
    
    /**
     * Exibe lista de disciplinas
     */
    public void exibirDisciplinas(List<Disciplina> disciplinas) {
        System.out.println("\n📚 DISCIPLINAS CADASTRADAS:");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            for (Disciplina disciplina : disciplinas) {
                System.out.println("• " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
                System.out.println("  Professor: " + disciplina.getProfessor().getNome());
                System.out.println("  Curso: " + disciplina.getCurso().getNome());
                System.out.println("  Tipo: " + (disciplina.isObrigatoria() ? "Obrigatória" : "Optativa"));
                System.out.println("  Status: " + (disciplina.isAtiva() ? "Ativa" : "Inativa"));
                System.out.println("  Alunos: " + disciplina.getQuantidadeAlunosMatriculados());
                System.out.println();
            }
        }
    }
    
    /**
     * Exibe lista de professores
     */
    public void exibirProfessores(List<Professor> professores) {
        System.out.println("\n👨‍🏫 PROFESSORES CADASTRADOS:");
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            for (Professor professor : professores) {
                System.out.println("• " + professor.getNome() + " (" + professor.getEmail() + ")");
                System.out.println("  Área: " + professor.getAreaEspecializacao());
                System.out.println("  SIAPE: " + professor.getSiape());
            }
        }
    }
    
    /**
     * Exibe lista de alunos
     */
    public void exibirAlunos(List<Aluno> alunos) {
        System.out.println("\n👨‍🎓 ALUNOS CADASTRADOS:");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno aluno : alunos) {
                System.out.println("• " + aluno.getNome() + " (" + aluno.getEmail() + ")");
                System.out.println("  Matrícula: " + aluno.getMatricula());
                System.out.println("  Curso: " + aluno.getCurso().getNome());
                System.out.println("  Matrículas: " + aluno.getMatriculas().size());
            }
        }
    }
    
    /**
     * Exibe opção inválida
     */
    public void exibirOpcaoInvalida() {
        exibirErro("Opção inválida!");
    }
}
