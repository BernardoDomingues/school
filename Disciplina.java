import java.util.*;

public class Disciplina {
    private String id;
    private String nome;
    private String codigo;
    private int creditos;
    private Professor professor;
    private Curso curso;
    private boolean obrigatoria;
    private boolean ativa;
    private int vagasDisponiveis;
    private int vagasTotais;
    private List<Aluno> alunosMatriculados;
    private String semestre;

    public Disciplina(String id, String nome, String codigo, int creditos, Professor professor, Curso curso,
            boolean obrigatoria, String semestre) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.creditos = creditos;
        this.professor = professor;
        this.curso = curso;
        this.obrigatoria = obrigatoria;
        this.semestre = semestre;
        this.ativa = true;
        this.vagasTotais = 60;
        this.vagasDisponiveis = 60;
        this.alunosMatriculados = new ArrayList<>();
    }

    public boolean matricularAluno(Aluno aluno) {
        System.out.println("Matriculando aluno " + aluno.getNome() + " na disciplina " + nome);
        if (vagasDisponiveis > 0 && ativa) {
            alunosMatriculados.add(aluno);
            vagasDisponiveis--;
            return true;
        }
        return false;
    }

    public boolean cancelarMatricula(Aluno aluno) {
        System.out.println("Cancelando matrícula do aluno " + aluno.getNome() + " na disciplina " + nome);
        if (alunosMatriculados.remove(aluno)) {
            vagasDisponiveis++;
            return true;
        }
        return false;
    }

    public boolean verificarSePodeSerAtivada() {
        System.out.println("Verificando se disciplina " + nome + " pode ser ativada");
        boolean podeSerAtivada = alunosMatriculados.size() >= 3;
        this.ativa = podeSerAtivada;
        return podeSerAtivada;
    }

    public boolean isLotada() {
        System.out.println("Verificando se disciplina " + nome + " está lotada");
        return vagasDisponiveis == 0;
    }

    public int getQuantidadeAlunosMatriculados() {
        System.out.println("Obtendo quantidade de alunos matriculados na disciplina: " + nome);
        return alunosMatriculados.size();
    }

    public List<Aluno> getAlunosMatriculados() {
        System.out.println("Obtendo lista de alunos matriculados na disciplina: " + nome);
        return new ArrayList<>(alunosMatriculados);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public int getVagasTotais() {
        return vagasTotais;
    }

    public void setVagasTotais(int vagasTotais) {
        this.vagasTotais = vagasTotais;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public void setAlunosMatriculados(List<Aluno> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }
}
