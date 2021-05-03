package com.desafiogruposeis.demo.services.crud.impl;

import com.desafiogruposeis.demo.dto.RegistroSalarioEmpleadoDTO;
import com.desafiogruposeis.demo.models.Empleado;
import com.desafiogruposeis.demo.models.RegistroSalarioEmpleado;
import com.desafiogruposeis.demo.repositories.RegistroSalarioEmpleadoRepository;
import com.desafiogruposeis.demo.services.crud.IRegistroSalarioEmpleadoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroSalarioEmpleadoServiceImpl implements IRegistroSalarioEmpleadoService {

    private RegistroSalarioEmpleadoRepository registroSalarioEmpleadoRepository;
    private final ModelMapper modelMapper;

    public RegistroSalarioEmpleadoServiceImpl(RegistroSalarioEmpleadoRepository registroSalarioEmpleadoRepository, ModelMapper modelMapper) {
        this.registroSalarioEmpleadoRepository = registroSalarioEmpleadoRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public RegistroSalarioEmpleadoDTO registrar(RegistroSalarioEmpleadoDTO registroSalarioEmpleadoDTO) {

        if (registroSalarioEmpleadoDTO.getUnEmpleado()==null){
            throw new IllegalArgumentException("Registrar no permitido, debe de poseer un empleado");
        }

        RegistroSalarioEmpleado nuevoRegistroEmpleado = modelMapper.map(registroSalarioEmpleadoDTO,RegistroSalarioEmpleado.class);

        registroSalarioEmpleadoDTO.setId(registroSalarioEmpleadoRepository.save(nuevoRegistroEmpleado).getId());

        return registroSalarioEmpleadoDTO;
    }

    @Override
    @Transactional
    public RegistroSalarioEmpleadoDTO modificar(RegistroSalarioEmpleadoDTO registroSalarioEmpleadoDTO) {
        if (registroSalarioEmpleadoDTO.getUnEmpleado()==null){
            throw new IllegalArgumentException("Modificar no permitido, debe de poseer un empleado");
        }
        registroSalarioEmpleadoDTO.setId(registroSalarioEmpleadoRepository.save(modelMapper.map(registroSalarioEmpleadoDTO,RegistroSalarioEmpleado.class)).getId());
        return registroSalarioEmpleadoDTO;
    }

    @Override
    public void eliminar(Integer id) {
        Optional<RegistroSalarioEmpleado> opt = registroSalarioEmpleadoRepository.findById(id);
        if (!opt.isPresent()) {
            throw new NoSuchElementException("No existe el registro de salario del empleado con el id: " + id);
        }
        registroSalarioEmpleadoRepository.deleteById(id);
    }

    @Override
    public RegistroSalarioEmpleadoDTO listarId(Integer id) {
        Optional<RegistroSalarioEmpleado> opt = registroSalarioEmpleadoRepository.findById(id);
        return modelMapper.map(opt.get(), RegistroSalarioEmpleadoDTO.class);
    }

    @Override
    public List<RegistroSalarioEmpleadoDTO> listarTodos() {
        List<RegistroSalarioEmpleadoDTO> registrosSalariosEmpleadoDTO = registroSalarioEmpleadoRepository.findAll()
                .stream()
                .map(registroSalarioEmpleado -> modelMapper.map(registroSalarioEmpleado, RegistroSalarioEmpleadoDTO.class))
                .collect(Collectors.toList());
        return registrosSalariosEmpleadoDTO;
    }


    @Override
    public List<RegistroSalarioEmpleadoDTO> buscarRegistrosSalariosEmpleadosPorEmpleado(Empleado unEmpleado) {
        List<RegistroSalarioEmpleadoDTO> registrosSalariosEmpleadoDTO = registroSalarioEmpleadoRepository.findByUnEmpleado(unEmpleado)
                .stream()
                .map(registroSalarioEmpleado -> modelMapper.map(registroSalarioEmpleado, RegistroSalarioEmpleadoDTO.class))
                .collect(Collectors.toList());
        return registrosSalariosEmpleadoDTO;
    }
}
