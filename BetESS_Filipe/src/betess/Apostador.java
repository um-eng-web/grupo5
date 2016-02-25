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
public class Apostador extends User {
    private int id;
    private float quantia_disp; //saldo da conta
    private ArrayList<Aposta> lista_aposta; //lista de apostas
    private ApostadorObserver ap; 

    public Apostador(String user, String pass, String mail, float quantia) {
        super(user, pass, mail);
        this.quantia_disp=quantia;
        this.lista_aposta=new ArrayList<>();   
        this.ap=new ApostadorObserver(this.getUsername());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQuantia_disp() {
        return quantia_disp;
    }

    public void setQuantia_disp(float quantia_disp) {
        this.quantia_disp = quantia_disp;
    }

    public ArrayList<Aposta> getLista_aposta() {
        return lista_aposta;
    }

    public void setLista_aposta(ArrayList<Aposta> lista_aposta) {
        this.lista_aposta = lista_aposta;
    }
    
    public void inc(float qt){
        this.quantia_disp+=qt;
    }

    public ApostadorObserver getAp() {
        return ap;
    }

    public void setAp(ApostadorObserver ap) {
        this.ap = ap;
    }

    
    
}
