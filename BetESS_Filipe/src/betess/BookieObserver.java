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
public class BookieObserver extends Observer {

    public BookieObserver(String nome) {
        this.nome=nome;
        this.msg="";
    }

    //mensagens do bookie
    public void update(int code,String e1,String e2,float valor,String res) {
        switch(code){
            case 0:
                this.msg=this.msg.concat("O jogo "+e1+" vs "+e2+" sofreu alteração nas suas odds\n");
                break;
            case 1:
                if(res.equals("1")){
                    this.msg=this.msg.concat("O jogo "+e1+" vs "+e2+" terminou! "+e1+" venceu!\n");
                }
                if(res.equals("x")){
                    this.msg=this.msg.concat("O jogo "+e1+" vs "+e2+" terminou! As equipas empataram!\n");
                }
                if(res.equals("2")){
                    this.msg=this.msg.concat("O jogo "+e1+" vs "+e2+" terminou! "+e2+" venceu!\n");
                }
                break;
            case 2:
                this.msg=this.msg.concat("O jogo "+e1+" vs "+e2+" foi cancelado\n");
                break;
            default:
                break;
        }
    }
    
}
