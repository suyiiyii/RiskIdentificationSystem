package com.ljh.main.UserLR.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    private static Long expire=43200000L;

    private static String signKey="sssssssseeeeeeeeccccccccrrrrrrrreeeeeeeetttttttt";
    //生成jwt令牌
    public static String generateJWT(Map<String, Object> claims) {

        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)//签名算法
                .setClaims(claims) //自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis()+expire))//设置有效期为1h
                .compact();

        return jwt;

    }

    //解析jwt令牌
    public static Claims parseJWT(String jwt) {
        Claims claims=Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }




}
