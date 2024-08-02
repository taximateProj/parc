package com.umc.taxisharing.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTUtils {

	private static final String SECRET_KEY = "your_secret_key";

	// JWT에서 사용자 ID를 추출합니다.
	// parseClaimsJws 메서드를 사용하여 JWT의 클레임을 파싱하고,
	// getSubject()를 호출하여 사용자 ID를 반환합니다.
	public static String getUserIdFromToken(String token) {
		Claims claims = Jwts.parser()
			.setSigningKey(SECRET_KEY.getBytes())
			.parseClaimsJws(token.replace("Bearer ", ""))
			.getBody();
		return claims.getSubject();
	}
}