/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author nunomonteirodias1
 */
public class Jogo implements Subject {
    private int id;
    private String equipa1;
    private String equipa2;
    private String estado; //estado atual do jogo
    private String resultado;
    private Date data;
    private ArrayList<String> lista_apostadores;
    private ArrayList<Odd> lista_odds;
    private String bookie;
    private ArrayList<Observer> observers=new ArrayList<>();
       
   
    public Jogo(int id, String e1,String e2, float odd_vitoria, float odd_empate, float odd_derrota, String estado,String res, Date data,String bookie) {
        this.id=id;
        this.equipa1 = e1;
        this.equipa2 = e2;
        this.estado = estado; 
        this.resultado=res;
        this.data=data;
        this.lista_apostadores=new ArrayList<>();
        this.lista_odds=new ArrayList<>();
        this.lista_odds.add(new Odd(odd_vitoria,odd_empate,odd_derrota));
        this.bookie=bookie;
        this.observers=new ArrayList<>();
    }
    
    public boolean jogoEqual(Jogo j1){
        if(j1.getData().equals(this.getData()) && j1.getId()==this.getId() && j1.getEquipa1().equals(this.getEquipa1()) && j1.getEquipa2().equals(this.getEquipa2()) && j1.getResultado().equals(this.getResultado()) && j1.isEstado().equals(this.isEstado())) return true;
        else return false;
    }
    
    public boolean mesmoJogo(Jogo j1){
        if (j1.getData().equals(this.getData()) && j1.getEquipa1().equals(this.getEquipa1()) && j1.getEquipa2().equals(this.getEquipa2()) && j1.getLista_odds().get(j1.getLista_odds().size()-1).getOdd_vitoria()==this.getLista_odds().get(this.getLista_odds().size()-1).getOdd_vitoria() && j1.getLista_odds().get(j1.getLista_odds().size()-1).getOdd_empate()==this.getLista_odds().get(this.getLista_odds().size()-1).getOdd_empate() && j1.getLista_odds().get(j1.getLista_odds().size()-1).getOdd_derrota()==this.getLista_odds().get(this.getLista_odds().size()-1).getOdd_derrota() && j1.isEstado().equals(this.isEstado())) return true;
        else return false;
    }
    
   //consoante o novo estado do jogo, consoante o código de notificação atribuído para notificar os observadores
   public void setState(String state,float valor) {
      this.estado = state;
      if(this.estado.equals("editado")) notifyAllObservers(0,valor,this.resultado);
      if(this.estado.equals("concluido")) notifyAllObservers(1,valor,this.resultado);
      if(this.estado.equals("cancelado")) notifyAllObservers(2,valor,this.resultado);
   }

   //adiciona um novo observador
   public void attach(Observer observer){
      observers.add(observer);		
   }
   
   public String getState(){
       return this.estado;
   }

   //notifica observadores
   public void notifyAllObservers(int code,float valor,String resultado){
      for (Observer observer : observers) {
         observer.update(code,this.equipa1,this.equipa2,valor,resultado);
      }
   } 

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jogo other = (Jogo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.equipa1, other.equipa1)) {
            return false;
        }
        if (!Objects.equals(this.equipa2, other.equipa2)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.lista_apostadores, other.lista_apostadores)) {
            return false;
        }
        if (!Objects.equals(this.lista_odds, other.lista_odds)) {
            return false;
        }
        return true;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public ArrayList<Odd> getLista_odds() {
        return lista_odds;
    }

    public void setLista_odds(ArrayList<Odd> lista_odds) {
        this.lista_odds = lista_odds;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getLista_apostadores() {
        return lista_apostadores;
    }

    public void setLista_apostadores(ArrayList<String> lista_apostadores) {
        this.lista_apostadores = lista_apostadores;
    }
    
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getEquipa1() {
        return equipa1;
    }

    public void setEquipa1(String equipa1) {
        this.equipa1 = equipa1;
    }

    public String getEquipa2() {
        return equipa2;
    }

    public void setEquipa2(String equipa2) {
        this.equipa2 = equipa2;
    }

    public String isEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBookie() {
        return bookie;
    }

    public void setBookie(String bookie) {
        this.bookie = bookie;
    }    

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }
    
}
