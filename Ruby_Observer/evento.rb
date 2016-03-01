class Evento

  implements :Observable

  def self.initialize(id,descricao,data,odd1,odd2,empate,eq1,eq2)
    @id=id
    @descricao=descricao
    @data_init=data
    @nome_equipa1=eq1
    @nome_equipa2=eq2
    @estado=true
    @concluida=false
    @observers=Array.new
    @odd=Odd.initialize(odd1,empate,odd2)
  end

  def add_observer(o)
    @observers.insert(@observers.last,o)
  end

  def remove_observer(o)
    @observers.delete(o)
  end

  # falta o método notify_observer(resultado)

  def notify_observer_odd()
    @observers.each {|o| o.update_odd(@id)}
  end

  def self.get_id
    return @id
  end
end