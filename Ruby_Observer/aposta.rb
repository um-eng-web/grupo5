require_relative 'odd'

class Aposta

  attr_reader :ganho, :id_evento, :ganho
  attr_accessor :fechada, :resultado

  def initialize(id, id_evento, escolha, valor, odd_v, odd_e, odd_d, data)
    @id=id
    @id_evento=id_evento
    @escolha=escolha
    @valor = valor
    @odd = Odd.new(odd_v, odd_e, odd_d)
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


    case @resultado.to_i
      when 1 then
        if @resultado==@escolha
          @ganho=@valor.to_f* @odd.odd_v.to_f
        end
      when 2 then
        if @resultado==@escolha
          @ganho=@valor.to_f* @odd.odd_d.to_f
        end
      when 0 then
        if @resultado==@escolha
          @ganho=@valor.to_f* @odd.odd_e.to_f
        end
    end
  end


end


