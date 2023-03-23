package co.kr.minzero.dev.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Assertions;

public class EncryptTest {

    public static void main(String[] args) throws Exception {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setProvider(new BouncyCastleProvider());
        encryptor.setPoolSize(2);
        encryptor.setPassword("minzeroDev");
        encryptor.setAlgorithm("PBEWithSHA256AND128BitAES-CBC-BC");

        String[] plainTextList = {"dev_tester", "xptmxm2023!@", "r2dbc:mariadb://localhost:3306/test_dev?useSSL=false"};
        for(String plainText: plainTextList) {
            String encryptText = encryptor.encrypt(plainText);
            String decryptText = encryptor.decrypt(encryptText);

            System.out.println("String: " + plainText);
            System.out.println("Encrypt: " + encryptText);
            System.out.println("Decrypt: " + decryptText);
            Assertions.assertEquals(plainText, decryptText);
        }
    }
}
