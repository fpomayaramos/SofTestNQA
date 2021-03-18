package org.example;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadTest {

    @Test
    public void downloadGoogle() throws MalformedURLException, IOException {

        URL url = new URL("https://www.google.com");
        InputStream in = url.openStream();
        String str = IOUtils.toString(in, "UTF-8");
        System.out.println(str);
    }
}
