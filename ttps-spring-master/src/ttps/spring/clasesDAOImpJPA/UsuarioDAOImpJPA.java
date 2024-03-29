package ttps.spring.clasesDAOImpJPA;

import ttps.spring.clasesDAO.UsuarioDAO;
import ttps.spring.model.Usuario;
import ttps.spring.model.DTO.UsuarioDTO;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpJPA extends GenericDAOImpJPA<Usuario> implements UsuarioDAO {

	public UsuarioDAOImpJPA() {
		super(Usuario.class);
	}
	
	
	public UsuarioDTO autenticar(String username, String password) {
		try {
			Object obj = this.getEntityManager().
					createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName()
							+ " o WHERE o.username='" + username + "' and o.password='"+password+"'").getSingleResult();
			UsuarioDTO user = new UsuarioDTO((Usuario)obj);
			return user;
		} catch (RuntimeException e) {
			System.out.println("No se encuentra usuario con username y password ingresado");
			return null;
		}
	}
	
	public String tipoUsuario(Long id) {
		try {
			//recupera por id y devuelve el tipo
			return( (this.getEntityManager().
					createQuery("SELECT o FROM " + this.getPersistentClass().getSimpleName()
							+ " o WHERE o.id=" +id).getSingleResult()).getClass().getSimpleName());
		} catch (RuntimeException e) {
			System.out.println("fall� la busqueda del rol");
			return "";
		}
	}

}
