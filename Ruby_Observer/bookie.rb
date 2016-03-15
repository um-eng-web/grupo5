require_relative 'user'

require 'set'
require_relative 'self_observer'

class Bookie < User
  include SelfObserver
  attr_accessor :not_odd, :resultados_eventos, :eventos_interesse
  attr_reader :eventos_criados

  def initialize(nome, password, email)
    super(email, password, nome)
    @not_odd = Set.new
    @resultados_eventos = Hash.new
    @eventos_criados = Set.new
    @eventos_interesse = Set.new


  end

  def addInteresse(id)
    @eventos_interesse.add(id)


  end

  def semEventosCriados?
    return @eventos_criados.empty?
  end

  def novo_evento(id_evento)
    @eventos_criados.add(id_evento)

  end

  def criou_evento(id)
    return @eventos_criados.include?(id)

  end

  def update(id, info, resultado)

    @resultados_eventos[id]=info

end

def update_odd(id)
  @not_odd.add(id)
end

def limpaNoficacoesOdd
  @not_odd = Set.new
end


end


#tbookie = Bookie.new("Ze",1234,"ze@gmail.com")

#tbookie.novo_evento(1)
#tbookie.novo_evento(2)

#tbookie.eventos_criados.each { |x| puts "valor dentro do set = #{x} "}