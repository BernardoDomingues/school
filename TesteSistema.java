import java.time.LocalDateTime;
import java.util.*;

public class TesteSistema {
    public static void main(String[] args) {
        System.out.println("=== TESTE COMPLETO DO SISTEMA DE MATRÍCULAS ===\n");

        SistemaMatriculas sistema = SistemaMatriculas.getInstance();

        // Verificar se já existe uma secretaria carregada
        Secretaria secretaria;
        if (sistema.getSecretarias().isEmpty()) {
            secretaria = new Secretaria("SEC001", "Maria da Secretaria", 
                                     "secretaria@universidade.edu", "senha123", sistema);
            sistema.getSecretarias().add(secretaria);
            sistema.salvarDados();
            System.out.println("Secretaria criada: " + secretaria.getNome());
        } else {
            secretaria = sistema.getSecretarias().get(0);
            System.out.println("Secretaria carregada: " + secretaria.getNome());
        }
        System.out.println("Tipo de usuário: " + secretaria.getTipo());
        
        boolean autenticado = secretaria.autenticar("senha123");
        System.out.println("Autenticação da secretaria: " + autenticado);

        System.out.println("\n=== TESTANDO FUNCIONALIDADES DA SECRETARIA ===");
        
        System.out.println("\n1. CRIANDO PROFESSORES:");
        Professor prof1 = new Professor("PROF001", "Dr. João Silva", "joao.silva@universidade.edu", 
                                       "senha123", "12345", "Programação");
        Professor prof2 = new Professor("PROF002", "Dra. Maria Santos", "maria.santos@universidade.edu", 
                                       "senha456", "67890", "Banco de Dados");
        
        secretaria.cadastrarProfessor(prof1);
        secretaria.cadastrarProfessor(prof2);
        
        System.out.println("\n2. CRIANDO CURSOS:");
        Curso curso1 = new Curso("CUR001", "Ciência da Computação", 240, prof1);
        Curso curso2 = new Curso("CUR002", "Sistemas de Informação", 220, prof2);
        
        secretaria.cadastrarCurso(curso1);
        secretaria.cadastrarCurso(curso2);
        
        System.out.println("\n3. DEFININDO COORDENADORES:");
        secretaria.definirCoordenadorCurso(curso1, prof1);
        secretaria.definirCoordenadorCurso(curso2, prof2);
        
        System.out.println("\n4. CRIANDO DISCIPLINAS:");
        Disciplina disc1 = secretaria.criarDisciplina("DISC001", "Programação Orientada a Objetos", 
                                                     "POO001", 4, prof1, curso1, true, "2024.1");
        Disciplina disc2 = secretaria.criarDisciplina("DISC002", "Banco de Dados", 
                                                     "BD001", 4, prof2, curso1, true, "2024.1");
        Disciplina disc3 = secretaria.criarDisciplina("DISC003", "Inteligência Artificial", 
                                                     "IA001", 3, prof1, curso1, false, "2024.1");
        
        System.out.println("\n5. ADICIONANDO DISCIPLINAS AOS CURSOS:");
        secretaria.adicionarDisciplinaAoCurso(curso1, disc1);
        secretaria.adicionarDisciplinaAoCurso(curso1, disc2);
        secretaria.adicionarDisciplinaAoCurso(curso1, disc3);
        secretaria.adicionarDisciplinaAoCurso(curso2, disc2);
        
        System.out.println("\n6. CRIANDO ALUNOS:");
        Aluno aluno1 = new Aluno("ALU001", "Pedro Oliveira", "pedro.oliveira@universidade.edu", 
                                "senha789", "2024001", curso1);
        Aluno aluno2 = new Aluno("ALU002", "Ana Costa", "ana.costa@universidade.edu", 
                                "senha012", "2024002", curso1);
        Aluno aluno3 = new Aluno("ALU003", "Carlos Lima", "carlos.lima@universidade.edu", 
                                "senha345", "2024003", curso2);
        secretaria.cadastrarAluno(aluno1);
        secretaria.cadastrarAluno(aluno2);
        secretaria.cadastrarAluno(aluno3);
        
        System.out.println("\n7. CRIANDO PERÍODO DE MATRÍCULA:");
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusDays(30);
        secretaria.criarPeriodoMatricula("PER001", "Matrícula 2024.1", inicio, fim, "2024.1");
        
        PeriodoMatricula periodo = sistema.getPeriodosMatricula().get(0);
        secretaria.iniciarPeriodoMatricula(periodo);
        
        System.out.println("\n8. GERANDO CURRÍCULO DO SEMESTRE:");
        secretaria.gerarCurriculoSemestre("2024.1");
        
        System.out.println("\n9. REALIZANDO MATRÍCULAS:");
        boolean mat1 = secretaria.matricularAluno(aluno1, disc1, true);
        boolean mat2 = secretaria.matricularAluno(aluno1, disc2, true);
        boolean mat3 = secretaria.matricularAluno(aluno2, disc1, true);
        boolean mat4 = secretaria.matricularAluno(aluno2, disc3, false);
        
        System.out.println("Matrícula 1 (Pedro em POO): " + mat1);
        System.out.println("Matrícula 2 (Pedro em BD): " + mat2);
        System.out.println("Matrícula 3 (Ana em POO): " + mat3);
        System.out.println("Matrícula 4 (Ana em IA): " + mat4);
        
        System.out.println("\n10. VERIFICANDO DISCIPLINAS ATIVAS:");
        secretaria.verificarDisciplinasAtivas();
        
        System.out.println("\n11. GERANDO RELATÓRIOS:");
        secretaria.gerarRelatorioGeral();
        secretaria.gerarRelatorioMatriculas("2024.1");
        secretaria.gerarRelatorioDisciplinas("2024.1");
        
        System.out.println("\n12. TESTANDO CONSULTAS:");
        List<Aluno> alunosCurso1 = secretaria.getAlunosPorCurso(curso1);
        System.out.println("Alunos do curso " + curso1.getNome() + ": " + alunosCurso1.size());
        
        List<Professor> profsProgramacao = secretaria.getProfessoresPorArea("Programação");
        System.out.println("Professores de Programação: " + profsProgramacao.size());
        
        List<Disciplina> disciplinasSemestre = secretaria.getDisciplinasPorSemestre("2024.1");
        System.out.println("Disciplinas do semestre 2024.1: " + disciplinasSemestre.size());
        
        List<Aluno> alunosPOO = secretaria.getAlunosMatriculados(disc1);
        System.out.println("Alunos matriculados em POO: " + alunosPOO.size());
        
        System.out.println("\n13. TESTANDO LIMITES DE MATRÍCULAS OBRIGATÓRIAS:");
        // Criar mais disciplinas obrigatórias para testar limite de 4
        Disciplina disc4 = secretaria.criarDisciplina("DISC004", "Estruturas de Dados", 
                                                     "ED001", 4, prof1, curso1, true, "2024.1");
        Disciplina disc5 = secretaria.criarDisciplina("DISC005", "Algoritmos", 
                                                     "ALG001", 4, prof1, curso1, true, "2024.1");
        Disciplina disc6 = secretaria.criarDisciplina("DISC006", "Sistemas Operacionais", 
                                                     "SO001", 4, prof1, curso1, true, "2024.1");
        
        secretaria.adicionarDisciplinaAoCurso(curso1, disc4);
        secretaria.adicionarDisciplinaAoCurso(curso1, disc5);
        secretaria.adicionarDisciplinaAoCurso(curso1, disc6);
        secretaria.ativarDisciplina(disc4);
        secretaria.ativarDisciplina(disc5);
        secretaria.ativarDisciplina(disc6);
        
        // Testar limite de 4 matrículas obrigatórias
        System.out.println("Testando limite de 4 matrículas obrigatórias para Pedro:");
        boolean mat5 = secretaria.matricularAluno(aluno1, disc4, true);
        boolean mat6 = secretaria.matricularAluno(aluno1, disc5, true);
        boolean mat7 = secretaria.matricularAluno(aluno1, disc6, true); // Deve falhar (5ª obrigatória)
        
        System.out.println("Matrícula 5 (Pedro em ED): " + mat5);
        System.out.println("Matrícula 6 (Pedro em ALG): " + mat6);
        System.out.println("Matrícula 7 (Pedro em SO - deve falhar): " + mat7);
        System.out.println("Matrículas obrigatórias de Pedro: " + aluno1.getQuantidadeMatriculasObrigatorias());
        
        System.out.println("\n14. TESTANDO LIMITES DE MATRÍCULAS OPTATIVAS:");
        // Criar mais disciplinas optativas para testar limite de 2
        Disciplina disc7 = secretaria.criarDisciplina("DISC007", "Redes de Computadores", 
                                                     "RED001", 3, prof1, curso1, false, "2024.1");
        Disciplina disc8 = secretaria.criarDisciplina("DISC008", "Segurança da Informação", 
                                                     "SEG001", 3, prof1, curso1, false, "2024.1");
        Disciplina disc9 = secretaria.criarDisciplina("DISC009", "Engenharia de Software", 
                                                     "ES001", 3, prof1, curso1, false, "2024.1");
        
        secretaria.adicionarDisciplinaAoCurso(curso1, disc7);
        secretaria.adicionarDisciplinaAoCurso(curso1, disc8);
        secretaria.adicionarDisciplinaAoCurso(curso1, disc9);
        secretaria.ativarDisciplina(disc7);
        secretaria.ativarDisciplina(disc8);
        secretaria.ativarDisciplina(disc9);
        
        // Testar limite de 2 matrículas optativas
        System.out.println("Testando limite de 2 matrículas optativas para Ana:");
        boolean mat8 = secretaria.matricularAluno(aluno2, disc7, false);
        boolean mat9 = secretaria.matricularAluno(aluno2, disc8, false);
        boolean mat10 = secretaria.matricularAluno(aluno2, disc9, false); // Deve falhar (3ª optativa)
        
        System.out.println("Matrícula 8 (Ana em RED): " + mat8);
        System.out.println("Matrícula 9 (Ana em SEG): " + mat9);
        System.out.println("Matrícula 10 (Ana em ES - deve falhar): " + mat10);
        System.out.println("Matrículas optativas de Ana: " + aluno2.getQuantidadeMatriculasOptativas());
        
        System.out.println("\n15. TESTANDO LIMITE DE 60 ALUNOS POR DISCIPLINA:");
        // Criar muitos alunos para testar limite de 60
        System.out.println("Criando 62 alunos para testar limite de 60 por disciplina...");
        for (int i = 1; i <= 62; i++) {
            Aluno aluno = new Aluno("ALU" + String.format("%03d", i + 3), 
                                   "Aluno " + i, "aluno" + i + "@universidade.edu", 
                                   "senha" + i, "2024" + String.format("%03d", i + 3), curso1);
            secretaria.cadastrarAluno(aluno);
        }
        
        // Tentar matricular 62 alunos em uma disciplina (deve aceitar apenas 60)
        Disciplina discTeste = secretaria.criarDisciplina("DISCTESTE", "Disciplina Teste", 
                                                         "TEST001", 4, prof1, curso1, true, "2024.1");
        secretaria.adicionarDisciplinaAoCurso(curso1, discTeste);
        secretaria.ativarDisciplina(discTeste);
        
        int matriculasSucesso = 0;
        int matriculasFalha = 0;
        for (int i = 0; i < 62; i++) {
            Aluno aluno = sistema.getAlunos().get(i + 3); // Pular os 3 alunos iniciais
            boolean sucesso = secretaria.matricularAluno(aluno, discTeste, true);
            if (sucesso) {
                matriculasSucesso++;
            } else {
                matriculasFalha++;
            }
        }
        
        System.out.println("Matrículas bem-sucedidas: " + matriculasSucesso);
        System.out.println("Matrículas que falharam: " + matriculasFalha);
        System.out.println("Alunos matriculados na disciplina teste: " + discTeste.getQuantidadeAlunosMatriculados());
        
        System.out.println("\n16. TESTANDO VALIDAÇÕES DE NEGÓCIO:");
        
        // Teste 1: Matrícula com período inativo
        System.out.println("\n16.1. Testando matrícula com período inativo:");
        secretaria.encerrarPeriodoMatricula(periodo);
        Aluno alunoTeste = new Aluno("ALUTESTE", "Aluno Teste", "teste@universidade.edu", 
                                    "senha123", "2024999", curso1);
        secretaria.cadastrarAluno(alunoTeste);
        boolean matInativo = secretaria.matricularAluno(alunoTeste, disc1, true);
        System.out.println("Matrícula com período inativo (deve falhar): " + matInativo);
        
        // Reativar período para próximos testes
        secretaria.iniciarPeriodoMatricula(periodo);
        
        // Teste 2: Matrícula em disciplina inativa
        System.out.println("\n16.2. Testando matrícula em disciplina inativa:");
        Disciplina discInativa = secretaria.criarDisciplina("DISCINATIVA", "Disciplina Inativa", 
                                                           "INAT001", 4, prof1, curso1, true, "2024.1");
        secretaria.desativarDisciplina(discInativa);
        boolean matInativa = secretaria.matricularAluno(alunoTeste, discInativa, true);
        System.out.println("Matrícula em disciplina inativa (deve falhar): " + matInativa);
        
        // Teste 3: Matrícula em disciplina de outro curso
        System.out.println("\n16.3. Testando matrícula em disciplina de outro curso:");
        Disciplina discOutroCurso = secretaria.criarDisciplina("DISCOUTRO", "Disciplina Outro Curso", 
                                                              "OUTRO001", 4, prof2, curso2, true, "2024.1");
        secretaria.ativarDisciplina(discOutroCurso);
        boolean matOutroCurso = secretaria.matricularAluno(alunoTeste, discOutroCurso, true);
        System.out.println("Matrícula em disciplina de outro curso (deve falhar): " + matOutroCurso);
        
        // Teste 4: Matrícula duplicada
        System.out.println("\n16.4. Testando matrícula duplicada:");
        secretaria.ativarDisciplina(disc1);
        boolean matDuplicada = secretaria.matricularAluno(aluno1, disc1, true);
        System.out.println("Matrícula duplicada (deve falhar): " + matDuplicada);
        
        System.out.println("\n17. TESTANDO CANCELAMENTO DE MATRÍCULA:");
        boolean cancelamento = secretaria.cancelarMatricula(aluno2, disc3);
        System.out.println("Cancelamento da matrícula de Ana em IA: " + cancelamento);
        
        System.out.println("\n18. SALVANDO DADOS:");
        secretaria.salvarDados();

        System.out.println("\n19. TESTANDO PERSISTÊNCIA:");
        limparDadosMemoria(sistema);
        secretaria.carregarDados();

        System.out.println("\n20. TESTANDO FUNCIONALIDADES APÓS CARREGAMENTO:");
        testarFuncionalidades(secretaria);

        System.out.println("\n=== TESTE COMPLETO CONCLUÍDO COM SUCESSO ===");
    }


    private static void limparDadosMemoria(SistemaMatriculas sistema) {
        System.out.println("Limpando dados da memória...");
        sistema.getAlunos().clear();
        sistema.getProfessores().clear();
        sistema.getCursos().clear();
        sistema.getDisciplinas().clear();
        sistema.getMatriculas().clear();
        sistema.getPeriodosMatricula().clear();
        System.out.println("Dados limpos da memória.");
    }

    private static void testarFuncionalidades(Secretaria secretaria) {
        SistemaMatriculas sistema = secretaria.getSistema();
        System.out.println("Testando funcionalidades do sistema através da secretaria...");

        boolean loginSecretaria = secretaria.autenticar("senha123");
        System.out.println("Login da secretaria: " + loginSecretaria);

        boolean loginSucesso = sistema.realizarLogin("pedro.oliveira@universidade.edu", "senha789", TipoUsuario.ALUNO);
        System.out.println("Login do aluno: " + loginSucesso);

        secretaria.verificarDisciplinasAtivas();

        if (sistema.getDisciplinas().size() > 0) {
            Disciplina disciplina = sistema.getDisciplinas().get(0);
            List<Aluno> alunosMatriculados = secretaria.getAlunosMatriculados(disciplina);
            System.out.println("Alunos matriculados em " + disciplina.getNome() + ": " + alunosMatriculados.size());
        }


        if (sistema.getProfessores().size() > 0 && sistema.getDisciplinas().size() > 0) {
            Professor professor = sistema.getProfessores().get(0);
            Disciplina disciplina = sistema.getDisciplinas().get(0);
            List<Aluno> alunosProfessor = professor.getAlunosMatriculados(disciplina);
            System.out.println("Professor " + professor.getNome() + " tem " + alunosProfessor.size() + " alunos em "
                    + disciplina.getNome());
        }

        System.out.println("\n=== TESTANDO FUNCIONALIDADES ESPECÍFICAS DA SECRETARIA ===");
        
        secretaria.gerarRelatorioGeral();
        secretaria.gerarRelatorioMatriculas("2024.1");
        secretaria.gerarRelatorioDisciplinas("2024.1");
        
        if (sistema.getCursos().size() > 0) {
            Curso curso = sistema.getCursos().get(0);
            List<Aluno> alunosCurso = secretaria.getAlunosPorCurso(curso);
            System.out.println("Alunos do curso " + curso.getNome() + ": " + alunosCurso.size());
        }
        
        if (sistema.getProfessores().size() > 0) {
            Professor professor = sistema.getProfessores().get(0);
            List<Professor> profsArea = secretaria.getProfessoresPorArea(professor.getAreaEspecializacao());
            System.out.println("Professores da área " + professor.getAreaEspecializacao() + ": " + profsArea.size());
        }
        
        List<Disciplina> disciplinasSemestre = secretaria.getDisciplinasPorSemestre("2024.1");
        System.out.println("Disciplinas do semestre 2024.1: " + disciplinasSemestre.size());
        
        List<PeriodoMatricula> periodosAtivos = secretaria.getPeriodosAtivos();
        System.out.println("Períodos de matrícula ativos: " + periodosAtivos.size());
        
        if (sistema.getAlunos().size() > 1 && sistema.getDisciplinas().size() > 2) {
            Aluno aluno = sistema.getAlunos().get(1);
            Disciplina disciplina = sistema.getDisciplinas().get(2);
            boolean cancelamento = secretaria.cancelarMatricula(aluno, disciplina);
            System.out.println("Cancelamento de matrícula: " + cancelamento);
        }
        
        System.out.println("\n=== TESTANDO FUNCIONALIDADES ADICIONAIS ===");
        
        if (sistema.getAlunos().size() > 0) {
            Aluno aluno = sistema.getAlunos().get(0);
            System.out.println("Aluno encontrado: " + aluno.getNome() + " (" + aluno.getEmail() + ")");
        }
        
        if (sistema.getPeriodosMatricula().size() > 0) {
            PeriodoMatricula periodo = sistema.getPeriodosMatricula().get(0);
            System.out.println("Período de matrícula: " + periodo.getNome() + " - Status: " + (periodo.isAtivo() ? "Ativo" : "Inativo"));
        }
        
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        System.out.println("Total de alunos: " + sistema.getAlunos().size());
        System.out.println("Total de professores: " + sistema.getProfessores().size());
        System.out.println("Total de cursos: " + sistema.getCursos().size());
        System.out.println("Total de disciplinas: " + sistema.getDisciplinas().size());
        System.out.println("Total de matrículas: " + sistema.getMatriculas().size());
        System.out.println("Total de períodos de matrícula: " + sistema.getPeriodosMatricula().size());
    }
}
