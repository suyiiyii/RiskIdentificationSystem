package com.ljh.main;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


//@SpringBootTest
class MainApplicationTests {

    @Test
    public void testUuid () {
        for(int i = 0; i < 10; i++){
            String uuid= UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    @Test
    public void testJWT() {
        Map<String, Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","tom");
        //byte[] key = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
        String key="sssssssseeeeeeeeccccccccrrrrrrrreeeeeeeetttttttt";

        String jwt=Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)//签名算法
                .setClaims(claims) //自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis()+1000*3600))//设置有效期为1h
                .compact();
        System.out.println(jwt);
        System.out.println(key);

        /*解析jwt*/
        System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody());

        Map<String, Object> claims2 = Jwts.parser()
                .setSigningKey("sssssssseeeeeeeeccccccccrrrrrrrreeeeeeeetttttttt")
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(claims2);
    }




}


/*
public class JWTUtil {

    // 假设这是从配置文件或环境变量中获取的密钥
    private static final String SECRET_KEY = "your-secure-secret-key";
    // 从配置文件读取的JWT过期时间（以毫秒为单位）
    private static final long EXPIRATION_TIME = 3600000; // 1小时

    public static void main(String[] args) {
        try {
            String jwt = generateJWT(1, "tom");
            System.out.println(jwt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateJWT(int id, String username) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", username);

        Claims jwtClaims = Jwts.claims().setSubject(username);
        jwtClaims.putAll(claims);

        return Jwts.builder()
                .setClaims(jwtClaims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}*/
