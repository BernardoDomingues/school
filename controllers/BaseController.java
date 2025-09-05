package controllers;

import models.*;
import java.util.*;

/**
 * Controller base que contém funcionalidades comuns a todos os controllers
 */
public abstract class BaseController {
    protected SistemaMatriculas sistema;
    protected Scanner scanner;
    
    public BaseController() {
        this.sistema = SistemaMatriculas.getInstance();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Realiza login de um usuário no sistema
     */
    protected Usuario realizarLogin(String email, String senha, TipoUsuario tipo) {
        System.out.println("Realizando login para usuário: " + email + " do tipo: " + tipo);
        
        if (tipo == TipoUsuario.ALUNO) {
            for (Aluno aluno : sistema.getAlunos()) {
                if (aluno.getEmail().equals(email) && aluno.autenticar(senha)) {
                    System.out.println("Login realizado com sucesso para aluno: " + aluno.getNome());
                    return aluno;
                }
            }
        } else if (tipo == TipoUsuario.PROFESSOR) {
            for (Professor professor : sistema.getProfessores()) {
                if (professor.getEmail().equals(email) && professor.autenticar(senha)) {
                    System.out.println("Login realizado com sucesso para professor: " + professor.getNome());
                    return professor;
                }
            }
        } else if (tipo == TipoUsuario.SECRETARIA) {
            for (Secretaria secretaria : sistema.getSecretarias()) {
                if (secretaria.getEmail().equals(email) && secretaria.autenticar(senha)) {
                    System.out.println("Login realizado com sucesso para secretaria: " + secretaria.getNome());
                    return secretaria;
                }
            }
        }
        
        System.out.println("Falha no login - usuário ou senha inválidos");
        return null;
    }
    
    /**
     * Exibe mensagem de erro
     */
    protected void exibirErro(String mensagem) {
        System.out.println("❌ " + mensagem);
    }
    
    /**
     * Exibe mensagem de sucesso
     */
    protected void exibirSucesso(String mensagem) {
        System.out.println("✅ " + mensagem);
    }
    
    /**
     * Exibe mensagem informativa
     */
    protected void exibirInfo(String mensagem) {
        System.out.println("ℹ️ " + mensagem);
    }
    
    /**
     * Pausa a execução aguardando Enter
     */
    protected void pausar() {
        System.out.print("Pressione Enter para continuar...");
        scanner.nextLine();
    }
    
    /**
     * Limpa a tela (simulado)
     */
    protected void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
