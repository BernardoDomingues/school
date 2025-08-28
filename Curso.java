import java.util.*;

public class Curso {
    private String id;
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinas;
    private Professor coordenador;

    public Curso(String id, String nome, int creditosTotais, Professor coordenador) {
        this.id = id;
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.coordenador = coordenador;
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        System.out.println("Adicionando disciplina " + disciplina.getNome() + " ao curso " + nome);
        this.disciplinas.add(disciplina);
    }

    public void removerDisciplina(Disciplina disciplina) {
        System.out.println("Removendo disciplina " + disciplina.getNome() + " do curso " + nome);
        this.disciplinas.remove(disciplina);
    }

    public List<Disciplina> getDisciplinasObrigatorias() {
        System.out.println("Obtendo disciplinas obrigatórias do curso: " + nome);
        List<Disciplina> obrigatorias = new ArrayList<>();
        for (Disciplina disc : disciplinas) {
            if (disc.isObrigatoria()) {
                obrigatorias.add(disc);
            }
        }
        return obrigatorias;
    }

    public List<Disciplina> getDisciplinasOptativas() {
        System.out.println("Obtendo disciplinas optativas do curso: " + nome);
        List<Disciplina> optativas = new ArrayList<>();
        for (Disciplina disc : disciplinas) {
            if (!disc.isObrigatoria()) {
                optativas.add(disc);
            }
        }
        return optativas;
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

    public int getCreditosTotais() {
        return creditosTotais;
    }

    public void setCreditosTotais(int creditosTotais) {
        this.creditosTotais = creditosTotais;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Professor getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Professor coordenador) {
        this.coordenador = coordenador;
    }
}
