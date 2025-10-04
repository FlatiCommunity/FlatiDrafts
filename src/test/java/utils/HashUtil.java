```java
    public static String getMD5() throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(Files.readAllBytes(Paths.get(Bootstrap.class.getProtectionDomain().getCodeSource().getLocation().toURI())));

        BigInteger bigInt = new BigInteger(1, messageDigest.digest());
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));

        while(md5Hex.length() < 32) md5Hex.insert(0, "0");

        return md5Hex.toString();
    }
```