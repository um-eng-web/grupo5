class User

  def initialize(email,password,nome)
    @nome=nome
    @password=password
    @email=email

  end

  def  getEmail
    @email;
  end

  def  setEmail=( email)
    @email = email;
  end

  def  getPassword
    @password;
end

  def  setPassword=(password)
    @password = password;
  end

  def  getNome
    @nome;
  end

  def  setNome=(nome)
    @nome = nome;
  end
end