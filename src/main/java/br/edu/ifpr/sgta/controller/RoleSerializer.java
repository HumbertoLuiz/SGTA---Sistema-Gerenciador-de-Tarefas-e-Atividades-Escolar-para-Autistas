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

import br.edu.ifpr.sgta.model.Role;
public class RoleSerializer extends StdSerializer<Set<Role>> {
	
	private static final long serialVersionUID = 1L;

	public RoleSerializer() {
		this(null);
	}

	public RoleSerializer(Class<Set<Role>> t) {
		super(t);
	}

	@Override
	public void serialize(Set<Role> role, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Map<String, Object> roleMap = new HashMap<>();
		List<Map<String, Object>> roleList = new ArrayList<>();
		for (Role r : role) {
			roleMap = new HashMap<>();
			roleMap.put("id", r.getId());
			roleMap.put("nome", r.getNome());
			roleList.add(roleMap);
		}
		generator.writeObject(roleList);
	}

}

