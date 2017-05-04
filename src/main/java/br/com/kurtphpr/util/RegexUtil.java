package br.com.kurtphpr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	private static final Pattern regexAll = Pattern.compile("/carros");
	private static final Pattern regexById = Pattern.compile("/carros/([0-9]*)");

	// Verifica se a URL é padrão "/carros/id"
	public static Long matchId(String requestURL) {
		// Verifica o ID
		Matcher matcher = regexById.matcher(requestURL);
		if (matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			if (s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}

	// Verifica se a URL é padrão "/carros"
	public boolean matchAll(String requestURL) {
		Matcher matcher = regexAll.matcher(requestURL);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

}
