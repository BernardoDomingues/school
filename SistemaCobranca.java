import java.util.*;
import java.time.LocalDateTime;

public class SistemaCobranca {
    private List<Cobranca> cobrancas;
    private double valorPorCredito;

    public SistemaCobranca() {
        this.cobrancas = new ArrayList<>();
        this.valorPorCredito = 100.0;
    }

    public void gerarCobranca(Aluno aluno, List<Disciplina> disciplinas) {
        System.out.println("Gerando cobrança para aluno: " + aluno.getNome());

        double valorTotal = 0;
        for (Disciplina disciplina : disciplinas) {
            valorTotal += disciplina.getCreditos() * valorPorCredito;
        }

        Cobranca cobranca = new Cobranca(
                gerarIdCobranca(),
                aluno,
                disciplinas,
                valorTotal,
                LocalDateTime.now());

        cobrancas.add(cobranca);
        System.out.println("Cobrança gerada com valor: R$ " + valorTotal);
    }

    public List<Cobranca> getCobrancasAluno(Aluno aluno) {
        System.out.println("Obtendo cobranças do aluno: " + aluno.getNome());
        List<Cobranca> cobrancasAluno = new ArrayList<>();
        for (Cobranca cobranca : cobrancas) {
            if (cobranca.getAluno().equals(aluno)) {
                cobrancasAluno.add(cobranca);
            }
        }
        return cobrancasAluno;
    }

    public double calcularValorTotal(Aluno aluno) {
        System.out.println("Calculando valor total das cobranças do aluno: " + aluno.getNome());
        double total = 0;
        for (Cobranca cobranca : getCobrancasAluno(aluno)) {
            total += cobranca.getValor();
        }
        return total;
    }

    public void registrarPagamento(Cobranca cobranca, double valorPago) {
        System.out.println("Registrando pagamento de R$ " + valorPago + " para cobrança: " + cobranca.getId());
        cobranca.registrarPagamento(valorPago);
    }

    public List<Cobranca> getCobrancasVencidas() {
        System.out.println("Obtendo cobranças vencidas");
        List<Cobranca> vencidas = new ArrayList<>();
        for (Cobranca cobranca : cobrancas) {
            if (cobranca.isVencida()) {
                vencidas.add(cobranca);
            }
        }
        return vencidas;
    }

    private String gerarIdCobranca() {
        return "COB" + System.currentTimeMillis();
    }

    public List<Cobranca> getCobrancas() {
        return cobrancas;
    }

    public void setCobrancas(List<Cobranca> cobrancas) {
        this.cobrancas = cobrancas;
    }

    public double getValorPorCredito() {
        return valorPorCredito;
    }

    public void setValorPorCredito(double valorPorCredito) {
        this.valorPorCredito = valorPorCredito;
    }
}
