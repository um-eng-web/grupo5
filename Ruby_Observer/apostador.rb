require_relative 'user'
require_relative 'aposta'
require_relative 'evento'
require 'time'
require_relative 'self_observer'

require 'set'

class Apostador < User
  include SelfObserver
  #implements :SelfObserver

  attr_accessor :nome, :valor, :not_odd, :lista_apostas, :contador_id_aposta

  def initialize(email, password, nome, valor)
    super(email, password, nome)
    @valor=valor
    @contador_id_aposta = 0
    @lista_apostas = Hash.new
    @not_odd = Set.new
  end

  def add_contador_aposta
    @contador_id_aposta += 1
  end

  #def add_aposta(id_aposta,aposta)
  def add_aposta(aposta)
    @lista_apostas[aposta.get_id] = aposta
    add_contador_aposta
  end

  def contains_event(id)
    flag = false
    @lista_apostas.keys.each do |id_aposta|
      flag = true if id_aposta.eql?(id)
    end
    return flag
  end

  def get_apostas_by_id_event(evento)
    apostas = Set.new
    @lista_apostas.keys.each do |id_aposta|
      apostas.add(@lista_apostas[id_aposta]) if id_aposta.eql?(evento)
    end
    return apostas
  end

  def set_resultado_aposta(id_aposta, resultado_aposta)
    @lista_apostas[id_aposta].set_resultado(resultado_aposta)
    @lista_apostas[id_aposta].cal_ganho
  end

  def limpa_noficacoes_odd
    @not_odd = Set.new
  end

  def update(id, info, resultado)

    self.get_apostas_by_id_event(id.to_i).each do |apos|
      apos.set_fechada
      apos.set_resultado(resultado)
      apos.cal_ganho

      @valor += apos.ganho.to_f

    end
  end


  def update_odd(id_evento)
    @not_odd.add(id_evento)
  end

  def set_valor(valor)
    @valor = valor
  end
end
