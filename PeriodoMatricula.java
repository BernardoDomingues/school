import java.time.LocalDateTime;

public class PeriodoMatricula {
    private String id;
    private String nome;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String semestre;
    private boolean ativo;

    public PeriodoMatricula(String id, String nome, LocalDateTime dataInicio, LocalDateTime dataFim, String semestre) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.semestre = semestre;
        this.ativo = false;
    }

    public void iniciarPeriodo() {
        System.out.println("Iniciando período de matrícula: " + nome);
        this.ativo = true;
    }

    public void encerrarPeriodo() {
        System.out.println("Encerrando período de matrícula: " + nome);
        this.ativo = false;
    }

    public boolean isAtivo() {
        System.out.println("Verificando se período " + nome + " está ativo");
        LocalDateTime agora = LocalDateTime.now();
        return ativo && agora.isAfter(dataInicio) && agora.isBefore(dataFim);
    }

    public boolean isPeriodoValido() {
        System.out.println("Verificando se período " + nome + " é válido");
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(dataInicio) && agora.isBefore(dataFim);
    }

    public long getDiasRestantes() {
        System.out.println("Calculando dias restantes do período: " + nome);
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isBefore(dataFim)) {
            return java.time.Duration.between(agora, dataFim).toDays();
        }
        return 0;
    }

    public String getStatus() {
        System.out.println("Obtendo status do período: " + nome);
        if (!ativo) {
            return "Inativo";
        } else if (isPeriodoValido()) {
            return "Ativo";
        } else {
            return "Encerrado";
        }
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

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
