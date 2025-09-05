import java.util.*;

public class MainAluno {
    private static Scanner scanner = new Scanner(System.in);
    private static SistemaMatriculas sistema = SistemaMatriculas.getInstance();
    private static Aluno alunoLogado = null;
    
    public static void setAluno(Aluno aluno) {
        alunoLogado = aluno;
    }
    
    public static void menuAluno() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("👨‍🎓 MENU ALUNO - " + alunoLogado.getNome());
            System.out.println("📚 Curso: " + alunoLogado.getCurso().getNome());
            System.out.println("🎓 Matrícula: " + alunoLogado.getMatricula());
            System.out.println("=".repeat(50));
            System.out.println("1. 📋 Ver Disciplinas Disponíveis");
            System.out.println("2. 📋 Ver Minhas Matrículas");
            System.out.println("3. 📊 Ver Estatísticas Pessoais");
            System.out.println("4. 🔍 Buscar Disciplina");
            System.out.println("0. 🔙 Voltar ao Menu Principal");
            System.out.println("=".repeat(50));
            System.out.print("Escolha uma opção: ");
            
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1: verDisciplinasDisponiveis(); break;
                    case 2: verMinhasMatriculas(); break;
                    case 3: verEstatisticasPessoais(); break;
                    case 4: buscarDisciplina(); break;
                    case 0: alunoLogado = null; return;
                    default: System.out.println("❌ Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, digite um número válido.");
            }
        }
    }
    
    // Métodos do Aluno
    private static void verDisciplinasDisponiveis() {
        System.out.println("\n📚 DISCIPLINAS DISPONÍVEIS PARA " + alunoLogado.getNome());
        System.out.println("Curso: " + alunoLogado.getCurso().getNome());
        System.out.println("-".repeat(50));
        
        List<Disciplina> disciplinasCurso = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getCurso().equals(alunoLogado.getCurso())) {
                disciplinasCurso.add(disciplina);
            }
        }
        
        if (disciplinasCurso.isEmpty()) {
            System.out.println("Nenhuma disciplina disponível para seu curso.");
        } else {
            for (Disciplina disciplina : disciplinasCurso) {
                System.out.println("• " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
                System.out.println("  Professor: " + disciplina.getProfessor().getNome());
                System.out.println("  Créditos: " + disciplina.getCreditos());
                System.out.println("  Tipo: " + (disciplina.isObrigatoria() ? "Obrigatória" : "Optativa"));
                System.out.println("  Status: " + (disciplina.isAtiva() ? "Ativa" : "Inativa"));
                System.out.println("  Vagas: " + disciplina.getVagasDisponiveis() + "/" + disciplina.getVagasTotais());
                System.out.println();
            }
        }
    }
    
    private static void verMinhasMatriculas() {
        System.out.println("\n📋 MINHAS MATRÍCULAS - " + alunoLogado.getNome());
        System.out.println("-".repeat(50));
        
        if (alunoLogado.getMatriculas().isEmpty()) {
            System.out.println("Você não possui matrículas.");
        } else {
            for (Matricula matricula : alunoLogado.getMatriculas()) {
                System.out.println("• " + matricula.getDisciplina().getNome());
                System.out.println("  Professor: " + matricula.getDisciplina().getProfessor().getNome());
                System.out.println("  Tipo: " + (matricula.isObrigatoria() ? "Obrigatória" : "Optativa"));
                System.out.println("  Semestre: " + matricula.getSemestre());
                System.out.println("  Status: " + (matricula.isAtiva() ? "Ativa" : "Inativa"));
                System.out.println();
            }
        }
    }
    
    private static void verEstatisticasPessoais() {
        System.out.println("\n📊 ESTATÍSTICAS PESSOAIS - " + alunoLogado.getNome());
        System.out.println("-".repeat(50));
        System.out.println("🎓 Matrícula: " + alunoLogado.getMatricula());
        System.out.println("📚 Curso: " + alunoLogado.getCurso().getNome());
        System.out.println("📝 Total de Matrículas: " + alunoLogado.getMatriculas().size());
        System.out.println("📚 Matrículas Obrigatórias: " + alunoLogado.getQuantidadeMatriculasObrigatorias() + "/4");
        System.out.println("🎯 Matrículas Optativas: " + alunoLogado.getQuantidadeMatriculasOptativas() + "/2");
        System.out.println("💯 Créditos Cursados: " + alunoLogado.getCreditosCursados());
    }
    
    private static void buscarDisciplina() {
        System.out.print("\n🔍 Digite o nome ou código da disciplina: ");
        String busca = scanner.nextLine().toLowerCase();
        
        System.out.println("\n📚 RESULTADOS DA BUSCA:");
        System.out.println("-".repeat(50));
        
        boolean encontrou = false;
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getNome().toLowerCase().contains(busca) || 
                disciplina.getCodigo().toLowerCase().contains(busca)) {
                
                System.out.println("• " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
                System.out.println("  Professor: " + disciplina.getProfessor().getNome());
                System.out.println("  Curso: " + disciplina.getCurso().getNome());
                System.out.println("  Créditos: " + disciplina.getCreditos());
                System.out.println("  Tipo: " + (disciplina.isObrigatoria() ? "Obrigatória" : "Optativa"));
                System.out.println("  Status: " + (disciplina.isAtiva() ? "Ativa" : "Inativa"));
                System.out.println();
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhuma disciplina encontrada com o termo: " + busca);
        }
    }
}
