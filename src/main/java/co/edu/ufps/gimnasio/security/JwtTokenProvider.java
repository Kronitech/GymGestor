package co.edu.ufps.gimnasio.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import co.edu.ufps.gimnasio.excepciones.BlogAppException;
import co.edu.ufps.gimnasio.model.entity.Usuario;
import co.edu.ufps.gimnasio.repository.UsuarioReporitory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	@Autowired
	UsuarioReporitory usuarioRepository;

	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	public String generarToken(Authentication authentication) {
		String username = authentication.getName();
		Date fechaActual = new Date();
		Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationInMs);
		Optional<Usuario>usuario=usuarioRepository.findByEmail(username);
		Map<String, Object>extra=new HashMap<>();
		extra.put("nombre",usuario.get().getNombre());
		extra.put("foto", usuario.get().getFoto());
		extra.put("email", usuario.get().getEmail());
		extra.put("roles", usuario.get().getRoles());
		extra.put("id", usuario.get().getId());
		extra.put("fechaNacimiento", usuario.get().getFechaNacimiento().toString());
		extra.put("telefono", usuario.get().getTelefono());
		extra.put("cedula", usuario.get().getCedula());
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.addClaims(extra)
				.setExpiration(fechaExpiracion)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		
		return token;
	}
	
	public String obtenerUsernameDelJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch (SignatureException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,"Firma JWT no valida");
		}
		catch (MalformedJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT no valida");
		}
		catch (ExpiredJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT caducado");
		}
		catch (UnsupportedJwtException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,"Token JWT no compatible");
		}
		catch (IllegalArgumentException ex) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST,"La cadena claims JWT esta vacia");
		}
	}
}

