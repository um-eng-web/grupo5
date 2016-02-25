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
public abstract class Observer {
     
    protected String nome; //nome do seguidor
    protected String msg; //mensagem
    //method to update the observer, used by subject
    public abstract void update(int code,String e1,String e2,float valor,String res);

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
