import java.util.*;

public class Aluno extends Usuario {
    private String matricula;
    private Curso curso;
    private List<Matricula> matriculas;
    private int creditosCursados;

    public Aluno(String id, String nome, String email, String senha, String matricula, Curso curso) {
        super(id, nome, email, senha, TipoUsuario.ALUNO);
        this.matricula = matricula;
        this.curso = curso;
        this.matriculas = new ArrayList<>();
        this.creditosCursados = 0;
    }

    public boolean podeMatricular(Disciplina disciplina) {
        System.out.println("Verificando se aluno " + nome + " pode se matricular em " + disciplina.getNome());
        return true;
    }

    public int getQuantidadeMatriculasObrigatorias() {
        System.out.println("Contando matrículas obrigatórias do aluno: " + nome);
        return 0;
    }

    public int getQuantidadeMatriculasOptativas() {
        System.out.println("Contando matrículas optativas do aluno: " + nome);
        return 0;
    }

    public void adicionarMatricula(Matricula matricula) {
        System.out.println("Adicionando matrícula para aluno: " + nome);
        this.matriculas.add(matricula);
    }

    public void removerMatricula(Matricula matricula) {
        System.out.println("Removendo matrícula do aluno: " + nome);
        this.matriculas.remove(matricula);
    }

    public List<Disciplina> getDisciplinasMatriculadas() {
        System.out.println("Obtendo disciplinas matriculadas do aluno: " + nome);
        List<Disciplina> disciplinas = new ArrayList<>();
        for (Matricula mat : matriculas) {
            disciplinas.add(mat.getDisciplina());
        }
        return disciplinas;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public int getCreditosCursados() {
        return creditosCursados;
    }

    public void setCreditosCursados(int creditosCursados) {
        this.creditosCursados = creditosCursados;
    }
}
