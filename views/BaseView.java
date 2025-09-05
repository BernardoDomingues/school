package views;

import java.util.*;

/**
 * View base que contém funcionalidades comuns a todas as views
 */
public abstract class BaseView {
    protected Scanner scanner;
    
    public BaseView() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Exibe uma linha separadora
     */
    protected void exibirSeparador(int tamanho) {
        System.out.println("=".repeat(tamanho));
    }
    
    /**
     * Exibe um título centralizado
     */
    public void exibirTitulo(String titulo) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📋 " + titulo);
        System.out.println("=".repeat(50));
    }
    
    /**
     * Lê uma opção do menu
     */
    public int lerOpcao() {
        System.out.print("Escolha uma opção: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Lê uma string
     */
    protected String lerString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }
    
    /**
     * Lê um número inteiro
     */
    protected int lerInteiro(String prompt) {
        System.out.print(prompt + ": ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Lê um boolean (s/n)
     */
    protected boolean lerBoolean(String prompt) {
        System.out.print(prompt + " (s/n): ");
        String resposta = scanner.nextLine().toLowerCase();
        return resposta.startsWith("s");
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
     * Pausa a execução
     */
    protected void pausar() {
        System.out.print("Pressione Enter para continuar...");
        scanner.nextLine();
    }
}
