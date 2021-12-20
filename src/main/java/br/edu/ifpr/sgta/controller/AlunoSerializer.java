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

import br.edu.ifpr.sgta.model.Aluno;

public class AlunoSerializer extends StdSerializer<Set<Aluno>> {
	private static final long serialVersionUID = 1L;
	public AlunoSerializer() {
		this(null);
	}
	public AlunoSerializer(Class<Set<Aluno>> t) {
		super(t);
	}
	@Override
	public void serialize(Set<Aluno> tipo, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Map<String, Object> alunoMap = new HashMap<>();
		List<Map<String, Object>> tipoList = new ArrayList<>();
		for (Aluno a : tipo) {
			alunoMap = new HashMap<>();
			alunoMap.put("id", a.getId());
			alunoMap.put("matricula", a.getMatricula());
			tipoList.add(alunoMap);
		}
		generator.writeObject(tipoList);
	}
}