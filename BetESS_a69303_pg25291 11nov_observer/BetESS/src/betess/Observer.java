/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;


public interface Observer {
    public void update(int idEvento,String evento,int resultado);
    public void updateOdd(int idEvento);
    
}
