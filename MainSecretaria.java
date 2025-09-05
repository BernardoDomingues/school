import java.util.*;

public class MainSecretaria {
    private static Scanner scanner = new Scanner(System.in);
    private static SistemaMatriculas sistema = SistemaMatriculas.getInstance();
    private static Secretaria secretaria = null;
    
    public static void setSecretaria(Secretaria sec) {
        secretaria = sec;
    }
    
    public static void menuSecretaria() {
        while (true) {
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
            System.out.print("Escolha uma opção: ");
            
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1: cadastrarCurso(); break;
                    case 2: cadastrarDisciplina(); break;
                    case 3: cadastrarProfessor(); break;
                    case 4: cadastrarAluno(); break;
                    case 5: listarCursos(); break;
                    case 6: listarDisciplinas(); break;
                    case 7: listarProfessores(); break;
                    case 8: listarAlunos(); break;
                    case 9: criarPeriodoMatricula(); break;
                    case 10: matricularAluno(); break;
                    case 11: cancelarMatricula(); break;
                    case 12: gerenciarDisciplinas(); break;
                    case 13: secretaria.gerarRelatorioGeral(); break;
                    case 14: gerarRelatorioMatriculas(); break;
                    case 15: gerarRelatorioDisciplinas(); break;
                    case 16: secretaria.salvarDados(); break;
                    case 17: secretaria.carregarDados(); break;
                    case 0: return;
                    default: System.out.println("❌ Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, digite um número válido.");
            }
        }
    }
    
    // Métodos da Secretaria
    private static void cadastrarCurso() {
        System.out.println("\n📚 CADASTRAR CURSO");
        System.out.print("ID do Curso: ");
        String id = scanner.nextLine();
        System.out.print("Nome do Curso: ");
        String nome = scanner.nextLine();
        System.out.print("Créditos Totais: ");
        int creditos = Integer.parseInt(scanner.nextLine());
        
        // Buscar professor para ser coordenador
        System.out.println("Professores disponíveis:");
        for (int i = 0; i < sistema.getProfessores().size(); i++) {
            System.out.println((i+1) + ". " + sistema.getProfessores().get(i).getNome());
        }
        System.out.print("Escolha o coordenador (número): ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indice >= 0 && indice < sistema.getProfessores().size()) {
            Professor coordenador = sistema.getProfessores().get(indice);
            Curso novoCurso = new Curso(id, nome, creditos, coordenador);
            secretaria.cadastrarCurso(novoCurso);
            secretaria.definirCoordenadorCurso(novoCurso, coordenador);
            System.out.println("✅ Curso cadastrado com sucesso!");
        } else {
            System.out.println("❌ Professor inválido!");
        }
    }
    
    private static void cadastrarDisciplina() {
        System.out.println("\n📚 CADASTRAR DISCIPLINA");
        System.out.print("ID da Disciplina: ");
        String id = scanner.nextLine();
        System.out.print("Nome da Disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Código da Disciplina: ");
        String codigo = scanner.nextLine();
        System.out.print("Créditos: ");
        int creditos = Integer.parseInt(scanner.nextLine());
        System.out.print("É obrigatória? (s/n): ");
        boolean obrigatoria = scanner.nextLine().toLowerCase().startsWith("s");
        System.out.print("Semestre (ex: 2024.1): ");
        String semestre = scanner.nextLine();
        
        // Escolher professor
        System.out.println("Professores disponíveis:");
        for (int i = 0; i < sistema.getProfessores().size(); i++) {
            System.out.println((i+1) + ". " + sistema.getProfessores().get(i).getNome());
        }
        System.out.print("Escolha o professor (número): ");
        int indiceProf = Integer.parseInt(scanner.nextLine()) - 1;
        
        // Escolher curso
        System.out.println("Cursos disponíveis:");
        for (int i = 0; i < sistema.getCursos().size(); i++) {
            System.out.println((i+1) + ". " + sistema.getCursos().get(i).getNome());
        }
        System.out.print("Escolha o curso (número): ");
        int indiceCurso = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indiceProf >= 0 && indiceProf < sistema.getProfessores().size() &&
            indiceCurso >= 0 && indiceCurso < sistema.getCursos().size()) {
            
            Professor professor = sistema.getProfessores().get(indiceProf);
            Curso curso = sistema.getCursos().get(indiceCurso);
            
            Disciplina novaDisciplina = secretaria.criarDisciplina(id, nome, codigo, creditos, 
                                                                  professor, curso, obrigatoria, semestre);
            secretaria.adicionarDisciplinaAoCurso(curso, novaDisciplina);
            System.out.println("✅ Disciplina cadastrada com sucesso!");
        } else {
            System.out.println("❌ Professor ou curso inválido!");
        }
    }
    
    private static void cadastrarProfessor() {
        System.out.println("\n👨‍🏫 CADASTRAR PROFESSOR");
        System.out.print("ID do Professor: ");
        String id = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("SIAPE: ");
        String siape = scanner.nextLine();
        System.out.print("Área de Especialização: ");
        String area = scanner.nextLine();
        
        Professor novoProfessor = new Professor(id, nome, email, senha, siape, area);
        secretaria.cadastrarProfessor(novoProfessor);
        System.out.println("✅ Professor cadastrado com sucesso!");
    }
    
    private static void cadastrarAluno() {
        System.out.println("\n👨‍🎓 CADASTRAR ALUNO");
        System.out.print("ID do Aluno: ");
        String id = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        System.out.print("Número de Matrícula: ");
        String matricula = scanner.nextLine();
        
        // Escolher curso
        System.out.println("Cursos disponíveis:");
        for (int i = 0; i < sistema.getCursos().size(); i++) {
            System.out.println((i+1) + ". " + sistema.getCursos().get(i).getNome());
        }
        System.out.print("Escolha o curso (número): ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indice >= 0 && indice < sistema.getCursos().size()) {
            Curso curso = sistema.getCursos().get(indice);
            Aluno novoAluno = new Aluno(id, nome, email, senha, matricula, curso);
            secretaria.cadastrarAluno(novoAluno);
            System.out.println("✅ Aluno cadastrado com sucesso!");
        } else {
            System.out.println("❌ Curso inválido!");
        }
    }
    
    private static void listarCursos() {
        System.out.println("\n📚 CURSOS CADASTRADOS:");
        if (sistema.getCursos().isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
        } else {
            for (Curso curso : sistema.getCursos()) {
                System.out.println("• " + curso.getNome() + " (" + curso.getCreditosTotais() + " créditos)");
                System.out.println("  Coordenador: " + curso.getCoordenador().getNome());
            }
        }
    }
    
    private static void listarDisciplinas() {
        System.out.println("\n📚 DISCIPLINAS CADASTRADAS:");
        if (sistema.getDisciplinas().isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            for (Disciplina disciplina : sistema.getDisciplinas()) {
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
    
    private static void listarProfessores() {
        System.out.println("\n👨‍🏫 PROFESSORES CADASTRADOS:");
        if (sistema.getProfessores().isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            for (Professor professor : sistema.getProfessores()) {
                System.out.println("• " + professor.getNome() + " (" + professor.getEmail() + ")");
                System.out.println("  Área: " + professor.getAreaEspecializacao());
                System.out.println("  SIAPE: " + professor.getSiape());
            }
        }
    }
    
    private static void listarAlunos() {
        System.out.println("\n👨‍🎓 ALUNOS CADASTRADOS:");
        if (sistema.getAlunos().isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno aluno : sistema.getAlunos()) {
                System.out.println("• " + aluno.getNome() + " (" + aluno.getEmail() + ")");
                System.out.println("  Matrícula: " + aluno.getMatricula());
                System.out.println("  Curso: " + aluno.getCurso().getNome());
                System.out.println("  Matrículas: " + aluno.getMatriculas().size());
            }
        }
    }
    
    private static void criarPeriodoMatricula() {
        System.out.println("\n📅 CRIAR PERÍODO DE MATRÍCULA");
        System.out.print("ID do Período: ");
        String id = scanner.nextLine();
        System.out.print("Nome do Período: ");
        String nome = scanner.nextLine();
        System.out.print("Semestre (ex: 2024.1): ");
        String semestre = scanner.nextLine();
        System.out.print("Duração em dias: ");
        int duracao = Integer.parseInt(scanner.nextLine());
        
        java.time.LocalDateTime inicio = java.time.LocalDateTime.now();
        java.time.LocalDateTime fim = inicio.plusDays(duracao);
        
        secretaria.criarPeriodoMatricula(id, nome, inicio, fim, semestre);
        secretaria.iniciarPeriodoMatricula(sistema.getPeriodosMatricula().get(sistema.getPeriodosMatricula().size() - 1));
        System.out.println("✅ Período de matrícula criado e ativado!");
    }
    
    private static void matricularAluno() {
        System.out.println("\n🎯 MATRICULAR ALUNO");
        
        System.out.println("Alunos disponíveis:");
        for (int i = 0; i < sistema.getAlunos().size(); i++) {
            System.out.println((i+1) + ". " + sistema.getAlunos().get(i).getNome());
        }
        System.out.print("Escolha o aluno (número): ");
        int indiceAluno = Integer.parseInt(scanner.nextLine()) - 1;
        
        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < sistema.getDisciplinas().size(); i++) {
            Disciplina disc = sistema.getDisciplinas().get(i);
            System.out.println((i+1) + ". " + disc.getNome() + " (" + (disc.isObrigatoria() ? "Obrigatória" : "Optativa") + ")");
        }
        System.out.print("Escolha a disciplina (número): ");
        int indiceDisc = Integer.parseInt(scanner.nextLine()) - 1;
        
        System.out.print("É matrícula obrigatória? (s/n): ");
        boolean obrigatoria = scanner.nextLine().toLowerCase().startsWith("s");
        
        if (indiceAluno >= 0 && indiceAluno < sistema.getAlunos().size() &&
            indiceDisc >= 0 && indiceDisc < sistema.getDisciplinas().size()) {
            
            Aluno aluno = sistema.getAlunos().get(indiceAluno);
            Disciplina disciplina = sistema.getDisciplinas().get(indiceDisc);
            
            boolean sucesso = secretaria.matricularAluno(aluno, disciplina, obrigatoria);
            if (sucesso) {
                System.out.println("✅ Matrícula realizada com sucesso!");
            } else {
                System.out.println("❌ Falha na matrícula. Verifique as regras de negócio.");
            }
        } else {
            System.out.println("❌ Aluno ou disciplina inválido!");
        }
    }
    
    private static void cancelarMatricula() {
        System.out.println("\n❌ CANCELAR MATRÍCULA");
        
        System.out.println("Alunos disponíveis:");
        for (int i = 0; i < sistema.getAlunos().size(); i++) {
            System.out.println((i+1) + ". " + sistema.getAlunos().get(i).getNome());
        }
        System.out.print("Escolha o aluno (número): ");
        int indiceAluno = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indiceAluno >= 0 && indiceAluno < sistema.getAlunos().size()) {
            Aluno aluno = sistema.getAlunos().get(indiceAluno);
            
            System.out.println("Matrículas do aluno " + aluno.getNome() + ":");
            for (int i = 0; i < aluno.getMatriculas().size(); i++) {
                Matricula mat = aluno.getMatriculas().get(i);
                System.out.println((i+1) + ". " + mat.getDisciplina().getNome());
            }
            System.out.print("Escolha a matrícula para cancelar (número): ");
            int indiceMat = Integer.parseInt(scanner.nextLine()) - 1;
            
            if (indiceMat >= 0 && indiceMat < aluno.getMatriculas().size()) {
                Matricula matricula = aluno.getMatriculas().get(indiceMat);
                boolean sucesso = secretaria.cancelarMatricula(aluno, matricula.getDisciplina());
                if (sucesso) {
                    System.out.println("✅ Matrícula cancelada com sucesso!");
                } else {
                    System.out.println("❌ Falha ao cancelar matrícula.");
                }
            } else {
                System.out.println("❌ Matrícula inválida!");
            }
        } else {
            System.out.println("❌ Aluno inválido!");
        }
    }
    
    private static void gerenciarDisciplinas() {
        System.out.println("\n🔄 GERENCIAR DISCIPLINAS");
        System.out.println("1. Ativar Disciplina");
        System.out.println("2. Desativar Disciplina");
        System.out.println("3. Verificar Disciplinas Ativas");
        System.out.print("Escolha uma opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        
        switch (opcao) {
            case 1:
                ativarDisciplina();
                break;
            case 2:
                desativarDisciplina();
                break;
            case 3:
                secretaria.verificarDisciplinasAtivas();
                break;
            default:
                System.out.println("❌ Opção inválida!");
        }
    }
    
    private static void ativarDisciplina() {
        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < sistema.getDisciplinas().size(); i++) {
            Disciplina disc = sistema.getDisciplinas().get(i);
            System.out.println((i+1) + ". " + disc.getNome() + " - " + (disc.isAtiva() ? "Ativa" : "Inativa"));
        }
        System.out.print("Escolha a disciplina para ativar (número): ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indice >= 0 && indice < sistema.getDisciplinas().size()) {
            secretaria.ativarDisciplina(sistema.getDisciplinas().get(indice));
            System.out.println("✅ Disciplina ativada!");
        } else {
            System.out.println("❌ Disciplina inválida!");
        }
    }
    
    private static void desativarDisciplina() {
        System.out.println("Disciplinas disponíveis:");
        for (int i = 0; i < sistema.getDisciplinas().size(); i++) {
            Disciplina disc = sistema.getDisciplinas().get(i);
            System.out.println((i+1) + ". " + disc.getNome() + " - " + (disc.isAtiva() ? "Ativa" : "Inativa"));
        }
        System.out.print("Escolha a disciplina para desativar (número): ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indice >= 0 && indice < sistema.getDisciplinas().size()) {
            secretaria.desativarDisciplina(sistema.getDisciplinas().get(indice));
            System.out.println("✅ Disciplina desativada!");
        } else {
            System.out.println("❌ Disciplina inválida!");
        }
    }
    
    private static void gerarRelatorioMatriculas() {
        System.out.print("Digite o semestre (ex: 2024.1): ");
        String semestre = scanner.nextLine();
        secretaria.gerarRelatorioMatriculas(semestre);
    }
    
    private static void gerarRelatorioDisciplinas() {
        System.out.print("Digite o semestre (ex: 2024.1): ");
        String semestre = scanner.nextLine();
        secretaria.gerarRelatorioDisciplinas(semestre);
    }
}
