package com.lshadown.example.idgenerator.impl;

import com.lshadown.example.idgenerator.IId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author lshadown
 */

@Component
public class Id implements IId {

    private SecureRandom secureRandom;
    private MessageDigest messageDigest;
    private Logger logger = LoggerFactory.getLogger( getClass() );

    public Id() throws NoSuchAlgorithmException {
        this.secureRandom = new SecureRandom();
        logger.info( "using following random number generator: {}:{}", secureRandom.getAlgorithm(), secureRandom.getProvider() );
        this.messageDigest = MessageDigest.getInstance( "SHA-1" );
    }

    @Override
    public String generate() {
        logger.info( "starting id generation" );
        String randomNum = new Integer( secureRandom.nextInt() ).toString();
        byte[] result = messageDigest.digest( randomNum.getBytes() );
        return String.valueOf( Hex.encode( result ) );
    }
}
