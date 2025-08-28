import java.util.*;
import java.time.LocalDateTime;

public class Cobranca {
    private String id;
    private Aluno aluno;
    private List<Disciplina> disciplinas;
    private double valor;
    private LocalDateTime dataGeracao;
    private LocalDateTime dataVencimento;
    private double valorPago;
    private boolean paga;

    public Cobranca(String id, Aluno aluno, List<Disciplina> disciplinas, double valor, LocalDateTime dataGeracao) {
        this.id = id;
        this.aluno = aluno;
        this.disciplinas = new ArrayList<>(disciplinas);
        this.valor = valor;
        this.dataGeracao = dataGeracao;
        this.dataVencimento = dataGeracao.plusDays(30);
        this.valorPago = 0;
        this.paga = false;
    }

    public void registrarPagamento(double valorPago) {
        System.out.println("Registrando pagamento de R$ " + valorPago + " na cobrança " + id);
        this.valorPago += valorPago;
        if (this.valorPago >= this.valor) {
            this.paga = true;
            System.out.println("Cobrança " + id + " foi totalmente paga");
        }
    }

    public boolean isVencida() {
        System.out.println("Verificando se cobrança " + id + " está vencida");
        return !paga && LocalDateTime.now().isAfter(dataVencimento);
    }

    public boolean isPaga() {
        System.out.println("Verificando se cobrança " + id + " foi paga");
        return paga;
    }

    public double getValorRestante() {
        System.out.println("Calculando valor restante da cobrança " + id);
        return Math.max(0, valor - valorPago);
    }

    public String gerarBoleto() {
        System.out.println("Gerando boleto para cobrança " + id);
        return "Boleto - ID: " + id +
                " - Aluno: " + aluno.getNome() +
                " - Valor: R$ " + valor +
                " - Vencimento: " + dataVencimento;
    }

    public List<String> getDisciplinasCobradas() {
        System.out.println("Obtendo disciplinas cobradas na cobrança " + id);
        List<String> nomes = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            nomes.add(disciplina.getNome());
        }
        return nomes;
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }
}
