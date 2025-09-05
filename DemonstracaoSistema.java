import models.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Scanner;

public class DemonstracaoSistema {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("🎓 === DEMONSTRAÇÃO DO SISTEMA DE MATRÍCULAS === 🎓\n");
        
        pausarComMensagem("Vamos demonstrar um sistema completo de matrículas universitárias!");
        
        SistemaMatriculas sistema = SistemaMatriculas.getInstance();
        
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
        
        System.out.println("\n👨‍🏫 === SEÇÃO 2: CADASTRO DE PROFESSORES ===");
        pausarComMensagem("Agora vamos cadastrar professores no sistema");
        
        Professor prof1 = new Professor("PROF001", "Dr. João Silva", "joao.silva@universidade.edu", 
                                       "senha123", "12345", "Programação");
        Professor prof2 = new Professor("PROF002", "Dra. Maria Santos", "maria.santos@universidade.edu", 
                                       "senha456", "67890", "Banco de Dados");
        
        secretaria.cadastrarProfessor(prof1);
        secretaria.cadastrarProfessor(prof2);
        
        System.out.println("✅ Professor 1: " + prof1.getNome() + " - Área: " + prof1.getAreaEspecializacao());
        System.out.println("✅ Professor 2: " + prof2.getNome() + " - Área: " + prof2.getAreaEspecializacao());
        
        pausarComMensagem("Professores são cadastrados com área de especialização");
        
        System.out.println("\n🏫 === SEÇÃO 3: CRIAÇÃO DE CURSOS ===");
        pausarComMensagem("Agora vamos criar os cursos da universidade");
        
        Curso curso1 = new Curso("CUR001", "Ciência da Computação", 240, prof1);
        Curso curso2 = new Curso("CUR002", "Sistemas de Informação", 220, prof2);
        
        secretaria.cadastrarCurso(curso1);
        secretaria.cadastrarCurso(curso2);
        
        System.out.println("✅ Curso 1: " + curso1.getNome() + " - " + curso1.getCreditosTotais() + " créditos");
        System.out.println("✅ Curso 2: " + curso2.getNome() + " - " + curso2.getCreditosTotais() + " créditos");
        
        secretaria.definirCoordenadorCurso(curso1, prof1);
        secretaria.definirCoordenadorCurso(curso2, prof2);
        
        System.out.println("👨‍💼 Coordenadores definidos para cada curso");
        
        pausarComMensagem("Cada curso tem um coordenador e carga horária definida");
        
        System.out.println("\n📚 === SEÇÃO 4: CRIAÇÃO DE DISCIPLINAS ===");
        pausarComMensagem("Agora vamos criar as disciplinas do semestre");
        
        Disciplina disc1 = secretaria.criarDisciplina("DISC001", "Programação Orientada a Objetos", 
                                                     "POO001", 4, prof1, curso1, true, "2024.1");
        Disciplina disc2 = secretaria.criarDisciplina("DISC002", "Banco de Dados", 
                                                     "BD001", 4, prof2, curso1, true, "2024.1");
        Disciplina disc3 = secretaria.criarDisciplina("DISC003", "Inteligência Artificial", 
                                                     "IA001", 3, prof1, curso1, false, "2024.1");
        
        System.out.println("✅ Disciplina 1: " + disc1.getNome() + " - " + disc1.getCreditos() + " créditos - OBRIGATÓRIA");
        System.out.println("✅ Disciplina 2: " + disc2.getNome() + " - " + disc2.getCreditos() + " créditos - OBRIGATÓRIA");
        System.out.println("✅ Disciplina 3: " + disc3.getNome() + " - " + disc3.getCreditos() + " créditos - OPTATIVA");
        
        secretaria.adicionarDisciplinaAoCurso(curso1, disc1);
        secretaria.adicionarDisciplinaAoCurso(curso1, disc2);
        secretaria.adicionarDisciplinaAoCurso(curso1, disc3);
        secretaria.adicionarDisciplinaAoCurso(curso2, disc2);
        
        pausarComMensagem("Disciplinas podem ser obrigatórias ou optativas, com diferentes créditos");
        
        System.out.println("\n👨‍🎓 === SEÇÃO 5: CADASTRO DE ALUNOS ===");
        pausarComMensagem("Agora vamos cadastrar alunos no sistema");
        
        Aluno aluno1 = new Aluno("ALU001", "Pedro Oliveira", "pedro.oliveira@universidade.edu", 
                                "senha789", "2024001", curso1);
        Aluno aluno2 = new Aluno("ALU002", "Ana Costa", "ana.costa@universidade.edu", 
                                "senha012", "2024002", curso1);
        Aluno aluno3 = new Aluno("ALU003", "Carlos Lima", "carlos.lima@universidade.edu", 
                                "senha345", "2024003", curso2);
        
        secretaria.cadastrarAluno(aluno1);
        secretaria.cadastrarAluno(aluno2);
        secretaria.cadastrarAluno(aluno3);
        
        System.out.println("✅ Aluno 1: " + aluno1.getNome() + " - Curso: " + aluno1.getCurso().getNome());
        System.out.println("✅ Aluno 2: " + aluno2.getNome() + " - Curso: " + aluno2.getCurso().getNome());
        System.out.println("✅ Aluno 3: " + aluno3.getNome() + " - Curso: " + aluno3.getCurso().getNome());
        
        pausarComMensagem("Cada aluno pertence a um curso específico");
        
        System.out.println("\n⏰ === SEÇÃO 6: PERÍODO DE MATRÍCULA ===");
        pausarComMensagem("Vamos criar e ativar o período de matrícula");
        
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusDays(30);
        secretaria.criarPeriodoMatricula("PER001", "Matrícula 2024.1", inicio, fim, "2024.1");
        
        PeriodoMatricula periodo = sistema.getPeriodosMatricula().get(0);
        secretaria.iniciarPeriodoMatricula(periodo);
        
        System.out.println("📅 Período criado: " + periodo.getNome());
        System.out.println("⏰ Início: " + periodo.getDataInicio());
        System.out.println("⏰ Fim: " + periodo.getDataFim());
        System.out.println("🟢 Status: " + (periodo.isAtivo() ? "ATIVO" : "INATIVO"));
        
        pausarComMensagem("O sistema controla períodos específicos para matrículas");
        
        System.out.println("\n🔄 === SEÇÃO 7: ATIVAÇÃO DE DISCIPLINAS ===");
        pausarComMensagem("Vamos gerar o currículo do semestre e ativar disciplinas");
        
        secretaria.gerarCurriculoSemestre("2024.1");
        
        System.out.println("📋 Currículo do semestre 2024.1 gerado");
        System.out.println("🔄 Disciplinas ativadas para o semestre");
        
        pausarComMensagem("Disciplinas são ativadas quando o currículo é gerado");
        
        System.out.println("\n📝 === SEÇÃO 8: PROCESSO DE MATRÍCULA ===");
        pausarComMensagem("Agora vamos realizar matrículas e testar as regras de negócio");
        
        System.out.println("\n🎯 Testando matrículas obrigatórias:");
        boolean mat1 = secretaria.matricularAluno(aluno1, disc1, true);
        boolean mat2 = secretaria.matricularAluno(aluno1, disc2, true);
        boolean mat3 = secretaria.matricularAluno(aluno2, disc1, true);
        
        System.out.println("✅ Matrícula 1 (Pedro em POO): " + (mat1 ? "SUCESSO" : "FALHA"));
        System.out.println("✅ Matrícula 2 (Pedro em BD): " + (mat2 ? "SUCESSO" : "FALHA"));
        System.out.println("✅ Matrícula 3 (Ana em POO): " + (mat3 ? "SUCESSO" : "FALHA"));
        
        pausarComMensagem("Matrículas obrigatórias são registradas no sistema");
        
        System.out.println("\n🎯 Testando matrícula optativa:");
        boolean mat4 = secretaria.matricularAluno(aluno2, disc3, false);
        System.out.println("✅ Matrícula 4 (Ana em IA - optativa): " + (mat4 ? "SUCESSO" : "FALHA"));
        
        pausarComMensagem("Alunos podem se matricular em disciplinas optativas");
        
        System.out.println("\n🔍 === SEÇÃO 9: VERIFICAÇÃO DE DISCIPLINAS ATIVAS ===");
        pausarComMensagem("Vamos verificar quais disciplinas ficaram ativas");
        
        secretaria.verificarDisciplinasAtivas();
        
        System.out.println("📊 Verificação concluída - disciplinas com 3+ alunos ficam ativas");
        
        pausarComMensagem("Regra importante: disciplina precisa de pelo menos 3 alunos para ficar ativa");
        
        System.out.println("\n⚠️ === SEÇÃO 10: TESTE DE LIMITES DE MATRÍCULA ===");
        pausarComMensagem("Vamos testar os limites de matrícula obrigatória (máximo 4)");
        
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
        
        System.out.println("📚 Disciplinas adicionais criadas para teste de limite");
        
        System.out.println("\n🎯 Testando limite de 4 matrículas obrigatórias para Pedro:");
        boolean mat5 = secretaria.matricularAluno(aluno1, disc4, true);
        boolean mat6 = secretaria.matricularAluno(aluno1, disc5, true);
        boolean mat7 = secretaria.matricularAluno(aluno1, disc6, true); // Deve falhar
        
        System.out.println("✅ Matrícula 5 (Pedro em ED): " + (mat5 ? "SUCESSO" : "FALHA"));
        System.out.println("✅ Matrícula 6 (Pedro em ALG): " + (mat6 ? "SUCESSO" : "FALHA"));
        System.out.println("❌ Matrícula 7 (Pedro em SO - 5ª obrigatória): " + (mat7 ? "SUCESSO" : "FALHA - LIMITE EXCEDIDO"));
        System.out.println("📊 Matrículas obrigatórias de Pedro: " + aluno1.getQuantidadeMatriculasObrigatorias() + "/4");
        
        pausarComMensagem("O sistema impede matrícula em mais de 4 disciplinas obrigatórias!");
        
        System.out.println("\n🎯 Testando limite de 2 matrículas optativas para Ana:");
        
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
        
        boolean mat8 = secretaria.matricularAluno(aluno2, disc7, false);
        boolean mat9 = secretaria.matricularAluno(aluno2, disc8, false);
        boolean mat10 = secretaria.matricularAluno(aluno2, disc9, false);
        
        System.out.println("✅ Matrícula 8 (Ana em RED): " + (mat8 ? "SUCESSO" : "FALHA"));
        System.out.println("✅ Matrícula 9 (Ana em SEG): " + (mat9 ? "SUCESSO" : "FALHA"));
        System.out.println("❌ Matrícula 10 (Ana em ES - 3ª optativa): " + (mat10 ? "SUCESSO" : "FALHA - LIMITE EXCEDIDO"));
        System.out.println("📊 Matrículas optativas de Ana: " + aluno2.getQuantidadeMatriculasOptativas() + "/2");
        
        pausarComMensagem("O sistema também controla o limite de 2 disciplinas optativas!");
        
        System.out.println("\n📊 === SEÇÃO 11: GERAÇÃO DE RELATÓRIOS ===");
        pausarComMensagem("Vamos gerar relatórios do sistema");
        
        secretaria.gerarRelatorioGeral();
        secretaria.gerarRelatorioMatriculas("2024.1");
        secretaria.gerarRelatorioDisciplinas("2024.1");
        
        pausarComMensagem("O sistema gera relatórios úteis para gestão");
        
        System.out.println("\n💾 === SEÇÃO 12: PERSISTÊNCIA DE DADOS ===");
        pausarComMensagem("Agora vamos demonstrar a persistência em arquivos");
        
        System.out.println("💾 Salvando dados do sistema...");
        secretaria.salvarDados();
        
        System.out.println("🗑️ Limpando dados da memória...");
        limparDadosMemoria(sistema);
        
        System.out.println("📂 Dados salvos em arquivos na pasta 'dados/'");
        System.out.println("🧠 Memória limpa - sistema vazio");
        
        pausarComMensagem("Dados foram salvos em arquivos de texto");
        
        System.out.println("\n🔄 Recarregando dados dos arquivos...");
        secretaria.carregarDados();
        
        System.out.println("✅ Dados recarregados com sucesso!");
        System.out.println("📊 Total de alunos: " + sistema.getAlunos().size());
        System.out.println("📊 Total de professores: " + sistema.getProfessores().size());
        System.out.println("📊 Total de cursos: " + sistema.getCursos().size());
        System.out.println("📊 Total de disciplinas: " + sistema.getDisciplinas().size());
        System.out.println("📊 Total de matrículas: " + sistema.getMatriculas().size());
        
        pausarComMensagem("Todos os dados foram recuperados dos arquivos!");
        
        System.out.println("\n🔍 === SEÇÃO 13: TESTANDO FUNCIONALIDADES APÓS CARREGAMENTO ===");
        pausarComMensagem("Vamos testar se tudo funciona após recarregar os dados");
        
        boolean loginSucesso = sistema.realizarLogin("pedro.oliveira@universidade.edu", "senha789", TipoUsuario.ALUNO);
        System.out.println("🔐 Login do aluno Pedro: " + (loginSucesso ? "SUCESSO" : "FALHA"));
        
        if (sistema.getDisciplinas().size() > 0) {
            Disciplina disciplina = sistema.getDisciplinas().get(0);
            List<Aluno> alunosMatriculados = secretaria.getAlunosMatriculados(disciplina);
            System.out.println("👥 Alunos matriculados em " + disciplina.getNome() + ": " + alunosMatriculados.size());
        }
        
        if (sistema.getProfessores().size() > 0 && sistema.getDisciplinas().size() > 0) {
            Professor professor = sistema.getProfessores().get(0);
            Disciplina disciplina = sistema.getDisciplinas().get(0);
            List<Aluno> alunosProfessor = professor.getAlunosMatriculados(disciplina);
            System.out.println("👨‍🏫 Professor " + professor.getNome() + " tem " + alunosProfessor.size() + " alunos em " + disciplina.getNome());
        }
        
        pausarComMensagem("Todas as funcionalidades continuam funcionando após recarregar!");
        
        System.out.println("\n🎉 === DEMONSTRAÇÃO CONCLUÍDA COM SUCESSO! ===");
        pausarComMensagem("Sistema completo demonstrado com todas as funcionalidades!");
        
        System.out.println("\n📋 === RESUMO DO QUE FOI DEMONSTRADO ===");
        System.out.println("✅ Arquitetura orientada a objetos");
        System.out.println("✅ Padrão Singleton para o sistema");
        System.out.println("✅ Herança com classe Usuario abstrata");
        System.out.println("✅ Persistência completa em arquivos");
        System.out.println("✅ Regras de negócio complexas");
        System.out.println("✅ Validações e controles de limite");
        System.out.println("✅ Geração de relatórios");
        System.out.println("✅ Separação de responsabilidades");
        
        System.out.println("\n🎯 === CONCEITOS TÉCNICOS DEMONSTRADOS ===");
        System.out.println("🔹 Classes abstratas e herança");
        System.out.println("🔹 Collections (List, ArrayList)");
        System.out.println("🔹 Manipulação de arquivos");
        System.out.println("🔹 Validação de dados");
        System.out.println("🔹 Controle de fluxo complexo");
        System.out.println("🔹 Padrões de design");
        
        System.out.println("\n💡 === POSSÍVEIS MELHORIAS ===");
        System.out.println("🔸 Interface gráfica (Swing/JavaFX)");
        System.out.println("🔸 Banco de dados (MySQL/PostgreSQL)");
        System.out.println("🔸 API REST para sistema web");
        System.out.println("🔸 Criptografia de senhas");
        System.out.println("🔸 Geração de relatórios em PDF");
        
        System.out.println("\n🎓 OBRIGADO PELA ATENÇÃO! 🎓");
    }
    
    private static void limparDadosMemoria(SistemaMatriculas sistema) {
        sistema.getAlunos().clear();
        sistema.getProfessores().clear();
        sistema.getCursos().clear();
        sistema.getDisciplinas().clear();
        sistema.getMatriculas().clear();
        sistema.getPeriodosMatricula().clear();
    }
    
    private static void pausarComMensagem(String mensagem) {
        System.out.println("\n💬 " + mensagem);
        System.out.print("Pressione Enter para continuar...");
        scanner.nextLine();
    }
}
