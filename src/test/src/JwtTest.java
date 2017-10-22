import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.junit.Test;

import sun.security.rsa.RSAPublicKeyImpl;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;

public class JwtTest {

    private final static String PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhe5DM3az4Av8wIWOTJVbc1vyDNtLCkuv2DiQYS8l0K/Bq+/6ZMA38REafREg8oi+88VPzD4/6PTrt7O3yN8bs7J9lC+KkiintKw/D5Uld9VMAggh6Pjuk3f2ml97ERJqDOknJBdcaqw4gLvly/gNZm+xEJvssI7Gp0F15zewCyB8ocBvkL2jiUeD9obnVbDXWhAe2qKQtknD2vbOCY31OEw2F6htisUNGhsE0cOoV3JwVrH1l8ANgPz+nWAraVPh+vQytAGhDRT2d30sfxeJG7w6xF1r90q7OFsJTnPiv/h2oQC+ffKOaVi/OUzUYMy6P156xBhNg3X2mGGEqF29qQIDAQAB";
    private final static String CERT_KEY =
            "MIIClzCCAX8CBgFenhx7mjANBgkqhkiG9w0BAQsFADAPMQ0wCwYDVQQDDAR0ZXN0MB4XDTE3MDkyMDA3MDUyMFoXDTI3MDkyMDA3MDcwMFowDzENMAsGA1UEAwwEdGVzdDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAK7kwdbCHWonUNmlbAKygBhP123jBuffQHf+k7gqaTxCumNVLyCJS02Khb1pUAKcDQM4ZbELf1nBP6wBvzVDmWuTr7WkqUeDZUGPyoJIs8tdoCeswjVU82gJCizYI//X7g3roc/1p+dsLgz434pWwg6AdOPf2L2VCVTaP64p9xnGSX0HS8QNEDFG/itEcuQpQ2ltJH8yWJtE3kihutDHVFxxDbZhMosvj32J+EPahPV7+5tJF8TV7d725Ovay3IMN/NpHi75Kp8HXkzlar4bdM6uF+jeA3d8dh3pPtHKxxdAoUJKESfv8k+O6nvhH9M+rgMo0PPru/Phpd6aumhCb1UCAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAV3B6/THYh/a8YLHcy6vRU9oX+qZORzU6tlS06lmbxlASY924bzYW/Zp/dG99z1oajpKxWbfn/P08Qz+czwoBEV63klSLIlMu55ua78Gw6xwY6SMa2HOxGL6lzUeXzxuVS2jEo4MwCo4YPqGca/drvGddklmaOzvvj7L9Fww4gbOq938yP3Y981n6QGXwVQ0PSmbBeaTD7dpr6omCBS5RmtwwGP6W4cNGn0PyGJGaP+VEpH7s6fPuWNGyi1Tf2gqUm8A0l/OuXu1kAy8s/+FYKN/uUU7OTlqTIddcd/+lX4OcpFQ1xi/mhCKqigBhBQONH3LxneM3bGHfF3KhDoqngA==";

    @Test
    public void testJwt(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            RSAPublicKeyImpl rsaPublicKey = new RSAPublicKeyImpl(decoder.decode(PUBLIC_KEY));
            PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(decoder.decode(CERT_KEY));
            KeyFactory kf = KeyFactory.getInstance("RSA");
//            CertAndKeyGen certAndKeyGen = new CertAndKeyGen("RSA",);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kf.generatePrivate(ks);
            System.out.println(rsaPrivateKey);
//            PublicKeyFac
//            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .withIssuer("auth0")
//                    .build(); //Reusable verifier instance
//            DecodedJWT jwt = verifier.verify(token);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testJwt2() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyB64 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhe5DM3az4Av8wIWOTJVbc1vyDNtLCkuv2DiQYS8l0K/Bq+/6ZMA38REafREg8oi+88VPzD4/6PTrt7O3yN8bs7J9lC+KkiintKw/D5Uld9VMAggh6Pjuk3f2ml97ERJqDOknJBdcaqw4gLvly/gNZm+xEJvssI7Gp0F15zewCyB8ocBvkL2jiUeD9obnVbDXWhAe2qKQtknD2vbOCY31OEw2F6htisUNGhsE0cOoV3JwVrH1l8ANgPz+nWAraVPh+vQytAGhDRT2d30sfxeJG7w6xF1r90q7OFsJTnPiv/h2oQC+ffKOaVi/OUzUYMy6P156xBhNg3X2mGGEqF29qQIDAQAB";
// ok, you may need to use the Base64 decoder of bouncy or Android instead
        byte[] decoded = Base64.getDecoder().decode(publicKeyB64);
        org.bouncycastle.asn1.pkcs.RSAPublicKey pkcs1PublicKey = org.bouncycastle.asn1.pkcs.RSAPublicKey.getInstance(decoded);
        BigInteger modulus = pkcs1PublicKey.getModulus();
        BigInteger publicExponent = pkcs1PublicKey.getPublicExponent();
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, publicExponent);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey generatedPublic = kf.generatePublic(keySpec);

        String token = "eyJhbGciOiJSUzI1NiIsImtpZCIgOiAiTEZDMExBOXFqSlpwTUxKQU95ak1zbUhVQXlkWHBWcDNKZXRWYVpwc2FsbyJ9.eyJqdGkiOiJmMWUxY2JmZC1hMWM4LTQyY2YtOTJiZC1lNzM1MTFmZTAzN2EiLCJleHAiOjE1MDg1MjY0ODQsIm5iZiI6MCwiaWF0IjoxNTA4NDQwMDg0LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvRXhhbXBsZVJlYWxtIiwiYXVkIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL0V4YW1wbGVSZWFsbSIsInR5cCI6IkluaXRpYWxBY2Nlc3NUb2tlbiJ9.Wqwr9w8Vvcq-iWxDPSvBFAtc1jqMCVrEYM-q_uOq90Kt28YZ0xRSLNIN6s-mId41g7k3gj-rBFUuCNun5j3fiPmMqUNMiaSw0GeTCOe2bn3yo2FZArIjzOaEcq9Edui_h1UmTtzQX9vCBEYI1Lf3oINFzwdKkiovvQLx4_DxGfMV1mT22Tud9tEm2mzLdeSCK5ofLmU0oGv7AVO_KUTl8grIrNPJ8t1MJPMrfbb9qdx1Po2PCsikq-8CYSf3mV1kpaZGMUYoDHt1TKz9r_QIkuxyUhTwlgCfze9-rzESu70Iz1c9bExUsuQqEoXWioZ9Oq7rB3VyWGCXYomg0LY6Lw";
//        Object body1 = Jwts.parser().parse(token).getBody();
//        System.out.println(body1);
//        MacProvider.
        Object body = Jwts.parser().setSigningKey(generatedPublic).parse(token).getBody();
        Header header = Jwts.parser().setSigningKey(generatedPublic).parse(token).getHeader();
        System.out.println(body);
        System.out.println(header);

    }
    static String token = "eyJhbGciOiJSUzI1NiIsImtpZCIgOiAidlR2SUk2VTRRRC11Q0tHTW51NmtxNXhUOHplUWxlbzZfZ2szYllwMmxJOCJ9.eyJqdGkiOiIxMWEyMzg3Yi1jNzU2LTQ5MTQtOGQ1Ny1kMDc5NjNlYTFkMTgiLCJleHAiOjE1MDg2NjA2MjIsIm5iZiI6MCwiaWF0IjoxNTA4NTc0MjIyLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvVGVzdCIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hdXRoL3JlYWxtcy9UZXN0IiwidHlwIjoiSW5pdGlhbEFjY2Vzc1Rva2VuIn0.OLpkrMsYW3wcwxb_ArkrwlvbebiCYAyj52O2-Jaw3iPbhrf3bhiq9UoxZXeuCStBwxr6fvwFxWNIv57y2HUbETzV8NNBhXKs-xbKPnKdp81q55U57I-Bu_49ackcXqM8bomvbDtR1ace2NipTP8n7vixVNwHPfb88O4IxUJxRG3ml96mKBuQvATkWtC4cH_2VA4oQfUNuZ11J2P5mtQkDzNSUUt4oeZc-Jb4csyndByl7qI8rcRolZUM5AWhgssgd5EHL5D992prxHr9baYCcr07n39-KGJWioPjoGG8-YWDicD0eCBoYtrnv13ilzJUBpCICRNsdLgJwUS2TOSTPg";
    static String token2 = "eyJhbGciOiJSUzI1NiIsImtpZCIgOiAidlR2SUk2VTRRRC11Q0tHTW51NmtxNXhUOHplUWxlbzZfZ2szYllwMmxJOCJ9.eyJqdGkiOiJkZTc4ZDE3OS03YWU0LTQxNjctOGYzNS00ZjYwOTVjOTg2MDEiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTA4NTc1MzY0LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvVGVzdCIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hdXRoL3JlYWxtcy9UZXN0IiwidHlwIjoiSW5pdGlhbEFjY2Vzc1Rva2VuIn0.QziELdCC-OX7JT8J8kUHGmgyVo8Ea9kBeGaH-WVih8ZGKNnY40Nlr1-9BUio5e-6VRE4kbOqN4wmhfLoohWrq7WHTnq7umcgKQlALwcOfSUvEPzb6zs5BV4eh0LBiWWIBjnZqIqTijD-k482OLKlsPdZk8i5Hf_L7UQWUL0WpFfqBCjSfUv7k1pY1417tZ3AN1-RqH6bjXuhwTgxGMbu6TdbmA2cquMJws-noGA1wvUxEBwbUziXrabAYg39grnTrvN7vxU2_LK5n0s1XjkKwzDEJphzs68ZlLJw3reKhECa83gThvh9zv_3lqHGgHhTGgfJnYKjN08Mhy8cbJ7ndA";
    @Test
    public void testJwt3() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyData = Base64.getDecoder().decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAssBOxzMtbYwSYTucaIsmlhitv0NXZcu8ASbZoNPOshiqzpacY1rqXZvJzKEh2IAPCZLR6Mnd4jxtq2Lr7zbrva3P1B+4QzLM9p8w3d//sfqUw4F6I6Mvyt1aK3zlPPS493AHx+LM6+6e88kLIotc9WDzwq65nlNolkEDJdCqf/KDNFMg+R2aZjBfgDJfE8hpqAXTwBCycRnM+rEr5mcF+jH7dCO5e2F69EOuAVqp6Xc4+BHupGRbb8TCVZx6kG0eeUUd+Xai19YlKsA2zkrCAX8prR6bOzjVTjCQysWP82wQZ53JhGeFMR5HlTsvruQq/DxvcPgR2nXWAAlEYJaJFwIDAQAB");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyData);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, null);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token2);
        System.out.println(jwt.getHeader());
        System.out.println(jwt.getPayload());
        System.out.println(jwt);
        System.out.println(jwt.getClaims());
        System.out.println(jwt.getContentType());
        System.out.println(jwt.getExpiresAt());
        System.out.println(jwt.getSubject());
        System.out.println(jwt.getType());
    }
}
