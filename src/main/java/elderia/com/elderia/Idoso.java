package elderia.com.elderia;

import java.time.LocalDate;
import java.time.Period;


// TODOS TEM QUE EXTENDES DE USUARIO?
public class Idoso {

    private int idIdoso;
    private String nome;
    private int cpf;
    private LocalDate dataNascimento;
    private String alergias;
    private String informacoesSaude;
    private String necessidadesAcessibilidade;


    // ISSO AQ É VOID? DEU ERRO SEM KKKKKK

    public  void Idoso(int idIdoso, String nome, int cpf, LocalDate dataNascimento,
                 String alergias, String informacoesSaude, String necessidadesAcessibilidade) {
        this.idIdoso = idIdoso;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.alergias = alergias;
        this.informacoesSaude = informacoesSaude;
        this.necessidadesAcessibilidade = necessidadesAcessibilidade;
    }

//    MÉTODO 1: Calcula a idade exata com base na Data de Nascimento

    public int calcularIdade() {
        if (this.dataNascimento == null) {
            return 0;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }

    // MÉTODO 2: Gera um relatório ou resumo clínico rápido do paciente

    public String gerarResumoClinico() {
        StringBuilder resumo = new StringBuilder();
        resumo.append("=== PRONTUÁRIO RESUMIDO ===\n");
        resumo.append("Paciente: ").append(this.nome).append(" (").append(calcularIdade()).append(" anos)\n");
        resumo.append("Alergias registradas: ").append(this.alergias.isEmpty() ? "Nenhuma" : this.alergias).append("\n");
        resumo.append("Condições de Saúde: ").append(this.informacoesSaude.isEmpty() ? "Nenhuma observação" : this.informacoesSaude).append("\n");
        resumo.append("Necessita de Acessibilidade: ").append(this.necessidadesAcessibilidade.isEmpty() ? "Não" : this.necessidadesAcessibilidade);
        return resumo.toString();
    }


    // GETS E STES , TUDO GENERATE

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getCpf() {
        return cpf;
    }

    public int getIdIdoso() {
        return idIdoso;
    }

    public void setIdIdoso(int idIdoso) {
        this.idIdoso = idIdoso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getInformacoesSaude() {
        return informacoesSaude;
    }

    public void setInformacoesSaude(String informacoesSaude) {
        this.informacoesSaude = informacoesSaude;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getNecessidadesAcessibilidade() {
        return necessidadesAcessibilidade;
    }

    public void setNecessidadesAcessibilidade(String necessidadesAcessibilidade) {
        this.necessidadesAcessibilidade = necessidadesAcessibilidade;
    }
}
