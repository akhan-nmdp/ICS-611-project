package com.baeldung.commons.ftp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

public class JdkFtpClientIntegrationTest {

    private FakeFtpServer fakeFtpServer;


    @Before
    public void setup() throws IOException {
        fakeFtpServer = new FakeFtpServer();
        fakeFtpServer.addUserAccount(new UserAccount("user", "password", "/data"));

        FileSystem fileSystem = new UnixFakeFileSystem();
        fileSystem.add(new DirectoryEntry("/data"));
        fileSystem.add(new FileEntry("/data/foobar.txt", "abcdef 1234567890"));
        fakeFtpServer.setFileSystem(fileSystem);
        fakeFtpServer.setServerControlPort(0);

        fakeFtpServer.start();
    }

    @After
    public void teardown() throws IOException {
        fakeFtpServer.stop();
    }

    @Test
    public void givenRemoteFile_whenDownloading_thenItIsOnTheLocalFilesystem() throws Exception {
        String ftpUrl = String.format("ftp://user:password@localhost:%d/foobar.txt", fakeFtpServer.getServerControlPort());

        URLConnection urlConnection = new URI(ftpUrl).toURL().openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        Files.copy(inputStream, new File("downloaded_buz.txt").toPath());
        inputStream.close();

        assertThat(new File("downloaded_buz.txt")).exists();

        new File("downloaded_buz.txt").delete(); // cleanup
    }

}
