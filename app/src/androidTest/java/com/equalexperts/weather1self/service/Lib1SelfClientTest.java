package com.equalexperts.weather1self.service;

import com.equalexperts.weather1self.response.lib1Self.Stream;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class Lib1SelfClientTest {

    Lib1SelfClient lib1SelfClient;

    @Before
    public void setup() {
        lib1SelfClient = ServiceGenerator.createService(Lib1SelfClient.class, Lib1SelfClient.API_BASE_URL);
    }

    @Test
    public void creates1SelfStream() {
        Stream stream = lib1SelfClient.createStream();

        assertNotNull(stream);
        assertNotNull(stream.getId());
        assertThat(stream.getId().length(), is(10));
        assertNotNull(stream.getReadToken());
        assertThat(stream.getReadToken().length(), is(10));
        assertNotNull(stream.getWriteToken());
        assertThat(stream.getWriteToken().length(), is(10));
    }
}