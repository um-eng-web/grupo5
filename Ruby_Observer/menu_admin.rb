class Menu_Admin

  def initialize(admin)
    @admin=admin
    @flag = true
  end

  def start
    p '************************************************'
    p '*                    ADMIN                    *'
    p '************************************************'
    p 'Escolha a opção'
    p '1-Registar Bookie'
    p '2-Fechar Aposta'
    p '3-Concluir Aposta'
    p '4-Sair'
    opt = gets.chomp

    case opt
      when '1' then
        p '************************************************'
        registar_bookie
      when '2' then
        p '************************************************'
        fechar_aposta
      when '3' then
        p '************************************************'
        concluir_aposta
      when '4' then
        p '************************************************'
        @flag = false

    end



  end
end