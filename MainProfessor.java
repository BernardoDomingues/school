import java.util.*;

public class MainProfessor {
    private static Scanner scanner = new Scanner(System.in);
    private static SistemaMatriculas sistema = SistemaMatriculas.getInstance();
    private static Professor professorLogado = null;
    
    public static void setProfessor(Professor professor) {
        professorLogado = professor;
    }
    
    public static void menuProfessor() {
        while (true) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("👨‍🏫 MENU PROFESSOR - " + professorLogado.getNome());
            System.out.println("🎯 Área: " + professorLogado.getAreaEspecializacao());
            System.out.println("=".repeat(50));
            System.out.println("1. 📋 Ver Minhas Disciplinas");
            System.out.println("2. 👥 Ver Alunos Matriculados");
            System.out.println("3. 📊 Ver Estatísticas das Disciplinas");
            System.out.println("0. 🔙 Voltar ao Menu Principal");
            System.out.println("=".repeat(50));
            System.out.print("Escolha uma opção: ");
            
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1: verMinhasDisciplinas(); break;
                    case 2: verAlunosMatriculados(); break;
                    case 3: verEstatisticasDisciplinas(); break;
                    case 0: professorLogado = null; return;
                    default: System.out.println("❌ Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor, digite um número válido.");
            }
        }
    }
    
    // Métodos do Professor
    private static void verMinhasDisciplinas() {
        System.out.println("\n📚 MINHAS DISCIPLINAS - " + professorLogado.getNome());
        System.out.println("-".repeat(50));
        
        List<Disciplina> minhasDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getProfessor().equals(professorLogado)) {
                minhasDisciplinas.add(disciplina);
            }
        }
        
        if (minhasDisciplinas.isEmpty()) {
            System.out.println("Você não possui disciplinas atribuídas.");
        } else {
            for (Disciplina disciplina : minhasDisciplinas) {
                System.out.println("• " + disciplina.getNome() + " (" + disciplina.getCodigo() + ")");
                System.out.println("  Curso: " + disciplina.getCurso().getNome());
                System.out.println("  Créditos: " + disciplina.getCreditos());
                System.out.println("  Tipo: " + (disciplina.isObrigatoria() ? "Obrigatória" : "Optativa"));
                System.out.println("  Status: " + (disciplina.isAtiva() ? "Ativa" : "Inativa"));
                System.out.println("  Alunos Matriculados: " + disciplina.getQuantidadeAlunosMatriculados());
                System.out.println();
            }
        }
    }
    
    private static void verAlunosMatriculados() {
        System.out.println("\n👥 ALUNOS MATRICULADOS - " + professorLogado.getNome());
        System.out.println("-".repeat(50));
        
        List<Disciplina> minhasDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getProfessor().equals(professorLogado)) {
                minhasDisciplinas.add(disciplina);
            }
        }
        
        if (minhasDisciplinas.isEmpty()) {
            System.out.println("Você não possui disciplinas atribuídas.");
            return;
        }
        
        System.out.println("Suas disciplinas:");
        for (int i = 0; i < minhasDisciplinas.size(); i++) {
            System.out.println((i+1) + ". " + minhasDisciplinas.get(i).getNome());
        }
        System.out.print("Escolha a disciplina (número): ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indice >= 0 && indice < minhasDisciplinas.size()) {
            Disciplina disciplina = minhasDisciplinas.get(indice);
            List<Aluno> alunos = professorLogado.getAlunosMatriculados(disciplina);
            
            System.out.println("\n👥 Alunos matriculados em " + disciplina.getNome() + ":");
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno matriculado.");
            } else {
                for (Aluno aluno : alunos) {
                    System.out.println("• " + aluno.getNome() + " (" + aluno.getMatricula() + ")");
                    System.out.println("  Email: " + aluno.getEmail());
                    System.out.println("  Curso: " + aluno.getCurso().getNome());
                    System.out.println();
                }
            }
        } else {
            System.out.println("❌ Disciplina inválida!");
        }
    }
    
    private static void verEstatisticasDisciplinas() {
        System.out.println("\n📊 ESTATÍSTICAS DAS DISCIPLINAS - " + professorLogado.getNome());
        System.out.println("-".repeat(50));
        
        List<Disciplina> minhasDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getProfessor().equals(professorLogado)) {
                minhasDisciplinas.add(disciplina);
            }
        }
        
        if (minhasDisciplinas.isEmpty()) {
            System.out.println("Você não possui disciplinas atribuídas.");
        } else {
            int totalAlunos = 0;
            int disciplinasAtivas = 0;
            
            for (Disciplina disciplina : minhasDisciplinas) {
                totalAlunos += disciplina.getQuantidadeAlunosMatriculados();
                if (disciplina.isAtiva()) {
                    disciplinasAtivas++;
                }
            }
            
            System.out.println("📚 Total de Disciplinas: " + minhasDisciplinas.size());
            System.out.println("🟢 Disciplinas Ativas: " + disciplinasAtivas);
            System.out.println("👥 Total de Alunos: " + totalAlunos);
            System.out.println("📊 Média de Alunos por Disciplina: " + 
                             (minhasDisciplinas.size() > 0 ? totalAlunos / minhasDisciplinas.size() : 0));
        }
    }
}
