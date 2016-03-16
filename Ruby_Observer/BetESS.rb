require_relative 'Admin'
require_relative 'Bookie'
require_relative 'Apostador'
require_relative 'Evento'


class BetESS
  def initialize
    @users = Hash.new
    #@users["admin"] = Admin.new('admin@g.com', 'pass', 'zeArtolas')
    @eventos = Hash.new
  end

  def exist_user(email)
    (@users[email]) ? true : false
  end

  def get_user(email)
    @users[email]
  end


  def registar_bookie(email, password, nome)
    return nil if @users[email]
    bookie = Bookie.new(nome, password, email)
    @users[email] = bookie

  end


  def registar_admin(email, password, nome)
    admin = Admin.new(nome, password, email)
    @users[email] = admin


  end

  def registar_apostador(email, password, nome, valor)
    apos = Apostador.new(nome, password, email, valor)
    @users[email] = apos
  end


  def fecha_evento(id)
    @eventos[id.to_i].estado=false
  end

  def concluir_evento(id, resultado)
    @eventos[id.to_i].set_resultado(resultado)
    @eventos[id.to_i].notify_observers_resultado(resultado)

  end

  def add_evento(evento, bookie)
    #bookie = user = @users[bookiemail]
    if !bookie || !bookie.is_a?(Bookie)
    else
      id = @eventos.length
      evento.id=id
      evento.add_observer(bookie)
      bookie.novo_evento(evento.id)
      @eventos[id.to_i] = evento


    end
  end


  def set_odd_evento(id, odd_v, odd_e, odd_d)
    @eventos[id.to_i].set_odd(odd_v, odd_e, odd_d)
    @eventos[id.to_i].notify_observer_odd
  end

  def exist_evento(id)
    return @eventos.include?(id.to_i)
  end

  def regista_interesse(id, bookie)
    if @eventos[id.to_i].estado
    then
      @eventos[id.to_i].add_observer(bookie)
      bookie.add_interesse(id)
      return true
    else
      return false
    end
  end

  def retira_interesse(id, bookie)
    if @eventos[id.to_i].estado
    then
      @eventos[id.to_i].remove_observer(bookie)
      bookie.del_interesse(id)
      return true
    else
      return false
    end
  end

  def get_eventos
    @eventos.values
  end


end
