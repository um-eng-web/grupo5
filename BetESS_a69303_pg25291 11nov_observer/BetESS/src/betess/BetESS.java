package betess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bruno Pereira
 */
public class BetESS  implements Serializable {

    private HashMap<String, User> listaUtilizadores; //email para utilizador
    // private HashMap<Integer, Aposta> listaApostas;
    private HashMap<Integer, Evento> listaEventos;
  

    public BetESS() {
        this.listaUtilizadores = new HashMap<>();
        //this.listaApostas = new HashMap<>();
        this.listaEventos = new HashMap<>();

    }

    public boolean existUser(String email) {

        return this.listaUtilizadores.containsKey(email);

    }

    public void addEvento(int id, Evento e) {
        this.listaEventos.put(id, e);
    }

    public HashMap<Integer, Evento> getEventos() {
        return this.listaEventos;
    }

    public void addUser(User u) {
        this.listaUtilizadores.put(u.getEmail(), u);

    }
    /*
     public void addAposta(Aposta a) {
     System.out.println("Add Aposta");
     this.listaApostas.put(a.getId(), a);
     }
     */

    public HashMap<String, User> getUsers() {
        return this.listaUtilizadores;
    }
    /*
     public HashMap<Integer, Aposta> getApostas() {
     return this.listaApostas;
     }
     */
/*
    void avisaUsers(int id, int resultado) {
        for (String email : this.listaUtilizadores.keySet()) {
            User u = this.listaUtilizadores.get(email);
            if (u instanceof Apostador) {
                Apostador a = (Apostador) u;
                // if (a.getListaApostas().containsKey(id)) {
                if (a.containsEvent(id)) {
                    for (Aposta apos : a.getApostasByIdEvent(id)) {
                        apos.setFechada();
                        apos.setResultado(resultado);
                        apos.calGanho();
                        a.setValor(a.getValor() + apos.getGanho());

                        // a.getListaApostas().get(id).setFechada();
                        //a.getListaApostas().get(id).setResultado(resultado);
                        //a.getListaApostas().get(id).calGanho();
                    }
                }
            } else if (u instanceof Bookie) {
                Bookie a = (Bookie) u;
                if (a.getEventos().containsKey(id)) {

                    a.getEventos().get(id).concluido();
                    a.getEventos().get(id).setResultado(resultado);

                }
            }

        }
    }
    */

    /*
     void avisaUsers(int id,int resultado){
     this.listaEventos.get(id).setEstadoOff();
     this.listaEventos.get(id).setResultado(resultado);
     }
     */
    void updateUser(Apostador user) {
        this.listaUtilizadores.put(user.getEmail(), user);
    }
/*
    void avisaBookiesOdd(String email, int idEvento) {
        for (String em : this.listaUtilizadores.keySet()) {
            if (this.listaUtilizadores.get(em) instanceof Bookie) {
                Bookie b = (Bookie) this.listaUtilizadores.get(em);
                if (!b.getEmail().equals(email)) {
                    if (b.getEventos().containsKey(idEvento)) {
                        b.setNotificacaoOdd(idEvento);
                    }
                }
            }
        }
    }

  */

}
