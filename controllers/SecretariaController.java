package controllers;

import models.*;
import views.*;
import java.time.LocalDateTime;

/**
 * Controller responsável pelas operações da secretaria
 */
public class SecretariaController extends BaseController {
    private Secretaria secretaria;
    private SecretariaView secretariaView;
    
    public SecretariaController(Secretaria secretaria) {
        super();
        this.secretaria = secretaria;
        this.secretariaView = new SecretariaView();
    }
    
    /**
     * Menu principal da secretaria
     */
    public void menuSecretaria() {
        while (true) {
            secretariaView.exibirMenuPrincipal(secretaria);
            int opcao = secretariaView.lerOpcao();
            
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
                case 13: gerarRelatorioGeral(); break;
                case 14: gerarRelatorioMatriculas(); break;
                case 15: gerarRelatorioDisciplinas(); break;
                case 16: salvarDados(); break;
                case 17: carregarDados(); break;
                case 0: return;
                default: secretariaView.exibirOpcaoInvalida();
            }
        }
    }
    
    /**
     * Cadastra um novo curso
     */
    private void cadastrarCurso() {
        secretariaView.exibirTitulo("CADASTRAR CURSO");
        
        String id = secretariaView.lerId("do Curso");
        String nome = secretariaView.lerNome("do Curso");
        int creditos = secretariaView.lerCreditos();
        
        // Listar professores para escolher coordenador
        secretariaView.exibirProfessoresDisponiveis(sistema.getProfessores());
        int indice = secretariaView.lerIndice("coordenador");
        
        if (indice >= 0 && indice < sistema.getProfessores().size()) {
            Professor coordenador = sistema.getProfessores().get(indice);
            Curso novoCurso = new Curso(id, nome, creditos, coordenador);
            secretaria.cadastrarCurso(novoCurso);
            secretaria.definirCoordenadorCurso(novoCurso, coordenador);
            exibirSucesso("Curso cadastrado com sucesso!");
        } else {
            exibirErro("Professor inválido!");
        }
    }
    
    /**
     * Cadastra uma nova disciplina
     */
    private void cadastrarDisciplina() {
        secretariaView.exibirTitulo("CADASTRAR DISCIPLINA");
        
        String id = secretariaView.lerId("da Disciplina");
        String nome = secretariaView.lerNome("da Disciplina");
        String codigo = secretariaView.lerCodigo();
        int creditos = secretariaView.lerCreditos();
        boolean obrigatoria = secretariaView.lerObrigatoria();
        String semestre = secretariaView.lerSemestre();
        
        // Escolher professor
        secretariaView.exibirProfessoresDisponiveis(sistema.getProfessores());
        int indiceProf = secretariaView.lerIndice("professor");
        
        // Escolher curso
        secretariaView.exibirCursosDisponiveis(sistema.getCursos());
        int indiceCurso = secretariaView.lerIndice("curso");
        
        if (indiceProf >= 0 && indiceProf < sistema.getProfessores().size() &&
            indiceCurso >= 0 && indiceCurso < sistema.getCursos().size()) {
            
            Professor professor = sistema.getProfessores().get(indiceProf);
            Curso curso = sistema.getCursos().get(indiceCurso);
            
            Disciplina novaDisciplina = secretaria.criarDisciplina(id, nome, codigo, creditos, 
                                                                  professor, curso, obrigatoria, semestre);
            secretaria.adicionarDisciplinaAoCurso(curso, novaDisciplina);
            exibirSucesso("Disciplina cadastrada com sucesso!");
        } else {
            exibirErro("Professor ou curso inválido!");
        }
    }
    
    /**
     * Cadastra um novo professor
     */
    private void cadastrarProfessor() {
        secretariaView.exibirTitulo("CADASTRAR PROFESSOR");
        
        String id = secretariaView.lerId("do Professor");
        String nome = secretariaView.lerNome("do Professor");
        String email = secretariaView.lerEmail();
        String senha = secretariaView.lerSenha();
        String siape = secretariaView.lerSiape();
        String area = secretariaView.lerAreaEspecializacao();
        
        Professor novoProfessor = new Professor(id, nome, email, senha, siape, area);
        secretaria.cadastrarProfessor(novoProfessor);
        exibirSucesso("Professor cadastrado com sucesso!");
    }
    
    /**
     * Cadastra um novo aluno
     */
    private void cadastrarAluno() {
        secretariaView.exibirTitulo("CADASTRAR ALUNO");
        
        String id = secretariaView.lerId("do Aluno");
        String nome = secretariaView.lerNome("do Aluno");
        String email = secretariaView.lerEmail();
        String senha = secretariaView.lerSenha();
        String matricula = secretariaView.lerMatricula();
        
        // Escolher curso
        secretariaView.exibirCursosDisponiveis(sistema.getCursos());
        int indice = secretariaView.lerIndice("curso");
        
        if (indice >= 0 && indice < sistema.getCursos().size()) {
            Curso curso = sistema.getCursos().get(indice);
            Aluno novoAluno = new Aluno(id, nome, email, senha, matricula, curso);
            secretaria.cadastrarAluno(novoAluno);
            exibirSucesso("Aluno cadastrado com sucesso!");
        } else {
            exibirErro("Curso inválido!");
        }
    }
    
    /**
     * Lista todos os cursos
     */
    private void listarCursos() {
        secretariaView.exibirCursos(sistema.getCursos());
    }
    
    /**
     * Lista todas as disciplinas
     */
    private void listarDisciplinas() {
        secretariaView.exibirDisciplinas(sistema.getDisciplinas());
    }
    
    /**
     * Lista todos os professores
     */
    private void listarProfessores() {
        secretariaView.exibirProfessores(sistema.getProfessores());
    }
    
    /**
     * Lista todos os alunos
     */
    private void listarAlunos() {
        secretariaView.exibirAlunos(sistema.getAlunos());
    }
    
    /**
     * Cria um novo período de matrícula
     */
    private void criarPeriodoMatricula() {
        secretariaView.exibirTitulo("CRIAR PERÍODO DE MATRÍCULA");
        
        String id = secretariaView.lerId("do Período");
        String nome = secretariaView.lerNome("do Período");
        String semestre = secretariaView.lerSemestre();
        int duracao = secretariaView.lerDuracao();
        
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = inicio.plusDays(duracao);
        
        secretaria.criarPeriodoMatricula(id, nome, inicio, fim, semestre);
        secretaria.iniciarPeriodoMatricula(sistema.getPeriodosMatricula().get(sistema.getPeriodosMatricula().size() - 1));
        exibirSucesso("Período de matrícula criado e ativado!");
    }
    
    /**
     * Matricula um aluno em uma disciplina
     */
    private void matricularAluno() {
        secretariaView.exibirTitulo("MATRICULAR ALUNO");
        
        // Escolher aluno
        secretariaView.exibirAlunosDisponiveis(sistema.getAlunos());
        int indiceAluno = secretariaView.lerIndice("aluno");
        
        // Escolher disciplina
        secretariaView.exibirDisciplinasDisponiveis(sistema.getDisciplinas());
        int indiceDisc = secretariaView.lerIndice("disciplina");
        
        boolean obrigatoria = secretariaView.lerObrigatoria();
        
        if (indiceAluno >= 0 && indiceAluno < sistema.getAlunos().size() &&
            indiceDisc >= 0 && indiceDisc < sistema.getDisciplinas().size()) {
            
            Aluno aluno = sistema.getAlunos().get(indiceAluno);
            Disciplina disciplina = sistema.getDisciplinas().get(indiceDisc);
            
            boolean sucesso = secretaria.matricularAluno(aluno, disciplina, obrigatoria);
            if (sucesso) {
                exibirSucesso("Matrícula realizada com sucesso!");
            } else {
                exibirErro("Falha na matrícula. Verifique as regras de negócio.");
            }
        } else {
            exibirErro("Aluno ou disciplina inválido!");
        }
    }
    
    /**
     * Cancela uma matrícula
     */
    private void cancelarMatricula() {
        secretariaView.exibirTitulo("CANCELAR MATRÍCULA");
        
        // Escolher aluno
        secretariaView.exibirAlunosDisponiveis(sistema.getAlunos());
        int indiceAluno = secretariaView.lerIndice("aluno");
        
        if (indiceAluno >= 0 && indiceAluno < sistema.getAlunos().size()) {
            Aluno aluno = sistema.getAlunos().get(indiceAluno);
            
            // Escolher matrícula para cancelar
            secretariaView.exibirMatriculasAluno(aluno);
            int indiceMat = secretariaView.lerIndice("matrícula para cancelar");
            
            if (indiceMat >= 0 && indiceMat < aluno.getMatriculas().size()) {
                Matricula matricula = aluno.getMatriculas().get(indiceMat);
                boolean sucesso = secretaria.cancelarMatricula(aluno, matricula.getDisciplina());
                if (sucesso) {
                    exibirSucesso("Matrícula cancelada com sucesso!");
                } else {
                    exibirErro("Falha ao cancelar matrícula.");
                }
            } else {
                exibirErro("Matrícula inválida!");
            }
        } else {
            exibirErro("Aluno inválido!");
        }
    }
    
    /**
     * Gerencia disciplinas (ativar/desativar)
     */
    private void gerenciarDisciplinas() {
        secretariaView.exibirTitulo("GERENCIAR DISCIPLINAS");
        secretariaView.exibirOpcoesGerenciamento();
        int opcao = secretariaView.lerOpcao();
        
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
                exibirErro("Opção inválida!");
        }
    }
    
    /**
     * Ativa uma disciplina
     */
    private void ativarDisciplina() {
        secretariaView.exibirDisciplinasDisponiveis(sistema.getDisciplinas());
        int indice = secretariaView.lerIndice("disciplina para ativar");
        
        if (indice >= 0 && indice < sistema.getDisciplinas().size()) {
            secretaria.ativarDisciplina(sistema.getDisciplinas().get(indice));
            exibirSucesso("Disciplina ativada!");
        } else {
            exibirErro("Disciplina inválida!");
        }
    }
    
    /**
     * Desativa uma disciplina
     */
    private void desativarDisciplina() {
        secretariaView.exibirDisciplinasDisponiveis(sistema.getDisciplinas());
        int indice = secretariaView.lerIndice("disciplina para desativar");
        
        if (indice >= 0 && indice < sistema.getDisciplinas().size()) {
            secretaria.desativarDisciplina(sistema.getDisciplinas().get(indice));
            exibirSucesso("Disciplina desativada!");
        } else {
            exibirErro("Disciplina inválida!");
        }
    }
    
    /**
     * Gera relatório geral
     */
    private void gerarRelatorioGeral() {
        secretaria.gerarRelatorioGeral();
    }
    
    /**
     * Gera relatório de matrículas
     */
    private void gerarRelatorioMatriculas() {
        String semestre = secretariaView.lerSemestre();
        secretaria.gerarRelatorioMatriculas(semestre);
    }
    
    /**
     * Gera relatório de disciplinas
     */
    private void gerarRelatorioDisciplinas() {
        String semestre = secretariaView.lerSemestre();
        secretaria.gerarRelatorioDisciplinas(semestre);
    }
    
    /**
     * Salva dados do sistema
     */
    private void salvarDados() {
        secretaria.salvarDados();
        exibirSucesso("Dados salvos com sucesso!");
    }
    
    /**
     * Carrega dados do sistema
     */
    private void carregarDados() {
        secretaria.carregarDados();
        exibirSucesso("Dados carregados com sucesso!");
    }
}
