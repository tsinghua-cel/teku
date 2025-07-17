/*
 * Copyright Consensys Software Inc., 2025
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.teku.attacker;

import java.util.HexFormat;
import java.util.concurrent.atomic.AtomicBoolean;

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
