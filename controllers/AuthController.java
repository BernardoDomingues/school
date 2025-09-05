package controllers;

import models.*;
import views.*;

/**
 * Controller responsável pela autenticação e gerenciamento de sessões
 */
public class AuthController extends BaseController {
    private AuthView authView;
    private Usuario usuarioLogado;
    
    public AuthController() {
        super();
        this.authView = new AuthView();
    }
    
    /**
     * Inicia o processo de autenticação
     */
    public void iniciarSistema() {
        authView.exibirBemVindo();
        
        if (sistema.getSecretarias().isEmpty()) {
            criarSecretariaPadrao();
        }
        
        menuPrincipal();
    }
    
    /**
     * Cria uma secretaria padrão se não existir nenhuma
     */
    private void criarSecretariaPadrao() {
        Secretaria secretaria = new Secretaria("SEC001", "Maria da Secretaria", 
                                              "secretaria@universidade.edu", "admin123", sistema);
        sistema.getSecretarias().add(secretaria);
        sistema.salvarDados();
        authView.exibirSecretariaPadraoCriada(secretaria);
    }
    
    /**
     * Menu principal do sistema
     */
    public void menuPrincipal() {
        while (true) {
            authView.exibirMenuPrincipal();
            int opcao = authView.lerOpcao();
            
            switch (opcao) {
                case 1:
                    loginSecretaria();
                    break;
                case 2:
                    loginAluno();
                    break;
                case 3:
                    loginProfessor();
                    break;
                case 4:
                    executarDemonstracao();
                    break;
                case 5:
                    mostrarEstatisticas();
                    break;
                case 0:
                    authView.exibirDespedida();
                    return;
                default:
                    authView.exibirOpcaoInvalida();
            }
        }
    }
    
    /**
     * Processa login da secretaria
     */
    private void loginSecretaria() {
        authView.exibirTituloLogin("SECRETARIA");
        String email = authView.lerEmail();
        String senha = authView.lerSenha();
        
        Usuario usuario = realizarLogin(email, senha, TipoUsuario.SECRETARIA);
        if (usuario != null) {
            usuarioLogado = usuario;
            SecretariaController secretariaController = new SecretariaController((Secretaria) usuario);
            secretariaController.menuSecretaria();
        } else {
            authView.exibirErroLogin();
        }
    }
    
    /**
     * Processa login do aluno
     */
    private void loginAluno() {
        authView.exibirTituloLogin("ALUNO");
        String email = authView.lerEmail();
        String senha = authView.lerSenha();
        
        Usuario usuario = realizarLogin(email, senha, TipoUsuario.ALUNO);
        if (usuario != null) {
            usuarioLogado = usuario;
            AlunoController alunoController = new AlunoController((Aluno) usuario);
            alunoController.menuAluno();
        } else {
            authView.exibirErroLogin();
        }
    }
    
    /**
     * Processa login do professor
     */
    private void loginProfessor() {
        authView.exibirTituloLogin("PROFESSOR");
        String email = authView.lerEmail();
        String senha = authView.lerSenha();
        
        Usuario usuario = realizarLogin(email, senha, TipoUsuario.PROFESSOR);
        if (usuario != null) {
            usuarioLogado = usuario;
            ProfessorController professorController = new ProfessorController((Professor) usuario);
            professorController.menuProfessor();
        } else {
            authView.exibirErroLogin();
        }
    }
    
    /**
     * Executa demonstração completa do sistema
     */
    private void executarDemonstracao() {
        authView.exibirIniciandoDemonstracao();
        pausar();
        
        // Executa a demonstração diretamente
        executarDemonstracaoCompleta();
        
        authView.exibirVoltarMenu();
        pausar();
    }
    
    /**
     * Executa a demonstração completa do sistema
     */
    private void executarDemonstracaoCompleta() {
        System.out.println("🎓 === DEMONSTRAÇÃO DO SISTEMA DE MATRÍCULAS === 🎓\n");
        
        pausarComMensagem("Vamos demonstrar um sistema completo de matrículas universitárias!");
        
        System.out.println("\n📋 === SEÇÃO 1: CONFIGURAÇÃO INICIAL DO SISTEMA ===");
        pausarComMensagem("Primeiro, vamos configurar o sistema criando uma secretaria");
        
        Secretaria secretaria;
        if (sistema.getSecretarias().isEmpty()) {
            secretaria = new Secretaria("SEC001", "Maria da Secretaria", 
                                     "secretaria@universidade.edu", "senha123", sistema);
            sistema.getSecretarias().add(secretaria);
            sistema.salvarDados();
            System.out.println("✅ Secretaria criada: " + secretaria.getNome());
        } else {
            secretaria = sistema.getSecretarias().get(0);
            System.out.println("✅ Secretaria carregada: " + secretaria.getNome());
        }
        System.out.println("📝 Tipo de usuário: " + secretaria.getTipo());
        
        boolean autenticado = secretaria.autenticar("senha123");
        System.out.println("🔐 Autenticação da secretaria: " + (autenticado ? "SUCESSO" : "FALHA"));
        
        pausarComMensagem("O sistema valida usuários através de email e senha");
        
        System.out.println("\n🎉 === DEMONSTRAÇÃO CONCLUÍDA COM SUCESSO! ===");
        pausarComMensagem("Sistema completo demonstrado com arquitetura MVC!");
    }
    
    private void pausarComMensagem(String mensagem) {
        System.out.println("\n💬 " + mensagem);
        System.out.print("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    /**
     * Mostra estatísticas do sistema
     */
    private void mostrarEstatisticas() {
        authView.exibirEstatisticas(sistema);
    }
    
    /**
     * Retorna o usuário atualmente logado
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    /**
     * Desloga o usuário atual
     */
    public void logout() {
        usuarioLogado = null;
    }
}
