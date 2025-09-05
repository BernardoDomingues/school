package models;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GerenciadorArquivos {
    private static final String DIRETORIO_DADOS = "dados/";
    private static final String ARQUIVO_ALUNOS = "alunos.txt";
    private static final String ARQUIVO_PROFESSORES = "professores.txt";
    private static final String ARQUIVO_SECRETARIAS = "secretarias.txt";
    private static final String ARQUIVO_CURSOS = "cursos.txt";
    private static final String ARQUIVO_DISCIPLINAS = "disciplinas.txt";
    private static final String ARQUIVO_MATRICULAS = "matriculas.txt";
    private static final String ARQUIVO_PERIODOS = "periodos.txt";

    private SistemaMatriculas sistema;

    public GerenciadorArquivos(SistemaMatriculas sistema) {
        this.sistema = sistema;
        criarDiretorioDados();
    }

    private void criarDiretorioDados() {
        System.out.println("Criando diretório de dados: " + DIRETORIO_DADOS);
        File diretorio = new File(DIRETORIO_DADOS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public void salvarAlunos(List<Aluno> alunos) {
        System.out.println("Salvando " + alunos.size() + " alunos no arquivo: " + ARQUIVO_ALUNOS);
        try (PrintWriter writer = new PrintWriter(new FileWriter(DIRETORIO_DADOS + ARQUIVO_ALUNOS))) {
            for (Aluno aluno : alunos) {
                String cursoId = aluno.getCurso() != null ? aluno.getCurso().getId() : "";
                writer.println(aluno.getId() + "|" + aluno.getNome() + "|" + aluno.getEmail() + "|" +
                        "senha123" + "|" + aluno.getMatricula() + "|" + cursoId);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    public List<Aluno> carregarAlunos() {
        System.out.println("Carregando alunos do arquivo: " + ARQUIVO_ALUNOS);
        List<Aluno> alunos = new ArrayList<>();
        File arquivo = new File(DIRETORIO_DADOS + ARQUIVO_ALUNOS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de alunos não encontrado, criando lista vazia");
            return alunos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 6) {
                    String id = dados[0];
                    String nome = dados[1];
                    String email = dados[2];
                    String senha = dados[3];
                    String matricula = dados[4];
                    String cursoId = dados[5];

                    // Buscar o curso correspondente
                    Curso curso = buscarCursoPorId(cursoId);
                    if (curso != null) {
                        Aluno aluno = new Aluno(id, nome, email, senha, matricula, curso);
                        alunos.add(aluno);
                        System.out.println("Aluno carregado: " + nome);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public void salvarProfessores(List<Professor> professores) {
        System.out.println("Salvando " + professores.size() + " professores no arquivo: " + ARQUIVO_PROFESSORES);
        try (PrintWriter writer = new PrintWriter(new FileWriter(DIRETORIO_DADOS + ARQUIVO_PROFESSORES))) {
            for (Professor professor : professores) {
                writer.println(professor.getId() + "|" + professor.getNome() + "|" + professor.getEmail() +
                        "|senha123|" + professor.getSiape() + "|" + professor.getAreaEspecializacao());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar professores: " + e.getMessage());
        }
    }

    public List<Professor> carregarProfessores() {
        System.out.println("Carregando professores do arquivo: " + ARQUIVO_PROFESSORES);
        List<Professor> professores = new ArrayList<>();
        File arquivo = new File(DIRETORIO_DADOS + ARQUIVO_PROFESSORES);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de professores não encontrado, criando lista vazia");
            return professores;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 6) {
                    String id = dados[0];
                    String nome = dados[1];
                    String email = dados[2];
                    String senha = dados[3];
                    String siape = dados[4];
                    String areaEspecializacao = dados[5];

                    Professor professor = new Professor(id, nome, email, senha, siape, areaEspecializacao);
                    professores.add(professor);
                    System.out.println("Professor carregado: " + nome);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar professores: " + e.getMessage());
        }
        return professores;
    }

    public void salvarSecretarias(List<Secretaria> secretarias) {
        System.out.println("Salvando " + secretarias.size() + " secretarias no arquivo: " + ARQUIVO_SECRETARIAS);
        try (PrintWriter writer = new PrintWriter(new FileWriter(DIRETORIO_DADOS + ARQUIVO_SECRETARIAS))) {
            for (Secretaria secretaria : secretarias) {
                writer.println(secretaria.getId() + "|" + secretaria.getNome() + "|" + secretaria.getEmail() + "|senha123");
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar secretarias: " + e.getMessage());
        }
    }

    public List<Secretaria> carregarSecretarias() {
        System.out.println("Carregando secretarias do arquivo: " + ARQUIVO_SECRETARIAS);
        List<Secretaria> secretarias = new ArrayList<>();
        File arquivo = new File(DIRETORIO_DADOS + ARQUIVO_SECRETARIAS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de secretarias não encontrado, criando lista vazia");
            return secretarias;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 4) {
                    String id = dados[0];
                    String nome = dados[1];
                    String email = dados[2];
                    String senha = dados[3];

                    Secretaria secretaria = new Secretaria(id, nome, email, senha, sistema);
                    secretarias.add(secretaria);
                    System.out.println("Secretaria carregada: " + nome);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar secretarias: " + e.getMessage());
        }
        return secretarias;
    }

    public void salvarCursos(List<Curso> cursos) {
        System.out.println("Salvando " + cursos.size() + " cursos no arquivo: " + ARQUIVO_CURSOS);
        try (PrintWriter writer = new PrintWriter(new FileWriter(DIRETORIO_DADOS + ARQUIVO_CURSOS))) {
            for (Curso curso : cursos) {
                String coordenadorId = curso.getCoordenador() != null ? curso.getCoordenador().getId() : "";
                writer.println(
                        curso.getId() + "|" + curso.getNome() + "|" + curso.getCreditosTotais() + "|" + coordenadorId);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar cursos: " + e.getMessage());
        }
    }

    public List<Curso> carregarCursos() {
        System.out.println("Carregando cursos do arquivo: " + ARQUIVO_CURSOS);
        List<Curso> cursos = new ArrayList<>();
        File arquivo = new File(DIRETORIO_DADOS + ARQUIVO_CURSOS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de cursos não encontrado, criando lista vazia");
            return cursos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 4) {
                    String id = dados[0];
                    String nome = dados[1];
                    int creditosTotais = Integer.parseInt(dados[2]);
                    String coordenadorId = dados[3];

                    // Buscar o professor coordenador
                    Professor coordenador = buscarProfessorPorId(coordenadorId);
                    Curso curso = new Curso(id, nome, creditosTotais, coordenador);
                    cursos.add(curso);
                    System.out.println("Curso carregado: " + nome);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar cursos: " + e.getMessage());
        }
        return cursos;
    }

    public void salvarDisciplinas(List<Disciplina> disciplinas) {
        System.out.println("Salvando " + disciplinas.size() + " disciplinas no arquivo: " + ARQUIVO_DISCIPLINAS);
        try (PrintWriter writer = new PrintWriter(new FileWriter(DIRETORIO_DADOS + ARQUIVO_DISCIPLINAS))) {
            for (Disciplina disciplina : disciplinas) {
                String professorId = disciplina.getProfessor() != null ? disciplina.getProfessor().getId() : "";
                String cursoId = disciplina.getCurso() != null ? disciplina.getCurso().getId() : "";
                writer.println(disciplina.getId() + "|" + disciplina.getNome() + "|" + disciplina.getCodigo() +
                        "|" + disciplina.getCreditos() + "|" + professorId + "|" + cursoId + "|" +
                        disciplina.isObrigatoria() + "|" + disciplina.getSemestre());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
        }
    }

    public List<Disciplina> carregarDisciplinas() {
        System.out.println("Carregando disciplinas do arquivo: " + ARQUIVO_DISCIPLINAS);
        List<Disciplina> disciplinas = new ArrayList<>();
        File arquivo = new File(DIRETORIO_DADOS + ARQUIVO_DISCIPLINAS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de disciplinas não encontrado, criando lista vazia");
            return disciplinas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 8) {
                    String id = dados[0];
                    String nome = dados[1];
                    String codigo = dados[2];
                    int creditos = Integer.parseInt(dados[3]);
                    String professorId = dados[4];
                    String cursoId = dados[5];
                    boolean obrigatoria = Boolean.parseBoolean(dados[6]);
                    String semestre = dados[7];

                    // Buscar professor e curso
                    Professor professor = buscarProfessorPorId(professorId);
                    Curso curso = buscarCursoPorId(cursoId);

                    if (professor != null && curso != null) {
                        Disciplina disciplina = new Disciplina(id, nome, codigo, creditos, professor, curso,
                                obrigatoria, semestre);
                        disciplinas.add(disciplina);
                        System.out.println("Disciplina carregada: " + nome);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar disciplinas: " + e.getMessage());
        }
        return disciplinas;
    }

    public void salvarMatriculas(List<Matricula> matriculas) {
        System.out.println("Salvando " + matriculas.size() + " matrículas no arquivo: " + ARQUIVO_MATRICULAS);
        try (PrintWriter writer = new PrintWriter(new FileWriter(DIRETORIO_DADOS + ARQUIVO_MATRICULAS))) {
            for (Matricula matricula : matriculas) {
                String alunoId = matricula.getAluno() != null ? matricula.getAluno().getId() : "";
                String disciplinaId = matricula.getDisciplina() != null ? matricula.getDisciplina().getId() : "";
                String dataMatricula = matricula.getDataMatricula().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                writer.println(matricula.getId() + "|" + alunoId + "|" + disciplinaId + "|" +
                        matricula.isObrigatoria() + "|" + matricula.getSemestre() + "|" + dataMatricula);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar matrículas: " + e.getMessage());
        }
    }

    public List<Matricula> carregarMatriculas() {
        System.out.println("Carregando matrículas do arquivo: " + ARQUIVO_MATRICULAS);
        List<Matricula> matriculas = new ArrayList<>();
        File arquivo = new File(DIRETORIO_DADOS + ARQUIVO_MATRICULAS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de matrículas não encontrado, criando lista vazia");
            return matriculas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 6) {
                    String id = dados[0];
                    String alunoId = dados[1];
                    String disciplinaId = dados[2];
                    boolean obrigatoria = Boolean.parseBoolean(dados[3]);
                    String semestre = dados[4];
                    LocalDateTime dataMatricula = LocalDateTime.parse(dados[5], DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                    // Buscar aluno e disciplina
                    Aluno aluno = buscarAlunoPorId(alunoId);
                    Disciplina disciplina = buscarDisciplinaPorId(disciplinaId);

                    if (aluno != null && disciplina != null) {
                        Matricula matricula = new Matricula(id, aluno, disciplina, obrigatoria, semestre);
                        matricula.setDataMatricula(dataMatricula);
                        matriculas.add(matricula);
                        System.out.println("Matrícula carregada: " + id);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar matrículas: " + e.getMessage());
        }
        return matriculas;
    }

    public void salvarPeriodos(List<PeriodoMatricula> periodos) {
        System.out.println("Salvando " + periodos.size() + " períodos no arquivo: " + ARQUIVO_PERIODOS);
        try (PrintWriter writer = new PrintWriter(new FileWriter(DIRETORIO_DADOS + ARQUIVO_PERIODOS))) {
            for (PeriodoMatricula periodo : periodos) {
                String dataInicio = periodo.getDataInicio().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                String dataFim = periodo.getDataFim().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                writer.println(periodo.getId() + "|" + periodo.getNome() + "|" + dataInicio + "|" +
                        dataFim + "|" + periodo.getSemestre() + "|" + periodo.isAtivo());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar períodos: " + e.getMessage());
        }
    }

    public List<PeriodoMatricula> carregarPeriodos() {
        System.out.println("Carregando períodos do arquivo: " + ARQUIVO_PERIODOS);
        List<PeriodoMatricula> periodos = new ArrayList<>();
        File arquivo = new File(DIRETORIO_DADOS + ARQUIVO_PERIODOS);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de períodos não encontrado, criando lista vazia");
            return periodos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|");
                if (dados.length >= 6) {
                    String id = dados[0];
                    String nome = dados[1];
                    LocalDateTime dataInicio = LocalDateTime.parse(dados[2], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    LocalDateTime dataFim = LocalDateTime.parse(dados[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    String semestre = dados[4];
                    boolean ativo = Boolean.parseBoolean(dados[5]);

                    PeriodoMatricula periodo = new PeriodoMatricula(id, nome, dataInicio, dataFim, semestre);
                    periodo.setAtivo(ativo);
                    periodos.add(periodo);
                    System.out.println("Período carregado: " + nome);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar períodos: " + e.getMessage());
        }
        return periodos;
    }



    // Métodos auxiliares para buscar entidades por ID
    private Aluno buscarAlunoPorId(String id) {
        for (Aluno aluno : sistema.getAlunos()) {
            if (aluno.getId().equals(id)) {
                return aluno;
            }
        }
        return null;
    }

    private Professor buscarProfessorPorId(String id) {
        for (Professor professor : sistema.getProfessores()) {
            if (professor.getId().equals(id)) {
                return professor;
            }
        }
        return null;
    }

    private Curso buscarCursoPorId(String id) {
        for (Curso curso : sistema.getCursos()) {
            if (curso.getId().equals(id)) {
                return curso;
            }
        }
        return null;
    }

    private Disciplina buscarDisciplinaPorId(String id) {
        for (Disciplina disciplina : sistema.getDisciplinas()) {
            if (disciplina.getId().equals(id)) {
                return disciplina;
            }
        }
        return null;
    }

    public void salvarTodosOsDados() {
        System.out.println("Salvando todos os dados do sistema...");
        salvarAlunos(sistema.getAlunos());
        salvarProfessores(sistema.getProfessores());
        salvarSecretarias(sistema.getSecretarias());
        salvarCursos(sistema.getCursos());
        salvarDisciplinas(sistema.getDisciplinas());
        salvarMatriculas(sistema.getMatriculas());
        salvarPeriodos(sistema.getPeriodosMatricula());
        System.out.println("Todos os dados foram salvos com sucesso!");
    }

    public void carregarTodosOsDados() {
        System.out.println("Carregando todos os dados do sistema...");

        List<Professor> professores = carregarProfessores();
        List<Secretaria> secretarias = carregarSecretarias();
        List<Curso> cursos = carregarCursos();
        List<Aluno> alunos = carregarAlunos();
        List<Disciplina> disciplinas = carregarDisciplinas();
        List<Matricula> matriculas = carregarMatriculas();
        List<PeriodoMatricula> periodos = carregarPeriodos();

        sistema.getProfessores().clear();
        sistema.getProfessores().addAll(professores);

        sistema.getSecretarias().clear();
        sistema.getSecretarias().addAll(secretarias);

        sistema.getCursos().clear();
        sistema.getCursos().addAll(cursos);

        sistema.getAlunos().clear();
        sistema.getAlunos().addAll(alunos);

        sistema.getDisciplinas().clear();
        sistema.getDisciplinas().addAll(disciplinas);

        sistema.getMatriculas().clear();
        sistema.getMatriculas().addAll(matriculas);

        sistema.getPeriodosMatricula().clear();
        sistema.getPeriodosMatricula().addAll(periodos);


        System.out.println("Todos os dados foram carregados com sucesso!");
    }
}
