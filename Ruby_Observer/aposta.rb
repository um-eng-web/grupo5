current_folder = File.expand_path('../', __FILE__) # get absolute directory
Dir["#{current_folder}**/*.rb"].each {|f| require f}



class Aposta

  attr_reader :ganho
  attr_accessor :fechada, :resultado

  def initialize(id,id_equipa,escolha,valor,odd_v,odd_e,odd_d,data)
    @id=id
    @id_equipa=id_equipa
    @escolha=escolha
    @valor = valor
    @odd = Odd.new(odd_v,odd_e,odd_d)
    @data=data
    @resultado
    @fechada = false
    @ganho
  end

  def get_id()
    @id
  end

  def get_id_evento()
    @id_equipa
  end

  def set_fechada()
    @fechada = true
  end

  def get_fechada()
    @fechada
  end

  def set_valor(v)
    @valor = v
  end

  def set_aposta_user(aposta)
    @escolha = aposta
  end

  def get_valor()
    @valor
  end

  def get_resultado_aposta()
    @resultado
  end

  def set_resultado(res)
    @resultado = res
  end

  def get_ganho()
    @ganho
  end

  def cal_ganho

    case @resultado
      when 1 then if @resultado==@escolha
                    @ganho=@valor* @odd.odd_v
                  end
      when 2 then if @resultado==@escolha
                      @ganho=@valor* @odd.odd_d
                  end
      when 0 then if @resultado==@escolha
                    @ganho=@valor* @odd.odd_e
                  end
    end
  end




end
=begin
time = Time.new
apos= Aposta.new(1,1,1,10.33,2.2,3.3,0.2,time)
p apos.fechada
apos.fechada =true
p apos.fechada
=end

