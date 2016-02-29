require "./Odd"


class Aposta
  attr_reader :ganho
  attr_accessor :fechada, :resultado
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
  def cal_ganho

    case @resultado
      when 1 then if @resultado==@escolha
                    @ganho=@valor* @odd.odd_v
                  end
      when 2 then if @resultado==@escolha
                      @ganho=@valor* @odd.odd_d
                  end
      when 0 then if @resultado==@escolha
                    @ganho=@valor* @odd.odd_e
                  end
    end
  end




end
=begin
time = Time.new
apos= Aposta.new(1,1,1,10.33,2.2,3.3,0.2,time)
p apos.fechada
apos.fechada =true
p apos.fechada
=end

