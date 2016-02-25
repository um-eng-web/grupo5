/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

/**
 *
 * @author nunomonteirodias1
 */
public class Aposta {
    private int idJogo;
    private String equipa1;
    private String equipa2;
    private float quantia;
    private float quantiaganha; //quanto ganhou na aposta, 0 se não ganhou
    private String resultado;
    private Odd odd; //odds de vitoria, empate e derrota atuais
    
    public Aposta(int id,String e1,String e2, float quantia,String resultado, Odd o){
        this.idJogo=id;
        this.equipa1=e1;
        this.equipa2=e2;
        this.quantia=quantia;
        this.resultado=resultado;
        this.odd=o;
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

    public Odd getOdd() {
        return odd;
    }

    public void setOdd(Odd odd) {
        this.odd = odd;
    }

    public float getQuantiaganha() {
        return quantiaganha;
    }

    public void setQuantiaganha(float quantiaganha) {
        this.quantiaganha = quantiaganha;
    }

    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public float getQuantia() {
        return quantia;
    }

    public void setQuantia(float quantia) {
        this.quantia = quantia;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
}
