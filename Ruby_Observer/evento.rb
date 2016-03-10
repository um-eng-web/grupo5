require_relative 'odd'


class Evento
  attr_reader :data_init, :descricao, :estado, :id
  attr_writer :id

  #implements :Observable

  def initialize(id, descricao, data, odd1, odd2, empate, eq1, eq2)
    @id=id
    @descricao=descricao
    @data_init=data
    @nome_equipa1=eq1
    @nome_equipa2=eq2
    @estado=true
    @concluida=false
    @observers=Array.new
    @odd=Odd.new(odd1, empate, odd2)
  end

  def add_observer(o)
    @observers.insert(@observers.last, o)
  end

  def remove_observer(o)
    @observers.delete(o)
  end

  # falta o método notify_observer(resultado)

  def notify_observer_odd
    @observers.each { |o| o.update_odd(@id) }
  end


  def to_s
    #if @result
    # "ID=#{@id} | #{@home} VS #{@away} Odds(#{@homeodd}|#{@drawodd}|#{@awayodd}) | Resultado Final:#{@result} | Date #{@date}"
    #else
    "ID=#{@id} | #{@nome_equipa1} VS #{@nome_equipa2} Odds(#{@odd.odd_v}|#{@odd.odd_e}|#{@odd.odd_d}) Date #{@data_init}"
  end
end