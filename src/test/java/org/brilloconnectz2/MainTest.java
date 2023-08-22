package org.brilloconnectz2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @BeforeEach
    void setUp() {
    }

//    @Test
//    void generateJWT() {
//    }
//
//    @Test
//    void verifyJWT() {
//    }

    @Test
    public void testValidToken() {
        String validToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb3NodWEiLCJpYXQiOjE2OTI3Mjc4OTUsImV4cCI6MTY5MjczMTQ5NX0.igN31vYxgP6RnOwOH7edTmxCaKwKgAzqPZUp4hf5_Yo";
        String verificationStatus = Main.verifyJWT(validToken);
        assertEquals("verification pass", verificationStatus);
    }

    @Test
    public void testInvalidToken() {
        String invalidToken = "this-is_an_invalid_token_for_testing";
        String verificationStatus = Main.verifyJWT(invalidToken);
        assertEquals("verification fails", verificationStatus);
    }
}