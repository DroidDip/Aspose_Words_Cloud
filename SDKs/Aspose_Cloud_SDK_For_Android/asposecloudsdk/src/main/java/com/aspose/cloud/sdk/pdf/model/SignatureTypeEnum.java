package com.aspose.cloud.sdk.pdf.model;

public enum SignatureTypeEnum {
	
    // Represents signature object regarding PKCS#1 standard.
    // RSA encryption algorithm and SHA-1 digest method are used for signing.
    PKCS1,
    
    // Represents the PKCS#7 object that conform to the PKCS#7 specification in Internet RFC 2315, 
    // PKCS #7: Cryptographic Message Syntax, Version 1.5.
    // The SHA1 digest of the document's byte range is encapsulated in the PKCS#7 SignedData field.
    PKCS7,
  
    // Represents the PKCS#7 object that conform to the PKCS#7 specification in Internet RFC 2315, 
    // PKCS #7: Cryptographic Message Syntax, Version 1.5.
    // The original signed message digest over the document's byte range is incorporated as the normal PKCS#7 SignedData field. 
    // No data shall is encapsulated in the PKCS#7 SignedData field.
    PKCS7Detached
}
