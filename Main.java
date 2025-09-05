import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static SistemaMatriculas sistema = SistemaMatriculas.getInstance();
    private static Secretaria secretaria = null;

    public static void main(String[] args) {
        System.out.println("🎓 === SISTEMA DE MATRÍCULAS DA UNIVERSIDADE === 🎓\n");
        
        if (sistema.getSecretarias().isEmpty()) {
            criarSecretariaPadrao();
        }
        
        menuPrincipal();
    }
    
    private static void criarSecretariaPadrao() {
        secretaria = new Secretaria("SEC001", "Maria da Secretaria", 
                                  "secretaria@universidade.edu", "admin123", sistema);
        sistema.getSecretarias().add(secretaria);
        sistema.salvarDados();
        System.out.println("✅ Secretaria padrão criada: " + secretaria.getNome());
        System.out.println("📧 Email: secretaria@universidade.edu");
        System.out.println("🔑 Senha: admin123\n");
    }
    
    private static void menuPrincipal() {
        while (true) {
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
            System.out.print("Escolha uma opção: ");
            
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                
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
                        System.out.println("\n👋 Obrigado por usar o sistema! Até logo!");
                        return;
                    default:
                        System.out.println("❌ Opção inválida! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, digite um número válido.");
            }
        }
    }
    
    private static void loginSecretaria() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("👨‍💼 LOGIN SECRETARIA");
        System.out.println("=".repeat(40));
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Boolean loginRealizado = false;
        for (Secretaria sec : sistema.getSecretarias()) {
            if (sec.getEmail().equals(email) && sec.autenticar(senha)) {
                System.out.println("✅ Login realizado com sucesso! Bem-vinda, " + sec.getNome());
                MainSecretaria.setSecretaria(sec);
                MainSecretaria.menuSecretaria();
                loginRealizado = true;
                break;
            }
        }
        if (!loginRealizado) {
            System.out.println("❌ Email ou senha incorretos!");
        }
    }
    
    private static void loginAluno() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("👨‍🎓 LOGIN ALUNO");
        System.out.println("=".repeat(40));
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        for (Aluno aluno : sistema.getAlunos()) {
            if (aluno.getEmail().equals(email) && aluno.autenticar(senha)) {
                System.out.println("✅ Login realizado com sucesso! Bem-vindo, " + aluno.getNome());
                MainAluno.setAluno(aluno);
                MainAluno.menuAluno();
                return;
            }
        }
        System.out.println("❌ Email ou senha incorretos!");
    }
    
    private static void loginProfessor() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("👨‍🏫 LOGIN PROFESSOR");
        System.out.println("=".repeat(40));
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        for (Professor professor : sistema.getProfessores()) {
            if (professor.getEmail().equals(email) && professor.autenticar(senha)) {
                System.out.println("✅ Login realizado com sucesso! Bem-vindo, " + professor.getNome());
                MainProfessor.setProfessor(professor);
                MainProfessor.menuProfessor();
                return;
            }
        }
        System.out.println("❌ Email ou senha incorretos!");
    }
    
    private static void executarDemonstracao() {
        System.out.println("\n🎬 Executando demonstração completa do sistema...");
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
        
        DemonstracaoSistema.main(new String[]{});
        
        System.out.println("\nPressione Enter para voltar ao menu...");
        scanner.nextLine();
    }
    
    private static void mostrarEstatisticas() {
        System.out.println("\n📊 ESTATÍSTICAS DO SISTEMA");
        System.out.println("=".repeat(50));
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
        
        System.out.println("=".repeat(50));
    }
}
