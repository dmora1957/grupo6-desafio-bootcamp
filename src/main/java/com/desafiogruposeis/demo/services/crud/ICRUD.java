package com.desafiogruposeis.demo.services.crud;

import java.util.List;

public interface ICRUD <DTO>{
    DTO registrar(DTO dto);

    DTO modificar(DTO dto);

    void eliminar(Integer id);

    DTO listarId(Integer id);

    List<DTO> listarTodos();
}
