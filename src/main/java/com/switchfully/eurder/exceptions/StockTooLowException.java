package com.switchfully.eurder.exceptions;

public class StockTooLowException extends RuntimeException{
	public StockTooLowException(String message){
		super(message);
	}
}
