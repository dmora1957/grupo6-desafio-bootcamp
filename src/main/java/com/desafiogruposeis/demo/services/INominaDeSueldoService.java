package com.desafiogruposeis.demo.services;

public interface INominaDeSueldoService<RequestDTO, ResponseDTO>{
    ResponseDTO incrementarSalario(RequestDTO dto);
}
