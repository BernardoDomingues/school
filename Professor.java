import java.util.*;

public class Professor extends Usuario {
    private String siape;
    private List<Disciplina> disciplinasMinistradas;
    private String areaEspecializacao;

    public Professor(String id, String nome, String email, String senha, String siape, String areaEspecializacao) {
        super(id, nome, email, senha, TipoUsuario.PROFESSOR);
        this.siape = siape;
        this.areaEspecializacao = areaEspecializacao;
        this.disciplinasMinistradas = new ArrayList<>();
    }

    public List<Aluno> getAlunosMatriculados(Disciplina disciplina) {
        System.out.println("Professor " + nome + " obtendo alunos da disciplina: " + disciplina.getNome());
        return disciplina.getAlunosMatriculados();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        System.out.println("Adicionando disciplina " + disciplina.getNome() + " ao professor " + nome);
        this.disciplinasMinistradas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        System.out.println("Removendo disciplina " + disciplina.getNome() + " do professor " + nome);
        this.disciplinasMinistradas.remove(disciplina);
    }

    public List<Disciplina> getDisciplinasMinistradas() {
        System.out.println("Obtendo disciplinas ministradas pelo professor: " + nome);
        return new ArrayList<>(disciplinasMinistradas);
    }

    public boolean podeMinistrarDisciplina(Disciplina disciplina) {
        System.out.println("Verificando se professor " + nome + " pode ministrar " + disciplina.getNome());
        return true;
    }

    public String getSiape() {
        return siape;
    }

    public void setSiape(String siape) {
        this.siape = siape;
    }

    public String getAreaEspecializacao() {
        return areaEspecializacao;
    }

    public void setAreaEspecializacao(String areaEspecializacao) {
        this.areaEspecializacao = areaEspecializacao;
    }

    public void setDisciplinasMinistradas(List<Disciplina> disciplinasMinistradas) {
        this.disciplinasMinistradas = disciplinasMinistradas;
    }
}
