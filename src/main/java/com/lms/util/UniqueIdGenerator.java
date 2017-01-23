package com.lms.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author bjha
 */
public class UniqueIdGenerator {
    
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        byte[] uuidArr = asByteArray(uuid);
        String uniqueId = bytesToHex(uuidArr);

        //String uniqueId = uniqueIdWithEquals.split("=")[0];

        return uniqueId;
    }

    
    public static byte[] asByteArray(UUID uuid) {

        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        byte[] buffer = new byte[16];

        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) (msb >>> 8 * (7 - i));
        }
        for (int i = 8; i < 16; i++) {
            buffer[i] = (byte) (lsb >>> 8 * (7 - i));
        }

        return buffer;

    }

    public static UUID toUUID(byte[] byteArray) {

        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (byteArray[i] & 0xff);
        }
        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (byteArray[i] & 0xff);
        }
        UUID result = new UUID(msb, lsb);

        return result;
    }
    
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) throws Exception {
        List<String> container = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            String uniqueId = UniqueIdGenerator.generateUniqueId();
            System.out.println("Base64 encoded unique ID: " + uniqueId);
            if(container.contains(uniqueId)) {
                System.out.println("Deplicate unique ID found: " + uniqueId);
            }
            container.add(uniqueId);
        }
    }

}

