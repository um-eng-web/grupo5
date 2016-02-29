require './user'
class Apostador < User
  def initialize(email,password,nome,valor)
    super(email,password,nome)
    @valor=valor

  end


end