class Odd


  attr_reader :odd_e, :odd_d, :odd_v

  def initialize(odd_vitoria, odd_empate, odd_derrota)
    @odd_v=odd_vitoria
    @odd_e=odd_empate
    @odd_d=odd_derrota
  end

end