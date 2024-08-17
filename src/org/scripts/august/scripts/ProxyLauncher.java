package org.scripts.august.scripts;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import simple.Loader;

public class ProxyLauncher {

    public static void main(String[] args) {
        String[] proxy = "".split(":");
        if (proxy.length >= 2) {
            System.setProperty("socksProxyHost", proxy[0]);
            System.setProperty("socksProxyPort", proxy[1]);
        }
        if (proxy.length >= 4) {
            System.setProperty("java.net.socks.username", proxy[2]);
            System.setProperty("java.net.socks.password", proxy[3]);
            final String user = proxy[2];
            final char[] pass = proxy[3].toCharArray();
            Authenticator.setDefault(new Authenticator() {
                private final PasswordAuthentication auth = new PasswordAuthentication(user, pass);

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return this.auth;
                }
            });
        }
        Loader.main(new String[0]);
    }

}