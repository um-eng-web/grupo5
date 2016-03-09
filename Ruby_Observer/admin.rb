require_relative 'user'
class Admin < User
  def initialize (email,password,nome)
    super(email,password,nome)
  end
end