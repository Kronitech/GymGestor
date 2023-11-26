package co.edu.ufps.gimnasio.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.gimnasio.model.dto.UsuarioDTO;
import co.edu.ufps.gimnasio.model.dto.UsuarioMembresiaDTO;
import co.edu.ufps.gimnasio.model.entity.Entrenador;
import co.edu.ufps.gimnasio.model.entity.Membresia;
import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;
import co.edu.ufps.gimnasio.repository.EntrenadorRepository;
import co.edu.ufps.gimnasio.repository.MembresiaRepository;
import co.edu.ufps.gimnasio.repository.UsuarioMembresiaRepository;
import co.edu.ufps.gimnasio.repository.UsuarioReporitory;
import co.edu.ufps.gimnasio.service.UsuarioMembresiaService;

@Service
public class UsuarioMembresiaServiceImpl implements UsuarioMembresiaService {

	@Autowired
	UsuarioMembresiaRepository usuarioMembresiaRepository;
	@Autowired
	MembresiaRepository membresiaRepository;
	@Autowired
	EntrenadorRepository entrenadorReposiotry;
	@Autowired
	UsuarioReporitory usuarioReporitory;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<UsuarioMembresia> lista() {
		return usuarioMembresiaRepository.findAll();
	}

	@Override
	public UsuarioMembresia findById(Integer id) {
		Optional<UsuarioMembresia> usuarioMembresia = usuarioMembresiaRepository.findById(id);
		if (usuarioMembresia.isPresent()) {
			return usuarioMembresia.get();
		}
		return null;
	}

	@Override
	public UsuarioMembresia saveUsuarioMembresia(UsuarioMembresia usuarioMembresia) {
		Date fechaInicio = new Date();
		Date fechaRegistro=new Date ();
		
		List<UsuarioMembresia>membresiasActivas=membresiasActivas(usuarioMembresia.getUsuarioId());
		if(!membresiasActivas.isEmpty()) {
			int n=membresiasActivas.size();
			
			fechaInicio=membresiasActivas.get(n-1).getFechaFin();
			System.err.println(n+" fecha Inicio"+fechaInicio);
		}
		Optional<Membresia> membresia=membresiaRepository.findById(usuarioMembresia.getMembresiaId());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaInicio);
		calendar.add(Calendar.DAY_OF_MONTH, 30); // Agrega 30 días a la fecha de inicio
		Date fechaFin = calendar.getTime();
		usuarioMembresia.setFechaInicio(fechaInicio);
		usuarioMembresia.setFechaFin(fechaFin);
		usuarioMembresia.setFechaRegistro(fechaRegistro);
		usuarioMembresia.setEntrenadorId(ObtenerEntrenador());
		usuarioMembresia.setPrecio(membresia.get().getPrecio());
		return usuarioMembresiaRepository.save(usuarioMembresia);
	}

	@Override
	public UsuarioMembresia updateUsuarioMembresia(UsuarioMembresia usuarioMembresia, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}
	@Override
	public List<UsuarioMembresia> membresiasActivas(Integer id) {
		List<UsuarioMembresia> usuarioMembresias = usuarioMembresiaRepository.findByUsuarioId(id);
		Date fecha = new Date();
		long fechaActual = fecha.getTime();
		
		
		List<UsuarioMembresia> membresias = usuarioMembresias.stream().filter(usuarioMembresia -> {
			long fechaInicio = usuarioMembresia.getFechaInicio().getTime();
			long fechaFin = usuarioMembresia.getFechaFin().getTime();
			
			return  fechaFin >= fechaActual;
		}).collect(Collectors.toList());
		
		return membresias;
	}
	
	@Override
	public UsuarioMembresiaDTO findByCedulaUsuario(String  cedula) {
		Optional<Usuario>usuario=usuarioReporitory.findByCedula(cedula);
		if(usuario.isPresent() && usuario.get().getRoles().stream()
	            .anyMatch(rol -> rol.getId() == 2) ) {
			UsuarioMembresiaDTO usuarioMembresiaDTO=new UsuarioMembresiaDTO();
			usuarioMembresiaDTO.setUsuario(modelMapper.map(usuario.get(), UsuarioDTO.class));
			usuarioMembresiaDTO.setUsuarioMembresias(membresiasActivas(usuario.get().getId()));
			return usuarioMembresiaDTO;
		}
		return null;
	}

	public Integer ObtenerEntrenador() {

		List<UsuarioMembresia> membresias = usuarioMembresiaRepository.findAll();
		List<Entrenador> entrenadores = entrenadorReposiotry.findAll();
		int n = membresias.size() - 1;
		if (n > 0) {
			UsuarioMembresia membresia = membresias.get(n);
			for (int i = 0; i < entrenadores.size(); i++) {
				if (entrenadores.get(i).getId().equals(membresia.getEntrenadorId())) {
					if (i == entrenadores.size() - 1) {
						return entrenadores.get(0).getId();
					} else {
						return entrenadores.get(i + 1).getId();
					}
				}
			}

		} else {
			return entrenadores.get(0).getId();
		}
		return null;

	}

	@Override
	public List<UsuarioDTO> listaClientesEntrenador(Integer id) {
	    Optional<Entrenador> entrenador = entrenadorReposiotry.findByUsuarioId(id);
	    if (entrenador.isPresent()) {
	        List<UsuarioMembresia> membresias = usuarioMembresiaRepository.findUsuariosMembresiaByFechaFin(new Date());
	        List<UsuarioMembresia> membresiasDelEntrenador = membresias.stream()
	            .filter(m -> m.getEntrenadorId() != null && m.getEntrenadorId().equals(entrenador.get().getId()))
	            .collect(Collectors.toList());

	        List<UsuarioDTO> usuarios = new ArrayList<>();
	        Set<Integer> usuariosAgregados = new HashSet<>();  // Usamos un conjunto para verificar duplicados

	        for (UsuarioMembresia usuarioMembresia : membresiasDelEntrenador) {
	            Optional<Usuario> usuario = usuarioReporitory.findById(usuarioMembresia.getUsuarioId());
	            if (usuario.isPresent()) {
	                Integer usuarioId = usuario.get().getId();
	                // Verificar si el usuario ya ha sido agregado a la lista
	                if (!usuariosAgregados.contains(usuarioId)) {
	                    UsuarioDTO usuarioDTO = modelMapper.map(usuario.get(), UsuarioDTO.class);
	                    usuarios.add(usuarioDTO);
	                    usuariosAgregados.add(usuarioId);  // Agregar el ID del usuario al conjunto de usuarios agregados
	                }
	            }
	        }

	        return usuarios;
	    }
	    return null;
	}

	@Override
	public List<UsuarioMembresia> membresiasUsuarioById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
