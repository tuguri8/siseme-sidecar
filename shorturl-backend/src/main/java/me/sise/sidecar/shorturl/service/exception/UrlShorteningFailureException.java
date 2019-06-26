package me.sise.sidecar.shorturl.service.exception;

public class UrlShorteningFailureException extends RuntimeException {
	public UrlShorteningFailureException(String message) {
		super(message);
	}

	public static CustomPathIsAlreadyInUseException customPathIsAlreadyInUse() {
		return new CustomPathIsAlreadyInUseException();
	}

	public static WebUrlIsAlreadyInUseException webUrlIsAlreadyInUseException() {
		return new WebUrlIsAlreadyInUseException();
	}

	public static class WebUrlIsAlreadyInUseException extends UrlShorteningFailureException {
		public WebUrlIsAlreadyInUseException() {
			super("webUrl is already in use.");
		}
	}

	public static class InvalidUrlException extends UrlShorteningFailureException {
		public InvalidUrlException(String message) {
			super(message);
		}
	}

	public static class PathIsAlreadyInUseException extends UrlShorteningFailureException {
		public PathIsAlreadyInUseException() {
			super("path is already in use.");
		}
	}

	public static class CustomPathIsAlreadyInUseException extends UrlShorteningFailureException {
		public CustomPathIsAlreadyInUseException() {
			super("customPath is already in use.");
		}
	}
	
	public static class CustomPathAndWebUrlNullPointerException extends UrlShorteningFailureException {
		public CustomPathAndWebUrlNullPointerException() {
			super("CustomPath and WebUrl is null");
		}
	}

	public static class PathNullPointerException extends UrlShorteningFailureException {
		public PathNullPointerException() {
			super("Path is null");
		}
	}
	
	public static class PathNotFoundException extends UrlShorteningFailureException {
		public PathNotFoundException() {
			super("Path doesn't exist");
		}
	}
}
