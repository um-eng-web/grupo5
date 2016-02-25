package betess;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author ana almeida
 */
public class Apostador extends User implements Observer {

    private double valor;
    private int contadorIdAposta;
    //private HashMap<LocalDateTime, Evento> eventos;
    private TreeMap<Integer, Aposta> listaApostas;
    private TreeSet<Integer> notificacoesOdd; // id do evento a qual as odds se referem

    /**
     * CONSTRUTORES
     */
    public Apostador(String email, String password, String nome, double valor) {
        super(email, password, nome);
        this.contadorIdAposta = 0;
        this.valor = valor;
        this.listaApostas = new TreeMap<>();
        this.notificacoesOdd = new TreeSet<>();
    }

    /**
     * GETTERS E SETTERS
     */
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void addContadorAposta() {
        this.contadorIdAposta++;
    }

    public int getContadorAposta() {
        return this.contadorIdAposta;

    }

    /**
     * Adicionar Aposta
     */
    public void addAposta(int aposta, Aposta apos) {
        // System.out.println("Odd Aposta Apotador:" + apos.getOdd().getOddB());
        this.listaApostas.put(apos.getId(), apos);
        this.contadorIdAposta++;
    }

    public boolean containsEvent(int id) {
        boolean flag = false;
        for (int idAposta : this.listaApostas.keySet()) {
            if (this.listaApostas.get(id).getIdEvento() == id) {
                flag = true;
                break;
            }

        }
        return flag;
    }

    public ArrayList<Aposta> getApostasByIdEvent(int evento) {
        ArrayList<Aposta> aux = new ArrayList<>();
        for (int idAposta : this.listaApostas.keySet()) {
            if (this.listaApostas.get(idAposta).getIdEvento() == evento) {
                aux.add(this.listaApostas.get(idAposta));
            }
        }
        return aux;
    }

    /**
     * Obter Valor Ganho na Aposta
     */
    public double getGanhoAposta(Aposta apos) {
        return this.listaApostas.get(apos.getId()).getGanho();
    }

    /**
     * Obter Lista Apostas
     *
     * @return
     */
    public TreeMap<Integer, Aposta> getListaApostas() {
        return this.listaApostas;
    }

    public void setResultadoAposta(int idAposta, int resultadoAposta) {
        this.listaApostas.get(idAposta).setResultado(resultadoAposta);
        this.listaApostas.get(idAposta).setGanho();
        //this.listaApostas.get(idAposta).setEstadoOff();
    }

    public String ganhoApostasToString() {
        StringBuilder s = new StringBuilder();
        for (int id : this.listaApostas.keySet()) {
            Aposta a = this.listaApostas.get(id);
            /*
             if (a.getEstado()) {
             s.append(a.toString()).append("\n");
             s.append("Ganho = ").append(a.getGanho());

             }
             */

        }
        return s.toString();

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

    @Override
    public void update(int idEvento, String evento,int resultado) {
        for (Aposta apos : this.getApostasByIdEvent(idEvento)) {
            apos.setFechada();
            apos.setResultado(resultado);
            apos.calGanho();
            this.valor += apos.getGanho();
        }
    }

    @Override
    public void updateOdd(int idEvento) {
        this.notificacoesOdd.add(idEvento);

    }

}
