require_relative 'odd'
require 'observer'


class Evento
  include Observable

  attr_reader :data_init, :descricao
  attr_accessor :id,:estado,:concluida


  def initialize(id, descricao, data, odd1, odd2, empate, eq1, eq2)
    @id=id
    @descricao=descricao
    @data_init=data
    @nome_equipa1=eq1
    @nome_equipa2=eq2
    @estado=true
    @concluida=false
    @odd=Odd.new(odd1, empate, odd2)
  end

  def add_observer_evento(o)
    add_observer(o)
  end

  def remove_observer(o)
    remove_observer(o)
  end

  def set_resultado(resultado)
    @resultado = resultado
    @concluida=true

  end

  # falta o método notify_observer(resultado)

  def notify_observers_resultado(resultado)
    case resultado
      when '0' then res="Empate"
      when '1' then res=@nome_equipa1
      else
        res=@nome_equipa2
    end
    res = self.to_s + " " + res
    notify_observers('res',res)



  end

  def notify_observer_odd
    notify_observers('odd',@id)
  end

  def set_odd(odd_v,odd_e,odd_d)
    @odd= Odd.new(odd_v,odd_e,odd_d)
  end

  def to_s

    "ID=#{@id} | #{@nome_equipa1} VS #{@nome_equipa2} Odds(#{@odd.odd_v}|#{@odd.odd_e}|#{@odd.odd_d}) Date #{@data_init} Aberta=>#{@estado} Concluida=>#{@concluida} "
  end
end