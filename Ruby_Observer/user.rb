class User

  attr_reader :nome, :password, :email

  def initialize(email, password, nome)
    @nome=nome
    @password=password
    @email=email

  end

  def get_email
    @email;
  end

  def set_email=(email)
    @email = email;
  end

  def get_password
    @password;
  end

  def set_password=(password)
    @password = password;
  end

  def get_nome
    @nome;
  end

  def set_nome=(nome)
    @nome = nome;
  end
end