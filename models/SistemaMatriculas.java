package models;

import java.util.*;

public class SistemaMatriculas {
    private static SistemaMatriculas instance;
    private List<Curso> cursos;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<Aluno> alunos;
    private List<Secretaria> secretarias;
    private List<Matricula> matriculas;
    private List<PeriodoMatricula> periodosMatricula;
    private GerenciadorArquivos gerenciadorArquivos;

    private SistemaMatriculas() {
        this.cursos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.secretarias = new ArrayList<>();
        this.matriculas = new ArrayList<>();
        this.periodosMatricula = new ArrayList<>();
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
        
        if (tipo == TipoUsuario.ALUNO) {
            for (Aluno aluno : alunos) {
                if (aluno.getEmail().equals(usuario) && aluno.autenticar(senha)) {
                    System.out.println("Login realizado com sucesso para aluno: " + aluno.getNome());
                    return true;
                }
            }
        } else if (tipo == TipoUsuario.PROFESSOR) {
            for (Professor professor : professores) {
                if (professor.getEmail().equals(usuario) && professor.autenticar(senha)) {
                    System.out.println("Login realizado com sucesso para professor: " + professor.getNome());
                    return true;
                }
            }
        } else if (tipo == TipoUsuario.SECRETARIA) {
            for (Secretaria secretaria : secretarias) {
                if (secretaria.getEmail().equals(usuario) && secretaria.autenticar(senha)) {
                    System.out.println("Login realizado com sucesso para secretaria: " + secretaria.getNome());
                    return true;
                }
            }
        }
        
        System.out.println("Falha no login - usuário ou senha inválidos");
        return false;
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

    public List<Secretaria> getSecretarias() {
        return secretarias;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public List<PeriodoMatricula> getPeriodosMatricula() {
        return periodosMatricula;
    }


    public GerenciadorArquivos getGerenciadorArquivos() {
        return gerenciadorArquivos;
    }
}
