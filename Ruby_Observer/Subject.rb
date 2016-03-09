
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

  def notify_observer(resultado)
    @observers.each do |observer|
      observer.update_odd(resultado)
    end
  end
  def notify_observers_odd
    @observers.each do |observer|
      observer.update(self)
    end
  end

end