package jc.oauth2;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
public class OAuth2Filter implements Filter {

    @Value("${oauth2.rsa.public.key.base64}")
    private String base64RSAPublicKey;
    private JWTVerifier jwtVerifier;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jwtVerifier = createJWTVerifier(base64RSAPublicKey);
    }

    private JWTVerifier createJWTVerifier(String base64RSAPublicKey) {
        RSAPublicKey rsaPublicKey = createRSAPublicKey(base64RSAPublicKey);
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, null);
        return JWT.require(algorithm)
                .build();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest){
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            String authentication = httpServletRequest.getHeader("Authorization");
            try{
                DecodedJWT jwt = jwtVerifier.verify(authentication.split(" ")[1]);
                checkPermissions(jwt, httpServletRequest.getPathInfo());
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (Exception e){
                e.printStackTrace();
                httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            }
        }
    }

    private void checkPermissions(DecodedJWT jwt, String pathInfo) {
        List<String> roles = (List<String>) jwt.getClaim("realm_access")
                .asMap()
                .get("roles");
//        if(!roles.contains(split))
        jwt.getClaims().entrySet().forEach(System.out::println);
    }

    @Override
    public void destroy() {

    }

    private RSAPublicKey createRSAPublicKey(String base64RSAPublicKey){
        try {
            byte[] publicKeyData = Base64.getDecoder().decode(base64RSAPublicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyData);
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException  e) {
            throw new RuntimeException(e);
        }
    }
}
