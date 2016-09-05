package com.soch.uam.svc.constants;

public class APPConstants {
	
	public static String REG_EMAIL_SUB = "Account Activation Required";
	public static String REG_EMAIL_TEXT ="Welcome to the UAS portal. <br><h4 style=\"color:RED\">IMPORTANT: FURTHER ACTION IS REQUIRED TO ACTIVATE YOUR ACCOUNT!</h4> " +
			" <h4 >FOLLOW THE WEB ADDRESS BELOW TO ACTIVATE YOUR ACCOUNT:</h4>" +
			"<a href=\"url\" target=\"_blank\">url"+
			" </a> <br> <h5>If not clickable, please copy and paste the address to your browser.</h5><h4 style=\"color:RED\">DO NOT SHARE THIS LINK. IT WILL EXPIRE IN 24 HOURS.</h4> <br><br>";;
	public static String REG_EMAIL_URL = "/UASSvcUI/registerVerification.html?token=tokenParam";

}
