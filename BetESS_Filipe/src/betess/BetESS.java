/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author nunomonteirodias1
 */
public class BetESS {
    private TreeMap<String,User> lista_utilizadores;
    private ArrayList<Jogo> lista_jogos;
    private TreeMap<String,User> bookies;
    private static Menu m;

    public TreeMap<String, User> getLista_utilizadores() {
        return lista_utilizadores;
    }

    public void setLista_utilizadores(TreeMap<String, User> lista_utilizadores) {
        this.lista_utilizadores = lista_utilizadores;
    }

    public ArrayList<Jogo> getLista_jogos() {
        return lista_jogos;
    }

    public void setLista_jogos(ArrayList<Jogo> lista_jogos) {
        this.lista_jogos = lista_jogos;
    }
    
    public BetESS(){
        this.lista_utilizadores=new TreeMap<>();
        this.lista_jogos=new ArrayList<>();
        this.bookies=new TreeMap<>();
        this.m=new Menu();
    }

    public static Menu getM() {
        return m;
    }

    public static void setM(Menu m) {
        BetESS.m = m;
    }

    public TreeMap<String, User> getBookies() {
        return bookies;
    }

    public void setBookies(TreeMap<String, User> bookies) {
        this.bookies = bookies;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        BetESS novo=new BetESS();
        // TODO code application logic here
        Admin admin=new Admin("admin","1234","admin@betess.com");
        Bookie b=new Bookie("bookie","1234","bookie@betess.com");
        Bookie b1=new Bookie("bookie2","1234","bookie@betess.com");
        Apostador ap=new Apostador("nuno","1234","nuno@betess.com",100);
        novo.lista_utilizadores.put("admin", admin);
        novo.lista_utilizadores.put("bookie", b);
        novo.lista_utilizadores.put("bookie2", b1);
        novo.lista_utilizadores.put("nuno",ap);
        novo.bookies.put("bookie", b);
        novo.bookies.put("bookie2", b1);
        m.menuInicial(novo);
        
    }
    
}
