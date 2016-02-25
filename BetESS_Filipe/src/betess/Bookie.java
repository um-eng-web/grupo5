/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import java.util.ArrayList;

/**
 *
 * @author FilipePinto
 */
public class Bookie extends User{
    
    private ArrayList<Jogo> jogos_interesse; //jogos que o bookie segue
    private ArrayList<Jogo> jogos_criados; //jogos em que revela interesse
    private BookieObserver bk;

    public Bookie(String user, String pass, String mail) {
        super(user, pass, mail);
        this.jogos_interesse=new ArrayList<>();
        this.jogos_criados=new ArrayList<>();
        this.bk=new BookieObserver(this.getUsername());
    }

    public ArrayList<Jogo> getJogos_interesse() {
        return jogos_interesse;
    }

    public void setJogos_interesse(ArrayList<Jogo> jogos_interesse) {
        this.jogos_interesse = jogos_interesse;
    }

    public ArrayList<Jogo> getJogos_criados() {
        return jogos_criados;
    }

    public void setJogos_criados(ArrayList<Jogo> jogos_criados) {
        this.jogos_criados = jogos_criados;
    }

    public BookieObserver getBk() {
        return bk;
    }

    public void setBk(BookieObserver bk) {
        this.bk = bk;
    }

}
