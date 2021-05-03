package com.desafiogruposeis.demo.services.crud;

import com.desafiogruposeis.demo.dto.EmpleadoDTO;

public interface IEmpleadoService extends ICRUD<EmpleadoDTO>{

    EmpleadoDTO buscarEmpleadoPorDNI(String dni);
}
