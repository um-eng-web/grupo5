require_relative 'user'

require 'set'
require 'observer'

class Bookie < User
  attr_accessor :eventos_criados

  def initialize(nome, password, email)
    super(email, password, nome)
    @eventos_criados = Set.new
  end


  def novo_evento(id_evento)
    @eventos_criados.add(id_evento)
  end

  def criou_evento(id)
    return @eventos_criados.include?(id)

  end


end





#tbookie = Bookie.new("Ze",1234,"ze@gmail.com")

#tbookie.novo_evento(1)
#tbookie.novo_evento(2)

#tbookie.eventos_criados.each { |x| puts "valor dentro do set = #{x} "}