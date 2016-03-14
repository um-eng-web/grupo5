

require_relative 'odd'

class Aposta

  attr_reader :ganho, :id_evento
  attr_accessor :fechada, :resultado

  def initialize(id,id_evento,escolha,valor,odd_v,odd_e,odd_d,data)
    @id=id
    @id_evento=id_evento
    @escolha=escolha
    @valor = valor
    @odd = Odd.new(odd_v,odd_e,odd_d)
    @data=data
    @resultado
    @fechada = false
    @ganho
  end

  def get_id
    @id
  end

  def self.get_id_evento
    id_evento
  end

  def set_fechada
    @fechada = true
  end

  def set_valor(v)
    @valor = v
  end

  def set_aposta_user(aposta)
    @escolha = aposta
  end

  def get_valor
    @valor
  end

  def get_resultado_aposta
    @resultado
  end

  def set_resultado(res)
    @resultado = res
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

