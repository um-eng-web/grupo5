package betess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Bruno Pereira
 */
public class Evento implements Observable {

    private int id;
    private String descricao;
    private ArrayList<Observer> observers;

    private LocalDateTime dataInit;
    private Odd odd;
    private int resultadoFinal;
    //private HashMap<Integer, Odd> listaOdds;
    //private HashMap<Integer, Aposta> apostas;

    private boolean estado;
    private boolean concluida;

    private String nomeEquipaA;
    private String nomeEquipaB;

    public Evento(int id, String des, LocalDateTime d, double oddA, double oddB, double oddEm, String eA, String eB) {
        this.id = id;
        this.descricao = des;
        this.dataInit = d;
        this.nomeEquipaA = eA;
        this.nomeEquipaB = eB;
        this.estado = true;
        this.concluida = false;
        this.observers = new ArrayList<>();

        odd = new Odd(oddA, oddB, oddEm);
        // this.listaOdds.put(contadorIdOdd, o);

    }

    public String getEquipaA() {
        return this.nomeEquipaA;
    }

    public String getEquipaB() {
        return this.nomeEquipaB;
    }

    public int getId() {
        return this.id;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public boolean concluido() {
        return this.concluida;
    }

    public void setEstadoOff() {
        this.estado = false;
    }

    public void setResultado(int resultado) {
        this.resultadoFinal = resultado;
    }

    public int getResultado() {
        return this.resultadoFinal;
    }

    public void setOdd(double oddA, double oddB, double oddEm) { //bookie
        //contadorIdOdd++;
        odd = new Odd(oddA, oddB, oddEm);
        for (Observer o : this.observers) {
            o.updateOdd(id);

        }

        // this.listaOdds.put(contadorIdOdd, o);
    }
    /*
     private void fechaEvento(int resultado) {
     for (int id : apostas.keySet()) {
     apostas.get(id).calGanho(resultado);
     }
     }
     */

    public void setConcluido() {
        this.concluida = true;
    }

    public Odd getOdd() {
        return this.odd;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = dataInit.format(formatter);
        StringBuilder s = new StringBuilder();
        //double oddA = this.listaOdds.get(contadorIdOdd).getOddA();
        //double oddB = this.listaOdds.get(contadorIdOdd).getOddB();
        //double oddEm = this.listaOdds.get(contadorIdOdd).getOddEmpate();

        s.append("Id Evento: ").append(this.id).append("\n");
        //s.append("Data: ").append(this.dataInit.toString()).append("\n");
        s.append("Data: ").append(formattedDateTime).append("\n");
        s.append("Aposta: ").append(this.nomeEquipaA).append("-");
        s.append(this.nomeEquipaB).append(" ");
        s.append("(").append(odd.getOddA()).append(",").append(odd.getOddB()).append(",").append(odd.getOddEmpate()).append(")").append("\n");

        return s.toString();
    }

    public LocalDateTime getDate() {
        return this.dataInit;
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObserver(int resultado) {

        for (Observer o : this.observers) {
            StringBuilder s = new StringBuilder();
            String res;
            switch (resultado) {
                case (0):
                    res = "Empate";
                    break;
                case (1):
                    res = this.nomeEquipaA;
                    break;
                default:
                    res = this.nomeEquipaB;
                    break;
            }
            s.append(this.toString()).append("Resultado: ").append(res).append("\n");

            o.update(this.id, s.toString(), resultado);
        }
    }

    @Override
    public void notifyObserverOdd() {
        for (Observer o : this.observers) {
            o.updateOdd(this.id);
        }

    }

}
