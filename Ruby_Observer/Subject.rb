module Subject
  def initialize
    @observers=[]
  end

  def add_observer(observer)
    @observers << observer
  end

  def delete_observer(observer)
    @observers.delete(observer)
  end
=begin
  def  notify_observers_resultado(tipo,info,resultado)
    @observers.each do |observer|
      observer.update(tipo,info,resultado)
    end
  end
=end
  def notify_observers(tipo,info,resultado)
    @observers.each do |observer|
      observer.update(tipo,info,resultado)
    end
  end
end