package com.umc.taxisharing.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTUtils {

	private static final String SECRET_KEY = "your_secret_key"; // 실제 프로젝트에서는 환경 변수나 별도의 설정 파일을 통해 관리

	public static String getUserIdFromToken(String token) {
		Claims claims = Jwts.parser()
			.setSigningKey(SECRET_KEY.getBytes())
			.parseClaimsJws(token.replace("Bearer ", ""))
			.getBody();
		return claims.getSubject();
	}
}