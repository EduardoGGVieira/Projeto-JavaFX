package elderia.com.elderia;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AgendaDisponivel extends Proffisional{
    private int idAgenda;
    private int idProfissional;
    private String diaSemana;
    private LocalTime horario;


    public AgendaDisponivel(int idAgenda, int idProfissional, String diaSemana, LocalTime horario) {
        this.idAgenda = idAgenda;
        this.idProfissional = idProfissional;
        this.diaSemana = diaSemana;
        this.horario = horario;

    }


    // MÉTODO 1: Identifica se o horário cadastrado é um fim de semana

    public boolean ehFimDeSemana() {
        if (this.diaSemana == null) return false;
        String dia = this.diaSemana.trim().toLowerCase();
        return dia.equals("sábado") || dia.equals("domingo") || dia.equals("sabado");
    }

    // MÉTODO 2: Retorna o horário formatado em HH:mm (padrão Brasil)

    public String obterHorarioFormatado() {
        if (this.horario == null) return "00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return this.horario.format(formatter);
    }




    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
