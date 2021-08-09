package dan.tp2021.zuul.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;


public class CustomJwtAuthenticationConverter extends JwtAuthenticationConverter{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomJwtAuthenticationConverter.class);
	public static final String RESOURCE_ACCESS = "resource_access";
	
	protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt){
		Map<String, Object> clientRoles = jwt.getClaimAsMap(RESOURCE_ACCESS);
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<GrantedAuthority> permisos = new ArrayList<>();
		JsonNode actualObj;
		try {
			actualObj = mapper.readTree(clientRoles.get("dan-client").toString());
			ArrayNode roles = (ArrayNode) actualObj.get("roles");
			roles.forEach(r->{logger.debug(r.asText().toString()); permisos.add(new SimpleGrantedAuthority(r.asText().toString()));});
		}
		catch(JsonProcessingException error) {
			error.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return permisos;
	}
}
