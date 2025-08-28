import java.time.LocalDateTime;
import java.util.*;

public class TesteSistema {
    public static void main(String[] args) {
        System.out.println("=== TESTE COMPLETO DO SISTEMA DE MATRÍCULAS ===\n");

        SistemaMatriculas sistema = SistemaMatriculas.getInstance();

        System.out.println("1. CRIANDO DADOS DE EXEMPLO:");
        criarDadosExemplo(sistema);

        System.out.println("\n2. SALVANDO DADOS:");
        sistema.salvarDados();

        System.out.println("\n3. TESTANDO PERSISTÊNCIA:");
        limparDadosMemoria(sistema);
        sistema.carregarDados();

        System.out.println("\n4. TESTANDO FUNCIONALIDADES:");
        testarFuncionalidades(sistema);

        System.out.println("\n=== TESTE CONCLUÍDO COM SUCESSO ===");
    }

    private static void criarDadosExemplo(SistemaMatriculas sistema) {
        // Criar professores
        Professor prof1 = new Professor("PROF001", "Dr. João Silva", "joao.silva@universidade.edu", "senha123", "12345",
                "Programação");
        Professor prof2 = new Professor("PROF002", "Dra. Maria Santos", "maria.santos@universidade.edu", "senha456",
                "67890", "Banco de Dados");
        sistema.getProfessores().add(prof1);
        sistema.getProfessores().add(prof2);
        System.out.println("Professores criados: " + prof1.getNome() + ", " + prof2.getNome());

        // Criar cursos
        Curso curso1 = new Curso("CUR001", "Ciência da Computação", 240, prof1);
        Curso curso2 = new Curso("CUR002", "Sistemas de Informação", 220, prof2);
        sistema.getCursos().add(curso1);
        sistema.getCursos().add(curso2);
        System.out.println("Cursos criados: " + curso1.getNome() + ", " + curso2.getNome());

        // Criar disciplinas
        Disciplina disc1 = new Disciplina("DISC001", "Programação Orientada a Objetos", "POO001", 4, prof1, curso1,
                true, "2024.1");
        Disciplina disc2 = new Disciplina("DISC002", "Banco de Dados", "BD001", 4, prof2, curso1, true, "2024.1");
        Disciplina disc3 = new Disciplina("DISC003", "Inteligência Artificial", "IA001", 3, prof1, curso1, false,
                "2024.1");
        sistema.getDisciplinas().add(disc1);
        sistema.getDisciplinas().add(disc2);
        sistema.getDisciplinas().add(disc3);
        System.out.println("Disciplinas criadas: " + disc1.getNome() + ", " + disc2.getNome() + ", " + disc3.getNome());

        // Criar alunos
        Aluno aluno1 = new Aluno("ALU001", "Pedro Oliveira", "pedro.oliveira@universidade.edu", "senha789", "2024001",
                curso1);
        Aluno aluno2 = new Aluno("ALU002", "Ana Costa", "ana.costa@universidade.edu", "senha012", "2024002", curso1);
        Aluno aluno3 = new Aluno("ALU003", "Carlos Lima", "carlos.lima@universidade.edu", "senha345", "2024003",
                curso2);
        sistema.getAlunos().add(aluno1);
        sistema.getAlunos().add(aluno2);
        sistema.getAlunos().add(aluno3);
        System.out.println("Alunos criados: " + aluno1.getNome() + ", " + aluno2.getNome() + ", " + aluno3.getNome());

        // Criar período de matrícula
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusDays(30);
        PeriodoMatricula periodo = new PeriodoMatricula("PER001", "Matrícula 2024.1", inicio, fim, "2024.1");
        periodo.iniciarPeriodo();
        sistema.getPeriodosMatricula().add(periodo);
        System.out.println("Período de matrícula criado: " + periodo.getNome());

        // Adicionar disciplinas aos cursos
        curso1.adicionarDisciplina(disc1);
        curso1.adicionarDisciplina(disc2);
        curso1.adicionarDisciplina(disc3);
        curso2.adicionarDisciplina(disc2);

        // Adicionar disciplinas aos professores
        prof1.adicionarDisciplina(disc1);
        prof1.adicionarDisciplina(disc3);
        prof2.adicionarDisciplina(disc2);

        // Realizar matrículas
        System.out.println("\nRealizando matrículas...");
        Matricula mat1 = new Matricula("MAT001", aluno1, disc1, true, "2024.1");
        Matricula mat2 = new Matricula("MAT002", aluno1, disc2, true, "2024.1");
        Matricula mat3 = new Matricula("MAT003", aluno2, disc1, true, "2024.1");
        Matricula mat4 = new Matricula("MAT004", aluno2, disc3, false, "2024.1");

        sistema.getMatriculas().add(mat1);
        sistema.getMatriculas().add(mat2);
        sistema.getMatriculas().add(mat3);
        sistema.getMatriculas().add(mat4);

        // Adicionar alunos às disciplinas
        disc1.matricularAluno(aluno1);
        disc1.matricularAluno(aluno2);
        disc2.matricularAluno(aluno1);
        disc3.matricularAluno(aluno2);

        // Adicionar matrículas aos alunos
        aluno1.adicionarMatricula(mat1);
        aluno1.adicionarMatricula(mat2);
        aluno2.adicionarMatricula(mat3);
        aluno2.adicionarMatricula(mat4);

        System.out.println("Matrículas realizadas com sucesso!");
    }

    private static void limparDadosMemoria(SistemaMatriculas sistema) {
        System.out.println("Limpando dados da memória...");
        sistema.getAlunos().clear();
        sistema.getProfessores().clear();
        sistema.getCursos().clear();
        sistema.getDisciplinas().clear();
        sistema.getMatriculas().clear();
        sistema.getPeriodosMatricula().clear();
        sistema.getSistemaCobranca().getCobrancas().clear();
        System.out.println("Dados limpos da memória.");
    }

    private static void testarFuncionalidades(SistemaMatriculas sistema) {
        System.out.println("Testando funcionalidades do sistema...");

        // Testar login
        boolean loginSucesso = sistema.realizarLogin("pedro.oliveira@universidade.edu", "senha789", TipoUsuario.ALUNO);
        System.out.println("Login do aluno: " + loginSucesso);

        // Testar verificação de disciplinas ativas
        sistema.verificarDisciplinasAtivas();

        // Testar obtenção de alunos matriculados
        if (sistema.getDisciplinas().size() > 0) {
            Disciplina disciplina = sistema.getDisciplinas().get(0);
            List<Aluno> alunosMatriculados = sistema.getAlunosMatriculados(disciplina);
            System.out.println("Alunos matriculados em " + disciplina.getNome() + ": " + alunosMatriculados.size());
        }

        // Testar sistema de cobrança
        if (sistema.getAlunos().size() > 0 && sistema.getDisciplinas().size() > 0) {
            Aluno aluno = sistema.getAlunos().get(0);
            List<Disciplina> disciplinasAluno = aluno.getDisciplinasMatriculadas();
            if (!disciplinasAluno.isEmpty()) {
                sistema.notificarSistemaCobranca(aluno, disciplinasAluno);
            }
        }

        // Testar funcionalidades de professor
        if (sistema.getProfessores().size() > 0 && sistema.getDisciplinas().size() > 0) {
            Professor professor = sistema.getProfessores().get(0);
            Disciplina disciplina = sistema.getDisciplinas().get(0);
            List<Aluno> alunosProfessor = professor.getAlunosMatriculados(disciplina);
            System.out.println("Professor " + professor.getNome() + " tem " + alunosProfessor.size() + " alunos em "
                    + disciplina.getNome());
        }
    }
}
