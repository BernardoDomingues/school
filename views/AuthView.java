package views;

import models.*;

/**
 * View responsável pela interface de autenticação
 */
public class AuthView extends BaseView {
    
    /**
     * Exibe mensagem de boas-vindas
     */
    public void exibirBemVindo() {
        System.out.println("🎓 === SISTEMA DE MATRÍCULAS DA UNIVERSIDADE === 🎓\n");
    }
    
    /**
     * Exibe informações da secretaria padrão criada
     */
    public void exibirSecretariaPadraoCriada(Secretaria secretaria) {
        exibirSucesso("Secretaria padrão criada: " + secretaria.getNome());
        exibirInfo("Email: " + secretaria.getEmail());
        exibirInfo("Senha: admin123\n");
    }
    
    /**
     * Exibe o menu principal
     */
    public void exibirMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🏠 MENU PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. 👨‍💼 Login Secretaria");
        System.out.println("2. 👨‍🎓 Login Aluno");
        System.out.println("3. 👨‍🏫 Login Professor");
        System.out.println("4. 🎬 Executar Demonstração Completa");
        System.out.println("5. 📊 Ver Estatísticas do Sistema");
        System.out.println("0. ❌ Sair");
        System.out.println("=".repeat(50));
    }
    
    /**
     * Exibe título do login
     */
    public void exibirTituloLogin(String tipo) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("👨‍💼 LOGIN " + tipo);
        System.out.println("=".repeat(40));
    }
    
    /**
     * Lê o email
     */
    public String lerEmail() {
        return lerString("Email");
    }
    
    /**
     * Lê a senha
     */
    public String lerSenha() {
        return lerString("Senha");
    }
    
    /**
     * Exibe erro de login
     */
    public void exibirErroLogin() {
        exibirErro("Email ou senha incorretos!");
    }
    
    /**
     * Exibe mensagem de início da demonstração
     */
    public void exibirIniciandoDemonstracao() {
        System.out.println("\n🎬 Executando demonstração completa do sistema...");
    }
    
    /**
     * Exibe mensagem para voltar ao menu
     */
    public void exibirVoltarMenu() {
        System.out.println("\nPressione Enter para voltar ao menu...");
    }
    
    /**
     * Exibe estatísticas do sistema
     */
    public void exibirEstatisticas(SistemaMatriculas sistema) {
        System.out.println("\n📊 ESTATÍSTICAS DO SISTEMA");
        exibirSeparador(50);
        System.out.println("👨‍🎓 Total de Alunos: " + sistema.getAlunos().size());
        System.out.println("👨‍🏫 Total de Professores: " + sistema.getProfessores().size());
        System.out.println("👨‍💼 Total de Secretarias: " + sistema.getSecretarias().size());
        System.out.println("📚 Total de Cursos: " + sistema.getCursos().size());
        System.out.println("📖 Total de Disciplinas: " + sistema.getDisciplinas().size());
        System.out.println("📝 Total de Matrículas: " + sistema.getMatriculas().size());
        System.out.println("📅 Total de Períodos: " + sistema.getPeriodosMatricula().size());
        
        int disciplinasAtivas = 0;
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.isAtiva()) {
                disciplinasAtivas++;
            }
        }
        System.out.println("🟢 Disciplinas Ativas: " + disciplinasAtivas);
        
        int periodosAtivos = 0;
        for (PeriodoMatricula periodo : sistema.getPeriodosMatricula()) {
            if (periodo.isAtivo()) {
                periodosAtivos++;
            }
        }
        System.out.println("📅 Períodos Ativos: " + periodosAtivos);
        
        exibirSeparador(50);
    }
    
    /**
     * Exibe mensagem de despedida
     */
    public void exibirDespedida() {
        System.out.println("\n👋 Obrigado por usar o sistema! Até logo!");
    }
    
    /**
     * Exibe opção inválida
     */
    public void exibirOpcaoInvalida() {
        exibirErro("Opção inválida! Tente novamente.");
    }
}
