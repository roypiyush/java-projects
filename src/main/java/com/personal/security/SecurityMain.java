package com.personal.security;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.bcpg.ArmoredOutputStream;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPLiteralDataGenerator;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKey;
import org.bouncycastle.openpgp.PGPPublicKeyRingCollection;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPSignature;
import org.bouncycastle.openpgp.PGPSignatureGenerator;
import org.bouncycastle.openpgp.PGPUtil;
import org.bouncycastle.openpgp.operator.jcajce.JcaKeyFingerprintCalculator;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentSignerBuilder;
import org.bouncycastle.openpgp.operator.jcajce.JcaPGPContentVerifierBuilderProvider;
import org.bouncycastle.openpgp.operator.jcajce.JcePBESecretKeyDecryptorBuilder;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Iterator;

public class SecurityMain {
    public static void main(String[] args) throws Exception {

        // Reference
        // https://docs.oracle.com/en/java/javase/11/security/java-security-overview1.html#GUID-2EF91196-D468-4D0F-8FDC-DA2BEA165D10
        // https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#messagedigest-algorithms

        final BouncyCastleProvider provider = new BouncyCastleProvider();
        System.out.printf("Name=%s\n", provider.getName());
        System.out.printf("Version=%s\n", provider.getVersionStr());
        System.out.printf("Info=%s\n", provider.getInfo());

        Security.addProvider(provider);
        System.out.printf("Max Key Size for AES=%s CFB=%s\n",
                javax.crypto.Cipher.getMaxAllowedKeyLength("AES"),
                javax.crypto.Cipher.getMaxAllowedKeyLength("CFB"));

        final SecureRandom nativePRNG = SecureRandom.getInstance("NativePRNG");
        System.out.println("Secure Random Algo = " + nativePRNG.getAlgorithm());

        System.out.println("Digest " + Base64.encodeBase64String(MessageDigest.getInstance("SHA3-512").digest("MyInput".getBytes(StandardCharsets.UTF_8))));

        Signature.getInstance("SHA256withDSA");

        final KeyPair keyPair = generateKeyPair(nativePRNG, 4096);
        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();

        final String message = "Hello, World!";
        final String signature = signMessage(message, privateKey);
        System.out.printf("Signature verified: %s\n", verifySignature(message, signature, publicKey));
    }

    public static KeyPair generateKeyPair(final SecureRandom secureRandom,
                                          final int keysize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keysize, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public static String signMessage(String message, PrivateKey privateKey) throws
            NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());
        byte[] signatureBytes = signature.sign();
        return Base64.encodeBase64String(signatureBytes);
    }

    public static boolean verifySignature(String message, String signatureString, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        byte[] signatureBytes = Base64.decodeBase64(signatureString);
        return signature.verify(signatureBytes);
    }
}
