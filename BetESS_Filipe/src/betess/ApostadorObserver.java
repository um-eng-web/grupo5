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
public class ApostadorObserver extends Observer {

    public ApostadorObserver(String nome) {
        this.nome=nome;
        this.msg="";
    }

    //mensagens do apostador
    public void update(int code,String e1,String e2,float valor,String res) {
        switch(code){
            case 0:
                this.msg=this.msg.concat("O jogo "+e1+" vs "+e2+" sofreu alteração nas suas odds\n");
                break;
            case 1:
                this.msg=this.msg.concat("O jogo "+e1+" vs "+e2+" terminou! Ganhou "+valor+" BetESSCoins!\n");
                break;
            case 2:
               this.msg=this.msg.concat("O jogo "+e1+" vs "+e2+" foi cancelado e as suas apostas devolvidas\n");
                break;
            default:
                break;
        }
        
    }
    
}
