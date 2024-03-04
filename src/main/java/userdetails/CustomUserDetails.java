package userdetails;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Alumno;
import com.example.demo.entity.Empresa;

@Component
public class CustomUserDetails implements UserDetails {

	private Alumno alumno;
	private Empresa empresa;

	public CustomUserDetails(Alumno alumno) {
		super();
		this.alumno = alumno;

	}

	public CustomUserDetails(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(alumno.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return alumno.getPassword();
	}

	@Override
	public String getUsername() {
		if (alumno != null) {
			return alumno.getUsername(); // o getEmail() si es el correo electrónico lo que almacenas aquí
		} else if (empresa != null) {
			return empresa.getEmail();
		}
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;

	}

	public int getId() {
		return alumno.getId();
	}

	public String getNombre() {
		if (alumno != null) {
			return alumno.getNombre();
		} else if (empresa != null) {
			return empresa.getNombre();
		}
		return null;
	}
}
