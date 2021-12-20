package br.edu.ifpr.sgta.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.edu.ifpr.sgta.model.Usuario;

public class UsuarioSerializer extends StdSerializer<Set<Usuario>> {
	private static final long serialVersionUID = 1L;
	public UsuarioSerializer() {
		this(null);
	}
	public UsuarioSerializer(Class<Set<Usuario>> t) {
		super(t);
	}
	@Override
	public void serialize(Set<Usuario> usuario, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Map<String, Object> usuarioMap = new HashMap<>();
		List<Map<String, Object>> userList = new ArrayList<>();
		for (Usuario u : usuario) {
			usuarioMap = new HashMap<>();
			usuarioMap.put("id", u.getId());
			usuarioMap.put("email", u.getEmail());
			userList.add(usuarioMap);
		}
		generator.writeObject(userList);
	}
}