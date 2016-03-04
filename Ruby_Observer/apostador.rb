current_folder = File.expand_path('../', __FILE__) # get absolute directory
Dir["#{current_folder}**/*.rb"].each {|f| require f}

require 'hash'
require 'set'

class Apostador < User

  implements :Observer

  def initialize(email,password,nome,valor)
    super(email,password,nome)
    @valor=valor
    @contador_id_aposta = 0
    @lista_apostas = Hash.new
    @notificacoes_odd = Set.new
  end

  def get_valor()
    @valor
  end

  def set_valor(v)
    @valor = v
  end

  def add_contador_aposta()
    @contador_id_aposta += 1
  end

  def get_contador_aposta()
    @contador_id_aposta
  end

  def add_aposta(id_aposta,aposta)
    @lista_apostas[aposta.get_id()] = aposta
    add_contador_aposta()
  end

  #def contains_event(id)

  #def get_apostas_by_id_event(evento)

  def get_ganho_aposta(aposta)
    aposta.get_ganho()
  end

  def get_listas_apostas()
    @lista_apostas
  end

  #def set_resultado_aposta(id_aposta,resultado_aposta)

  def get_notificacoes_odd()
    @notificacoes_odd
  end

  def set_notificacoes_odd(id_evento)
    @notificacoes_odd.add(id_evento)
  end

  def limpa_notificacoes_odd()
    @notificacoes_odd = Set.new
  end

  #def update(id_evento,evento,resultado)

  def update_odd(id_evento)
    @notificacoes_odd.add(id_evento)
  end
end