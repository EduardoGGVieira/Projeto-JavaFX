package elderia.com.elderia;

import java.io.Serializable;

public class HorarioDisponivel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String profissional;
    private String data;
    private String hora;
    private boolean reservado;

    public HorarioDisponivel(String profissional, String data, String hora) {
        this.profissional = profissional;
        this.data = data;
        this.hora = hora;
        this.reservado = false;
    }

    public String getProfissional() {
        return profissional;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    @Override
    public String toString() {
        return data + " - " + hora;
    }
}