class CalcolaPercento

  attr_accessor :percentuale, :importo

  def percento?
    percentuale * importo / 100
  end

end
