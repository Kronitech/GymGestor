package co.edu.ufps.gimnasio.service.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import co.edu.ufps.gimnasio.util.ReporteGenerate;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
	
	@Autowired
	ReporteGenerate reporteGenerate;

	@Override
	public List<UsuarioMembresia> lista() {
		return usuarioMembresiaRepository.findAll();
		/*
		 * List<UsuarioMembresia>usuarios=usuarioMembresiaRepository.findAll();
		List<UsuarioMembresiasDTO>lista=modelMapper.map(usuarios, new TypeToken<List<UsuarioMembresiasDTO>>() {}.getType());
		return lista;
		 */
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
		
		List<UsuarioMembresia>membresiasActivas=membresiasActivas(usuarioMembresia.getUsuarioId().getId());
		if(!membresiasActivas.isEmpty()) {
			int n=membresiasActivas.size();
			
			fechaInicio=membresiasActivas.get(n-1).getFechaFin();
			
		}
		Optional<Membresia> membresia=membresiaRepository.findById(usuarioMembresia.getMembresiaId());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaInicio);
		calendar.add(Calendar.DAY_OF_MONTH, membresia.get().getDuracion()); // Agrega 30 días a la fecha de inicio
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
		Usuario usuario=new Usuario();
		usuario.setId(id);
		List<UsuarioMembresia> usuarioMembresias = usuarioMembresiaRepository.findByUsuarioId(usuario);
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
	            Optional<Usuario> usuario = usuarioReporitory.findById(usuarioMembresia.getUsuarioId().getId());
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

	@Override
	public byte[] informeMembresias(Date fechaInicio, Date fechaFin) throws JRException, FileNotFoundException {
		List<UsuarioMembresia>lista=usuarioMembresiaRepository.findUsuariosMembresiaByFechaRegistro(fechaInicio, fechaFin);
		if(lista.size()>0) {
			
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		
        params.put("tableDataset", new JRBeanCollectionDataSource(lista));
        params.put("fechaInicio", fechaInicio);
        params.put("fechaFin", fechaFin);
        params.put("total",calcularSumaDePrecios(lista) );
		return reporteGenerate.exportToPdf(params);
		}else {
			return null;
		}
		
	}

	@Override
	public byte[] informeMembresias() throws JRException, FileNotFoundException{
		
		List<UsuarioMembresia>lista=usuarioMembresiaRepository.findAll();
		Map<String, Object> params = new HashMap<String, Object>();
		
		
        params.put("tableDataset", new JRBeanCollectionDataSource(lista));
        params.put("fechaInicio", null);
        params.put("fechaFin", new Date());
        params.put("total",calcularSumaDePrecios(lista) );
		return reporteGenerate.exportToPdf(params);
	}
	// Método para calcular la suma de precios
	private int calcularSumaDePrecios(List<UsuarioMembresia> lista) {
	    return lista.stream()
	            .mapToInt(UsuarioMembresia::getPrecio)
	            .sum();
	}

	@Override
	public byte[] comprobantePago(Integer id) throws JRException, FileNotFoundException {
		Optional<UsuarioMembresia>usuaarioMembresia=usuarioMembresiaRepository.findById(id);
		if(usuaarioMembresia.isPresent()) {
			
			Optional<Membresia> membresia=membresiaRepository.findById(usuaarioMembresia.get().getMembresiaId());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("clienteNombre", usuaarioMembresia.get().getUsuarioId().getNombre());
			params.put("clienteCedula", usuaarioMembresia.get().getUsuarioId().getCedula());

			params.put("clienteEmail", usuaarioMembresia.get().getUsuarioId().getEmail());
			params.put("vendedorNombre", usuaarioMembresia.get().getVendedorId().getNombre());
			params.put("vendedorCedula", usuaarioMembresia.get().getVendedorId().getCedula());
			params.put("vendedorEmail", usuaarioMembresia.get().getVendedorId().getEmail());
			params.put("membresia", membresia.get().getNombre());
			params.put("descripcion", membresia.get().getDescripcion());
			params.put("fechaInicio", usuaarioMembresia.get().getFechaInicio());
			params.put("fechaFin", usuaarioMembresia.get().getFechaFin());
			params.put("precio", usuaarioMembresia.get().getPrecio());
			
			return reporteGenerate.comprobantePago(params);
		}
		return null;
	}


	

}
