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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.repository.UsuarioReporitory;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	UsuarioReporitory usuarioRepository;

	public boolean usuarioNuevo(Integer id ) throws MessagingException {

		try {
			
			Optional<Usuario>usuario=usuarioRepository.findById(id);
			if(usuario.isPresent()) {
				MimeMessage mimeMessageHelpe = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessageHelpe, "UTF-8");
				// GENERO CODIGO DE REGISTRO
				
				// TITULO DEL EMAIL
				String titulo = "SMART FIT";
				// DESCRIPCION
				String detalle = "Bienvenido , su membresia se activo con exito ya puedes iniciar sesion en nuestro sitio web ";
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
				messageHelper.setSubject("SMART FIT CORREO REGISTRO");
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

	public int generarCodigo(int longitud) {
		Random random = new Random();
		int min = (int) Math.pow(10, longitud - 1);
		int max = (int) Math.pow(10, longitud) - 1;
		return random.nextInt(max - min + 1) + min;
	}
	/*
	public boolean recuperarClave(Usuario preRegistro) throws MessagingException {

		try {
			// BUSCAMOS EL USUARIO POR CORREO
			Optional<Usuario> usuario = usuarioRepository.findByEmail(preRegistro.getEmail());
			if (usuario.isPresent()) {

				MimeMessage mimeMessageHelpe = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessageHelpe, "UTF-8");
				// GENERO CODIGO DE REGISTRO
				int codigo = generarCodigo(6);
				preRegistro.setPassword(codigo + "");
				preRegistro.setNombre(usuario.get().getNombre() + " " + usuario.get().getApellido());
				// TITULO DEL EMAIL
				String titulo = "DAFACI";
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
				context.setVariable("docente", preRegistro);
				context.setVariable("fecha", fechaFormateada);
				String htmlContent = templateEngine.process("email-password", context);
				messageHelper.setTo(preRegistro.getEmail());
				messageHelper.setSubject("RECUPERAR CONTRASEÑA DAFACI ");
				// messageHelper.setFrom("MENSAJE DE BIENVENIDA");
				messageHelper.setText(htmlContent, true);
				// CREO OBJ CODIGOREGISTRO
				// VVVVVVVVVVVVVVVVVVVVVVVVVVVVV
				// Genera un token único
				String token = UUID.randomUUID().toString();

				CodigoPassword codigoRegistro = new CodigoPassword();
				codigoRegistro.setUsuarioId(usuario.get().getId());
				codigoRegistro.setCodigo(codigo);
				codigoRegistro.setFechaRegistro(fecha);
				codigoRegistro.setEmail(preRegistro.getEmail());
				codigoRegistro.setMensaje(token);

				CodigoPassword codigoCurrent = codigoPasswordRepository.save(codigoRegistro);
				Optional<CodigoPassword> codigoReturn = codigoPasswordRepository.findById(codigoCurrent.getId());

				if (codigoReturn.isPresent()) {
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
	*/

}