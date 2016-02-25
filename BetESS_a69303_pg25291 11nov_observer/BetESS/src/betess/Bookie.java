package betess;

import java.util.HashMap;
import java.util.TreeSet;

/**
 *
 * @author Bruno Pereira
 */
public class Bookie extends User implements Observer {

    //private HashMap<Integer, Evento> eventos;

    private HashMap<Integer, String> resultadoEventos;
    private TreeSet<Integer> notificacoesOdd; // id do evento a qual as odds se referem
    private TreeSet<Integer> eventosCriados;
    public Bookie(String email, String password, String nome) {
        super(email, password, nome);
       // this.eventos = new HashMap<>();
        this.notificacoesOdd = new TreeSet<>();
        this.resultadoEventos=new HashMap<>();
        this.eventosCriados=new TreeSet<>();
    }

    public void addEvento(int id) {

        this.eventosCriados.add(id);

    }

    public TreeSet<Integer> getEventos() {
        return this.eventosCriados;
    }

    public TreeSet<Integer> getNotificacoesOdd() {
        return this.notificacoesOdd;
    }

    public void setNotificacaoOdd(int idEvento) {
        this.notificacoesOdd.add(idEvento);

    }

    public void limpaNoficacoesOdd() {
        this.notificacoesOdd = new TreeSet<>();
    }

    public String getResultadosEventos() {
        StringBuilder s = new StringBuilder();
        if (!this.resultadoEventos.isEmpty()) {
            for (int id : this.resultadoEventos.keySet()) {
                s.append(this.resultadoEventos.get(id));
            }
        }
        return s.toString();
    }

    @Override
    public void update(int idEvento, String evento, int resultado) {
        this.resultadoEventos.put(idEvento, evento);

    }

    @Override
    public void updateOdd(int idEvento) {
        this.notificacoesOdd.add(idEvento);

    }

}
