package com.desafiogruposeis.demo.services.crud;

import com.desafiogruposeis.demo.dto.RegistroSalarioEmpleadoDTO;
import com.desafiogruposeis.demo.models.Empleado;

import java.util.List;

public interface IRegistroSalarioEmpleadoService extends ICRUD<RegistroSalarioEmpleadoDTO>{
    List<RegistroSalarioEmpleadoDTO> buscarRegistrosSalariosEmpleadosPorEmpleado(Empleado unEmpleado);
}
