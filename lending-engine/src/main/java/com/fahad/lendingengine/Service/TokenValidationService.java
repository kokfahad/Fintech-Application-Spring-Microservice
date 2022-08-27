package com.fahad.lendingengine.Service;


import com.fahad.lendingengine.domain.entity.User;
import com.fahad.lendingengine.exception.UserNotFoundException;
import com.fahad.lendingengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidationService {
    @Autowired
    private UserRepository userRepository;
    @Value("${security.baseurl}")
    private String securityContextBaseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public User validateTokenAndGetUser(String token){
        org.springframework.http.HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION,token);
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate
                .exchange(securityContextBaseUrl + "/user/validate", HttpMethod.POST
                ,httpEntity,String.class);

        if (response.getStatusCode().equals(HttpStatus.OK)){
            return userRepository.findById(response.getBody())
                    .orElseThrow(()-> new UserNotFoundException(response.getBody()));
        }

        throw new RuntimeException("Invalid token");
    }
}
