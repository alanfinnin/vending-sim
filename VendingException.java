class VendingException extends RuntimeException {
	/**
	*	The exception that is thrown for 
	*	an array of issues within the 
	*	machine etc a lack of funds   
	*/
	VendingException(String message) {
		super(message);
	}
}
