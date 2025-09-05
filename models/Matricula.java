package models;

import java.time.LocalDateTime;

public class Matricula {
    private String id;
    private Aluno aluno;
    private Disciplina disciplina;
    private LocalDateTime dataMatricula;
    private boolean obrigatoria;
    private boolean ativa;
    private String semestre;

    public Matricula(String id, Aluno aluno, Disciplina disciplina, boolean obrigatoria, String semestre) {
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.obrigatoria = obrigatoria;
        this.semestre = semestre;
        this.dataMatricula = LocalDateTime.now();
        this.ativa = true;
    }

    public void cancelarMatricula() {
        System.out.println("Cancelando matrícula " + id + " do aluno " + aluno.getNome());
        this.ativa = false;
    }

    public boolean isAtiva() {
        System.out.println("Verificando se matrícula " + id + " está ativa");
        return ativa;
    }

    public boolean isObrigatoria() {
        System.out.println("Verificando se matrícula " + id + " é obrigatória");
        return obrigatoria;
    }

    public double calcularValor() {
        System.out.println("Calculando valor da matrícula " + id);
        return disciplina.getCreditos() * 100.0;
    }

    public String gerarComprovante() {
        System.out.println("Gerando comprovante da matrícula " + id);
        return "Comprovante de Matrícula - ID: " + id +
                " - Aluno: " + aluno.getNome() +
                " - Disciplina: " + disciplina.getNome() +
                " - Data: " + dataMatricula;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
}
