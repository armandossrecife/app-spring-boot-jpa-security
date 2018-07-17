package com.myapp.util;

import java.util.ArrayList;
import java.util.List;

import com.myapp.modelo.Role;


public class ManipulaPermissoes {
	public List<Role> checaPapelUsuario(String papel){
		List<Role> papeis = new ArrayList<>();
		Role papelAdmin = new Role(); 
		Role papelUser = new Role();
		
		papelAdmin.setId(1);
		papelAdmin.setNome("ROLE_ADMIN");
		papelUser.setId(2);
		papelUser.setNome("ROLE_USER");
		
		switch (papel) {
		case "admin":
			papeis.add(papelAdmin);
			break;
		case "usuario":
			papeis.add(papelUser);
			break;
		default:
			break;
		}
				
		return papeis;
	}
}
