/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

/**
 *
 * @author ana almeida
 */
public class Aposta {

    private int id;
    private int idEvento;

    private double valor;
    private int resultado;
    private boolean fechada;
    private int apostaUser;
    private double ganho;
    private Odd odd;
  

    /**
     * CONSTRUTORES
     *
     * @param id
     * @param aposta
     */
    public Aposta(int id,int idE, int aposta, double valor, double oA, double oB, double oE) {
        this.id = id;
        this.apostaUser = aposta;
        this.ganho = 0.0;
        this.valor = valor;
        this.idEvento=idE;
        this.odd = new Odd(oA, oB, oE);
        fechada = false;
    }

    public Aposta(Aposta apos) {
        this.id = apos.getId();

        this.valor = apos.getValor();
        this.resultado = apos.getResultadoAposta();
    }

    /**
     * GETTER E SETTER
     */
    public int getId() {
        return this.id;
    }
    public int getIdEvento(){
        return this.idEvento;
    }

    public void setFechada() {
        this.fechada = true;
    }

    public boolean fechada() {
        return this.fechada;
    }

    public void setValor(double v) {
        this.valor = v;
    }
    /*
     public Odd getOdd(){
     return this.odd;
     }
     */

    public void setApostaUser(int aposta) {
        this.apostaUser = aposta;
    }

    public double getValor() {
        return this.valor;
    }

    public int getResultadoAposta() {
        return this.resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getResutado() {
        return this.resultado;
    }

    public double getGanho() {
        return this.ganho;
    }
    /*
     void setOdd(Odd odd) {
     System.out.println("ODD APOSTA");
     this.odd.setOddA(odd.getOddA());
     this.odd.setOddB(odd.getOddB());
     this.odd.setOddEmpate(odd.getOddEmpate());
     System.out.println("odd Aposta FIM###########");
     }
     */

    public void calGanho() {

        //this.resultado=resultado;
        this.setGanho();

    }

    /**
     * MÉTODOS
     */
    /**
     * Valor Ganho na Aposta
     *
     * @return
     */
    public void setGanho() {
        double res = 0.0;
        switch (resultado) {
            case (Main.EQUIPA_A):
                if (this.apostaUser == resultado) {
                    //res = this.valor * this.oddA;
                    res = this.valor * this.odd.getOddA();
                }
                break;
            case (Main.EQUIPA_B):
                if (this.apostaUser == resultado) {
                    //res = this.valor * this.oddB;
                    res = this.valor * this.odd.getOddB();
                }
                break;
            case (Main.EMPATE):
                if (this.apostaUser == resultado) {
                    res = this.valor * this.odd.getOddA();
                    //res = this.valor * this.oddEmpate;
                }
                break;
        }
        this.ganho = res;

        //System.out.println(this.getOdd().getOddEmpate());
    }

    /**
     * Adicionar Aposta
     */
    /**
     * Clone
     */
    /**
     * Equals public boolean equals (Object obj) { if (this == obj) return true;
     * if ((obj == null) || (this.getClass() != obj.getClass())) return false;
     *
     * Aposta apos = (Aposta) obj; return this.id == apos.getId() &&
     * this.equipaA.equals(apos.getEquipaA()) &&
     * this.equipaB.equals(apos.getEquipaB()) && this.estado == apos.getEstado()
     * && this.odd == apos.getOdd() && this.valor == apos.getValor() &&
     * this.resultado == apos.getResultadoAposta(); }
     */
    /**
     * ToString
     */
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("Id da Aposta: ").append(this.id).append("\n");

        return s.toString();
    }

}
