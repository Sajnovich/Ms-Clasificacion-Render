package com.biblioteca.clasificacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.biblioteca.clasificacion.dto.ClasificacionRequestDTO;
import com.biblioteca.clasificacion.dto.ClasificacionResponseDTO;
import com.biblioteca.clasificacion.model.Clasificacion;
import com.biblioteca.clasificacion.repository.ClasificacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class ClasificacionService {
    @Autowired
    private ClasificacionRepository clasificacionRepository;
    public List<ClasificacionResponseDTO> obtenerTodas() {
        return clasificacionRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }
    public Optional<ClasificacionResponseDTO> obtenerPorId(Long id) {
        return clasificacionRepository.findById(id).map(this::mapToDTO);
    }
    public List<ClasificacionResponseDTO> obtenerPorEdadMinima(Integer edadMinima) {
        return clasificacionRepository.findByEdadMinima(edadMinima).stream()
                .map(this::mapToDTO)
                .toList();
    }
    public List<ClasificacionResponseDTO> obtenerPermitidasParaEdad(Integer edad) {
        return clasificacionRepository.findByEdadMinimaLessThanEqual(edad).stream()
                .map(this::mapToDTO)
                .toList();
    }
    @Transactional
    public ClasificacionResponseDTO crear(ClasificacionRequestDTO dto) {
        Clasificacion clasificacion = new Clasificacion(
                null,
                dto.getPegi(),
                dto.getEsrb(),
                dto.getEdadMinima(),
                dto.getNombre(),
                dto.getDescripcion()
        );
        return mapToDTO(clasificacionRepository.save(clasificacion));
    }
    @Transactional
    public Optional<ClasificacionResponseDTO> actualizar(Long id, ClasificacionRequestDTO dto) {
        return clasificacionRepository.findById(id).map(clasificacion -> {
            clasificacion.setPegi(dto.getPegi());
            clasificacion.setEsrb(dto.getEsrb());
            clasificacion.setEdadMinima(dto.getEdadMinima());
            clasificacion.setNombre(dto.getNombre());
            clasificacion.setDescripcion(dto.getDescripcion());
            return mapToDTO(clasificacionRepository.save(clasificacion));
        });
    }
    @Transactional
    public void eliminar(Long id) {
        if (!clasificacionRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe una clasificacion con id " + id);
        }
        clasificacionRepository.deleteById(id);
    }
    private ClasificacionResponseDTO mapToDTO(Clasificacion clasificacion) {
        return new ClasificacionResponseDTO(
                clasificacion.getId(),
                clasificacion.getPegi(),
                clasificacion.getEsrb(),
                clasificacion.getEdadMinima(),
                clasificacion.getNombre(),
                clasificacion.getDescripcion()
        );
    }
}
