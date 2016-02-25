/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess;

/**
 *
 * @author FilipePinto
 */
public class Odd {
    
    private float odd_vitoria, odd_empate, odd_derrota;

    public Odd(float odd_vitoria, float odd_empate, float odd_derrota) {
        this.odd_vitoria = odd_vitoria;
        this.odd_empate = odd_empate;
        this.odd_derrota = odd_derrota;
    }

    public float getOdd_vitoria() {
        return odd_vitoria;
    }

    public void setOdd_vitoria(float odd_vitoria) {
        this.odd_vitoria = odd_vitoria;
    }

    public float getOdd_empate() {
        return odd_empate;
    }

    public void setOdd_empate(float odd_empate) {
        this.odd_empate = odd_empate;
    }

    public float getOdd_derrota() {
        return odd_derrota;
    }

    public void setOdd_derrota(float odd_derrota) {
        this.odd_derrota = odd_derrota;
    }
    
    
    
}
