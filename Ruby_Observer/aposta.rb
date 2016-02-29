require "./Odd"


class Aposta
  attr_accessor :fechada
  def initialize(id,id_equipa,escolha,valor,odd_v,odd_e,odd_d,data)
    @id=id
    @id_equipa=id_equipa
    @escolha=escolha
    @valor = valor
    @odd = Odd.new(odd_v,odd_e,odd_d)
    @data=data
    @resultado
    @fechada = false
    @ganho

  end
  



end
=begin
time = Time.new
apos= Aposta.new(1,1,1,10.33,2.2,3.3,0.2,time)
p apos.fechada
apos.fechada =true
p apos.fechada
=end

