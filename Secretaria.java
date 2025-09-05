import java.util.*;
import java.time.LocalDateTime;

public class Secretaria extends Usuario {
    private SistemaMatriculas sistema;

    public Secretaria(String id, String nome, String email, String senha, SistemaMatriculas sistema) {
        super(id, nome, email, senha, TipoUsuario.SECRETARIA);
        this.sistema = sistema;
    }
    
    public void gerarCurriculoSemestre(String semestre) {
        System.out.println("Secretaria " + nome + " gerando currículo para o semestre: " + semestre);
        
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (semestre.equals(disciplina.getSemestre())) {
                disciplina.setAtiva(true);
                System.out.println("Disciplina " + disciplina.getNome() + " ativada para o semestre " + semestre);
            }
        }
        
        verificarDisciplinasAtivas();
    }

    public void definirDisciplinasSemestre(String semestre, List<Disciplina> disciplinas) {
        System.out.println("Secretaria " + nome + " definindo disciplinas para o semestre: " + semestre);
        for (Disciplina disciplina : disciplinas) {
            disciplina.setSemestre(semestre);
            if (!sistema.getDisciplinas().contains(disciplina)) {
                sistema.getDisciplinas().add(disciplina);
            }
        }
    }

    public List<Disciplina> getDisciplinasPorSemestre(String semestre) {
        System.out.println("Secretaria " + nome + " obtendo disciplinas do semestre: " + semestre);
        List<Disciplina> disciplinasSemestre = new ArrayList<>();
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (semestre.equals(disciplina.getSemestre())) {
                disciplinasSemestre.add(disciplina);
            }
        }
        return disciplinasSemestre;
    }

    
    public Disciplina criarDisciplina(String id, String nome, String codigo, int creditos, 
                                     Professor professor, Curso curso, boolean obrigatoria, String semestre) {
        System.out.println("Secretaria " + nome + " criando disciplina: " + nome);
        Disciplina novaDisciplina = new Disciplina(id, nome, codigo, creditos, professor, curso, obrigatoria, semestre);
        sistema.getDisciplinas().add(novaDisciplina);
        return novaDisciplina;
    }

    public void ativarDisciplina(Disciplina disciplina) {
        System.out.println("Secretaria " + nome + " ativando disciplina: " + disciplina.getNome());
        disciplina.setAtiva(true);
    }

    public void desativarDisciplina(Disciplina disciplina) {
        System.out.println("Secretaria " + nome + " desativando disciplina: " + disciplina.getNome());
        disciplina.setAtiva(false);
    }

    public void alterarVagasDisciplina(Disciplina disciplina, int novasVagas) {
        System.out.println("Secretaria " + nome + " alterando vagas da disciplina " + disciplina.getNome() + " para: " + novasVagas);
        disciplina.setVagasTotais(novasVagas);
        disciplina.setVagasDisponiveis(novasVagas - disciplina.getQuantidadeAlunosMatriculados());
    }

    public void atribuirProfessorDisciplina(Disciplina disciplina, Professor professor) {
        System.out.println("Secretaria " + nome + " atribuindo professor " + professor.getNome() + " à disciplina " + disciplina.getNome());
        disciplina.setProfessor(professor);
        professor.adicionarDisciplina(disciplina);
    }

    public void verificarDisciplinasAtivas() {
        System.out.println("Secretaria " + nome + " verificando disciplinas ativas...");
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            boolean podeSerAtivada = disciplina.verificarSePodeSerAtivada();
            System.out.println("Disciplina " + disciplina.getNome() + " pode ser ativada: " + podeSerAtivada);
        }
    }
    
    public void cadastrarProfessor(Professor professor) {
        System.out.println("Secretaria " + nome + " cadastrando professor: " + professor.getNome());
        if (!sistema.getProfessores().contains(professor)) {
            sistema.getProfessores().add(professor);
        }
    }

    public void removerProfessor(Professor professor) {
        System.out.println("Secretaria " + nome + " removendo professor: " + professor.getNome());
        sistema.getProfessores().remove(professor);
    }

    public List<Professor> getProfessoresPorArea(String areaEspecializacao) {
        System.out.println("Secretaria " + nome + " obtendo professores da área: " + areaEspecializacao);
        List<Professor> professoresArea = new ArrayList<>();
        for (Professor professor : sistema.getProfessores()) {
            if (areaEspecializacao.equals(professor.getAreaEspecializacao())) {
                professoresArea.add(professor);
            }
        }
        return professoresArea;
    }

    public void definirCoordenadorCurso(Curso curso, Professor coordenador) {
        System.out.println("Secretaria " + nome + " definindo coordenador " + coordenador.getNome() + " para o curso " + curso.getNome());
        curso.setCoordenador(coordenador);
    }
    
    public void cadastrarAluno(Aluno aluno) {
        System.out.println("Secretaria " + nome + " cadastrando aluno: " + aluno.getNome());
        if (!sistema.getAlunos().contains(aluno)) {
            sistema.getAlunos().add(aluno);
        }
    }

    public void removerAluno(Aluno aluno) {
        System.out.println("Secretaria " + nome + " removendo aluno: " + aluno.getNome());
        sistema.getAlunos().remove(aluno);
    }

    public List<Aluno> getAlunosPorCurso(Curso curso) {
        System.out.println("Secretaria " + nome + " obtendo alunos do curso: " + curso.getNome());
        List<Aluno> alunosCurso = new ArrayList<>();
        for (Aluno aluno : sistema.getAlunos()) {
            if (curso.equals(aluno.getCurso())) {
                alunosCurso.add(aluno);
            }
        }
        return alunosCurso;
    }

    public void alterarCursoAluno(Aluno aluno, Curso novoCurso) {
        System.out.println("Secretaria " + nome + " alterando curso do aluno " + aluno.getNome() + " para: " + novoCurso.getNome());
        aluno.setCurso(novoCurso);
    }

    public List<Aluno> getAlunosMatriculados(Disciplina disciplina) {
        System.out.println("Secretaria " + nome + " obtendo alunos matriculados na disciplina: " + disciplina.getNome());
        return disciplina.getAlunosMatriculados();
    }
    
    public void criarPeriodoMatricula(String id, String nome, LocalDateTime dataInicio, 
                                     LocalDateTime dataFim, String semestre) {
        System.out.println("Secretaria " + nome + " criando período de matrícula: " + nome);
        PeriodoMatricula novoPeriodo = new PeriodoMatricula(id, nome, dataInicio, dataFim, semestre);
        sistema.getPeriodosMatricula().add(novoPeriodo);
    }

    public void iniciarPeriodoMatricula(PeriodoMatricula periodo) {
        System.out.println("Secretaria " + nome + " iniciando período de matrícula: " + periodo.getNome());
        periodo.iniciarPeriodo();
    }

    public void encerrarPeriodoMatricula(PeriodoMatricula periodo) {
        System.out.println("Secretaria " + nome + " encerrando período de matrícula: " + periodo.getNome());
        periodo.encerrarPeriodo();
    }

    public List<PeriodoMatricula> getPeriodosAtivos() {
        System.out.println("Secretaria " + nome + " obtendo períodos de matrícula ativos");
        List<PeriodoMatricula> periodosAtivos = new ArrayList<>();
        for (PeriodoMatricula periodo : sistema.getPeriodosMatricula()) {
            if (periodo.isAtivo()) {
                periodosAtivos.add(periodo);
            }
        }
        return periodosAtivos;
    }
    
    public void cadastrarCurso(Curso curso) {
        System.out.println("Secretaria " + nome + " cadastrando curso: " + curso.getNome());
        if (!sistema.getCursos().contains(curso)) {
            sistema.getCursos().add(curso);
        }
    }

    public void removerCurso(Curso curso) {
        System.out.println("Secretaria " + nome + " removendo curso: " + curso.getNome());
        sistema.getCursos().remove(curso);
    }

    public void adicionarDisciplinaAoCurso(Curso curso, Disciplina disciplina) {
        System.out.println("Secretaria " + nome + " adicionando disciplina " + disciplina.getNome() + " ao curso " + curso.getNome());
        curso.adicionarDisciplina(disciplina);
    }

    public void removerDisciplinaDoCurso(Curso curso, Disciplina disciplina) {
        System.out.println("Secretaria " + nome + " removendo disciplina " + disciplina.getNome() + " do curso " + curso.getNome());
        curso.removerDisciplina(disciplina);
    }
    
    public boolean matricularAluno(Aluno aluno, Disciplina disciplina, boolean obrigatoria) {
        System.out.println("Secretaria " + nome + " matriculando aluno " + aluno.getNome() + " na disciplina " + disciplina.getNome());
        
        boolean periodoAtivo = false;
        for (PeriodoMatricula periodo : sistema.getPeriodosMatricula()) {
            if (periodo.isAtivo()) {
                periodoAtivo = true;
                break;
            }
        }
        
        if (!periodoAtivo) {
            System.out.println("Não há período de matrícula ativo");
            return false;
        }
        
        if (!aluno.podeMatricular(disciplina)) {
            System.out.println("Aluno não pode se matricular nesta disciplina");
            return false;
        }
        
        if (disciplina.isLotada()) {
            System.out.println("Disciplina está lotada");
            return false;
        }
        
        String matriculaId = "MAT" + System.currentTimeMillis();
        String semestre = disciplina.getSemestre();
        Matricula matricula = new Matricula(matriculaId, aluno, disciplina, obrigatoria, semestre);
        
        sistema.getMatriculas().add(matricula);
        
        boolean sucesso = disciplina.matricularAluno(matricula);
        if (sucesso) {
            aluno.adicionarMatricula(matricula);
            System.out.println("Matrícula realizada com sucesso");
            
            List<Disciplina> disciplinasAluno = new ArrayList<>();
            disciplinasAluno.add(disciplina);
            sistema.notificarSistemaCobranca(aluno, disciplinasAluno);
        }
        
        return sucesso;
    }

    public boolean cancelarMatricula(Aluno aluno, Disciplina disciplina) {
        System.out.println("Secretaria " + nome + " cancelando matrícula do aluno " + aluno.getNome() + " na disciplina " + disciplina.getNome());
        
        Matricula matriculaParaRemover = null;
        for (Matricula matricula : sistema.getMatriculas()) {
            if (matricula.getAluno().equals(aluno) && matricula.getDisciplina().equals(disciplina)) {
                matriculaParaRemover = matricula;
                break;
            }
        }
        
        if (matriculaParaRemover == null) {
            System.out.println("Matrícula não encontrada");
            return false;
        }
        
        boolean sucesso = disciplina.cancelarMatricula(matriculaParaRemover);
        if (sucesso) {
            aluno.removerMatricula(matriculaParaRemover);
            
            sistema.getMatriculas().remove(matriculaParaRemover);
            
            System.out.println("Matrícula cancelada com sucesso");
        }
        
        return sucesso;
    }
    
    public void gerarRelatorioMatriculas(String semestre) {
        System.out.println("Secretaria " + nome + " gerando relatório de matrículas para o semestre: " + semestre);
        int totalMatriculas = 0;
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (semestre.equals(disciplina.getSemestre())) {
                totalMatriculas += disciplina.getQuantidadeAlunosMatriculados();
                System.out.println("Disciplina " + disciplina.getNome() + ": " + 
                                 disciplina.getQuantidadeAlunosMatriculados() + " alunos matriculados");
            }
        }
        System.out.println("Total de matrículas no semestre " + semestre + ": " + totalMatriculas);
    }

    public void gerarRelatorioDisciplinas(String semestre) {
        System.out.println("Secretaria " + nome + " gerando relatório de disciplinas para o semestre: " + semestre);
        List<Disciplina> disciplinasSemestre = getDisciplinasPorSemestre(semestre);
        System.out.println("Total de disciplinas no semestre " + semestre + ": " + disciplinasSemestre.size());
        
        int disciplinasAtivas = 0;
        int disciplinasInativas = 0;
        for (Disciplina disciplina : disciplinasSemestre) {
            if (disciplina.isAtiva()) {
                disciplinasAtivas++;
            } else {
                disciplinasInativas++;
            }
        }
        System.out.println("Disciplinas ativas: " + disciplinasAtivas);
        System.out.println("Disciplinas inativas: " + disciplinasInativas);
    }

    public void gerarRelatorioGeral() {
        System.out.println("Secretaria " + nome + " gerando relatório geral do sistema");
        System.out.println("Total de alunos: " + sistema.getAlunos().size());
        System.out.println("Total de professores: " + sistema.getProfessores().size());
        System.out.println("Total de cursos: " + sistema.getCursos().size());
        System.out.println("Total de disciplinas: " + sistema.getDisciplinas().size());
        System.out.println("Total de matrículas: " + sistema.getMatriculas().size());
        System.out.println("Total de períodos de matrícula: " + sistema.getPeriodosMatricula().size());
    }
    
    public void salvarDados() {
        System.out.println("Secretaria " + nome + " salvando dados do sistema");
        sistema.salvarDados();
    }

    public void carregarDados() {
        System.out.println("Secretaria " + nome + " carregando dados do sistema");
        sistema.carregarDados();
    }
    
    public SistemaMatriculas getSistema() {
        return sistema;
    }
}
