package co.edu.ufps.gimnasio.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import co.edu.ufps.gimnasio.model.entity.CodigoRecuperacion;
import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.model.entity.UsuarioMembresia;
import co.edu.ufps.gimnasio.repository.CodigoRecuperacionRepository;
import co.edu.ufps.gimnasio.repository.UsuarioMembresiaRepository;
import co.edu.ufps.gimnasio.repository.UsuarioReporitory;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	UsuarioReporitory usuarioRepository;
	@Autowired
	CodigoRecuperacionRepository codigoRecuperacionRepository;
	@Autowired
	UsuarioMembresiaService usuarioMembresiaService;

	public boolean usuarioNuevo(Integer id) throws MessagingException {

		try {

			Optional<Usuario> usuario = usuarioRepository.findById(id);
			if (usuario.isPresent()) {
				MimeMessage mimeMessageHelpe = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessageHelpe, "UTF-8");
				// GENERO CODIGO DE REGISTRO

				// TITULO DEL EMAIL
				String titulo = "ENERGY TIME";
				// DESCRIPCION
				String detalle = "Bienvenido , su cuenta se activo con exito ya puedes iniciar sesion en nuestro sitio web ";
				// FECHA GENERA EL EMAIL
				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es"));
				String fechaFormateada = sdf.format(fecha);
				// IMG
				// CREO EL CONTEXT PARA ENVIAR A LA PLANTILLA HTML

				Context context = new Context();
				context.setVariable("titulo", titulo);
				context.setVariable("detalle", detalle);
				context.setVariable("docente", usuario.get());
				context.setVariable("fecha", fechaFormateada);
				String htmlContent = templateEngine.process("email-template", context);
				messageHelper.setTo(usuario.get().getEmail());
				messageHelper.setSubject("ENERGY TIME CORREO REGISTRO");
				// messageHelper.setFrom("MENSAJE DE BIENVENIDA");
				messageHelper.setText(htmlContent, true);
				// CREO OBJ CODIGOREGISTRO

				javaMailSender.send(mimeMessageHelpe);
				return true;
			}
			return false;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean membresiaNueva(Integer id) throws MessagingException {

		try {

			UsuarioMembresia usuario = usuarioMembresiaService.findById(id);
			if (usuario != null) {
				MimeMessage mimeMessageHelpe = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessageHelpe, true, "UTF-8");
				// GENERO CODIGO DE REGISTRO

				// TITULO DEL EMAIL
				String titulo = "ENERGY TIME";
				// DESCRIPCION
				String detalle = "Su membresia se activo con exito ya puedes disfurtar de todos los beneficios ";
				// FECHA GENERA EL EMAIL
				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es"));
				String fechaFormateada = sdf.format(fecha);
				// IMG
				// CREO EL CONTEXT PARA ENVIAR A LA PLANTILLA HTML

				Context context = new Context();
				context.setVariable("titulo", titulo);
				context.setVariable("detalle", detalle);
				context.setVariable("fechaInicio", sdf.format(usuario.getFechaInicio()));
				context.setVariable("fechaFin", sdf.format(usuario.getFechaFin()));
				context.setVariable("docente", usuario.getUsuarioId());
				context.setVariable("fecha", fechaFormateada);
				String htmlContent = templateEngine.process("comprobante-template", context);
				messageHelper.setTo(usuario.getUsuarioId().getEmail());
				messageHelper.setSubject("ENERGY TIME COMPROBANTE DE MEMBRESIA");
				// messageHelper.setFrom("MENSAJE DE BIENVENIDA");
				messageHelper.setText(htmlContent, true);

				// Llamada al método comprobantePago
				byte[] pdfAttachment = usuarioMembresiaService.comprobantePago(id);

				// Adjunta el PDF al mensaje de correo electrónico
				if (pdfAttachment != null) {
					messageHelper.addAttachment("comprobantePago.pdf", new ByteArrayResource(pdfAttachment));
				}

				javaMailSender.send(mimeMessageHelpe);
				return true;
			}
			return false;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	public int generarCodigo(int longitud) {
		Random random = new Random();
		int min = (int) Math.pow(10, longitud - 1);
		int max = (int) Math.pow(10, longitud) - 1;
		return random.nextInt(max - min + 1) + min;
	}

	public boolean recuperarClave(String email) throws MessagingException {

		try {
			// BUSCAMOS EL USUARIO POR CORREO
			Optional<Usuario> usuario = usuarioRepository.findByEmail(email.toUpperCase());
			if (usuario.isPresent()) {
				MimeMessage mimeMessageHelpe = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessageHelpe, "UTF-8");
				// GENERO CODIGO DE REGISTRO
				int codigo = generarCodigo(6);
				usuario.get().setPassword(codigo + "");
				// TITULO DEL EMAIL
				String titulo = "ENERGY TIME";
				// DESCRIPCION
				String detalle = "El siguiente codigo es para completar el proceso de recuperar contraseña y  habilitar su cuenta ";
				// FECHA GENERA EL EMAIL
				Date fecha = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es"));
				String fechaFormateada = sdf.format(fecha);
				// IMG
				// CREO EL CONTEXT PARA ENVIAR A LA PLANTILLA HTML

				Context context = new Context();
				context.setVariable("titulo", titulo);
				context.setVariable("detalle", detalle);
				context.setVariable("docente", usuario.get());
				context.setVariable("fecha", fechaFormateada);
				String htmlContent = templateEngine.process("email-password", context);
				messageHelper.setTo(usuario.get().getEmail());
				messageHelper.setSubject("ENERGY TIME RECUPERAR CONTRASEÑA  ");
				messageHelper.setText(htmlContent, true);
				// CREO OBJ CODIGOREGISTRO
				// Genera un token único
				// String token = UUID.randomUUID().toString();

				CodigoRecuperacion codigoRegistro = new CodigoRecuperacion();
				codigoRegistro.setUsuarioId(usuario.get().getId());
				codigoRegistro.setCodigo(codigo);
				codigoRegistro.setFechaRegistro(fecha);
				codigoRegistro.setEstado(false);
				CodigoRecuperacion codigoReturn = codigoRecuperacionRepository.save(codigoRegistro);

				if (codigoReturn != null) {
					javaMailSender.send(mimeMessageHelpe);
					return true;
				}
			}
			return false;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
