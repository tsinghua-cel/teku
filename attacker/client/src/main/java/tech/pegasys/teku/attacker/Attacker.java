package tech.pegasys.teku.attacker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.HexFormat;

public class Attacker {
    private static final AtomicBoolean initOnce = new AtomicBoolean(false);
    private static String serviceUrl;
    private static AttackClient client;

    public static byte[] fromHex(String s) throws IllegalArgumentException {
        String hex = s.startsWith("0x") ? s.substring(2) : s;
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid hex string");
        }
        return HexFormat.of().parseHex(hex);
    }

    private static void initAttacker() {
        serviceUrl = System.getenv("ATTACKER_SERVICE_URL");
    }

    public static AttackClient getAttacker() {
        if (initOnce.compareAndSet(false, true)) {
            initAttacker();
        }

        if (client != null) {
            return client;
        }

        if (serviceUrl == null || serviceUrl.isEmpty()) {
            return null;
        }

        try {
            client = AttackClient.dial(serviceUrl, 0);
            return client;
        } catch (Exception e) {
            return null;
        }
    }
}