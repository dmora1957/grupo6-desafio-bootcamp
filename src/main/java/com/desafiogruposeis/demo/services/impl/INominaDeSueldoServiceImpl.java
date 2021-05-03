package com.desafiogruposeis.demo.services.impl;

import com.desafiogruposeis.demo.dto.EmpleadoDTO;
import com.desafiogruposeis.demo.dto.RegistroSalarioEmpleadoDTO;
import com.desafiogruposeis.demo.dto.request.IncrementoSalarioEmpleadoRequestDTO;
import com.desafiogruposeis.demo.dto.response.IncrementoSalarioEmpleadoResponseDTO;
import com.desafiogruposeis.demo.enums.TipoExperiencia;
import com.desafiogruposeis.demo.services.INominaDeSueldoService;
import com.desafiogruposeis.demo.services.crud.IEmpleadoService;
import com.desafiogruposeis.demo.services.crud.IRegistroSalarioEmpleadoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class INominaDeSueldoServiceImpl implements INominaDeSueldoService<IncrementoSalarioEmpleadoRequestDTO, IncrementoSalarioEmpleadoResponseDTO> {

    private IEmpleadoService empleadoService;
    private IRegistroSalarioEmpleadoService registroSalarioEmpleadoService;

    private final ModelMapper modelMapper;


    public INominaDeSueldoServiceImpl(IEmpleadoService empleadoService, IRegistroSalarioEmpleadoService registroSalarioEmpleadoService, ModelMapper modelMapper) {
        this.empleadoService = empleadoService;
        this.registroSalarioEmpleadoService = registroSalarioEmpleadoService;
        this.modelMapper = modelMapper;
    }


    @Override
    public IncrementoSalarioEmpleadoResponseDTO incrementarSalario(IncrementoSalarioEmpleadoRequestDTO incrementoSalarioEmpleadoRequestDTO) {
        EmpleadoDTO empleado = this.empleadoService.buscarEmpleadoPorDNI(incrementoSalarioEmpleadoRequestDTO.getDni());
        RegistroSalarioEmpleadoDTO registroSalarioEmpleadoDTO = new RegistroSalarioEmpleadoDTO();

        IncrementoSalarioEmpleadoResponseDTO incrementoSalarioEmpleadoResponseDTO = new IncrementoSalarioEmpleadoResponseDTO();

        if (empleado!=null ) {
            //  solo los empleados con más de 5 años de experiencia pueden obtener un aumento
            if (empleado.getAntiguedad() > 5) {
                empleado.setSalarioActual(empleado.getSalarioActual().add(incrementoSalarioEmpleadoRequestDTO.getMonto()));


                registroSalarioEmpleadoDTO.setUnEmpleado(this.empleadoService.registrar(empleado));

                registroSalarioEmpleadoDTO.setFecha(incrementoSalarioEmpleadoRequestDTO.getFecha());

                registroSalarioEmpleadoDTO.setMonto(incrementoSalarioEmpleadoRequestDTO.getMonto());

                registroSalarioEmpleadoService.registrar(registroSalarioEmpleadoDTO);

                incrementoSalarioEmpleadoResponseDTO.setUnTipoExperiencia(TipoExperiencia.CON_EXPERIENCIA);

            }

            incrementoSalarioEmpleadoResponseDTO.setUnEmpleado(empleado);
        }
        return incrementoSalarioEmpleadoResponseDTO;
    }
}
