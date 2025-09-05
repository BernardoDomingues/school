import java.util.*;

public class SistemaMatriculas {
    private static SistemaMatriculas instance;
    private List<Curso> cursos;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<Aluno> alunos;
    private List<Matricula> matriculas;
    private List<PeriodoMatricula> periodosMatricula;
    private SistemaCobranca sistemaCobranca;
    private GerenciadorArquivos gerenciadorArquivos;

    private SistemaMatriculas() {
        this.cursos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.matriculas = new ArrayList<>();
        this.periodosMatricula = new ArrayList<>();
        this.sistemaCobranca = new SistemaCobranca();
        this.gerenciadorArquivos = new GerenciadorArquivos(this);
        carregarDados();
    }

    public static SistemaMatriculas getInstance() {
        if (instance == null) {
            instance = new SistemaMatriculas();
        }
        return instance;
    }

    public void carregarDados() {
        System.out.println("Carregando dados do sistema...");
        gerenciadorArquivos.carregarTodosOsDados();
    }

    public void salvarDados() {
        System.out.println("Salvando dados do sistema...");
        gerenciadorArquivos.salvarTodosOsDados();
    }

    public boolean realizarLogin(String usuario, String senha, TipoUsuario tipo) {
        System.out.println("Realizando login para usuário: " + usuario + " do tipo: " + tipo);
        return true;
    }

    public void notificarSistemaCobranca(Aluno aluno, List<Disciplina> disciplinas) {
        System.out.println("Notificando sistema de cobrança para aluno: " + aluno.getNome());
        sistemaCobranca.gerarCobranca(aluno, disciplinas);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public List<PeriodoMatricula> getPeriodosMatricula() {
        return periodosMatricula;
    }

    public SistemaCobranca getSistemaCobranca() {
        return sistemaCobranca;
    }

    public GerenciadorArquivos getGerenciadorArquivos() {
        return gerenciadorArquivos;
    }
}
