class Fit4Ruby

  def initialize
    @number_of_succeded_tests = 0
    @number_of_failed_tests = 0
  end

  def load(file_name, class_name)
    require file_name
    @class_inst = eval('::'+class_name+'.new')
  end

  def nameLoadedClass?
    @class_inst.class.to_s
  end

  def set(name, value)
    @class_inst.send("#{name}=".to_sym, value)
  end

  def call(name)
    @class_inst.send(name.to_sym)
  end

  def parse(csv)
    lines = csv.split(/\n/)
    pick_header_from_lines(lines)
    pick_methods_from_lines(lines)
    pick_tests_from_lines(lines)
  end

  def pick_tests_from_lines(lines)
    @tests = lines
  end

  def pick_methods_from_lines(lines)
    @methods = lines.shift.strip.split(/\s*,\s*/)
  end

  def pick_header_from_lines(lines)
    file_name = lines.shift.strip
    class_name = lines.shift.strip
    load file_name, class_name  
  end

  def number_of_tests?
    @tests.size
  end

  def run
    @tests.each do | test |
      @number_of_succeded_tests += 1 if test_succeded test
    end
    @number_of_failed_tests = @tests.size - @number_of_succeded_tests
  end

  def is_callable?(method_name)
    method_name[-1].chr == '?'
  end

  def test_succeded(values_as_string)
    result = false
    values = values_as_string.split(/\s*,\s*/)
    values.each_with_index do | value, index |
      actual_method = @methods[index]
      if is_callable?(actual_method)
        result = call(actual_method) == value.to_i
      else
        set(actual_method, value.to_i)
      end
    end
    result
  end

  def number_of_succeded_tests?
    @number_of_succeded_tests
  end

  def number_of_failed_tests?
    @number_of_failed_tests
  end

end
