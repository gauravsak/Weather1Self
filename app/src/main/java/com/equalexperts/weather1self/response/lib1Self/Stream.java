package com.equalexperts.weather1self.response.lib1Self;

public class Stream {
    private String streamid;
    private String readToken;
    private String writeToken;

    public Stream(String streamid, String readToken, String writeToken) {
        this.streamid = streamid;
        this.readToken = readToken;
        this.writeToken = writeToken;
    }

    public String getId() {
        return streamid;
    }

    public String getReadToken() {
        return readToken;
    }

    public String getWriteToken() {
        return writeToken;
    }

    @Override
    public String toString() {
        return "[" + streamid + ", " + readToken + ", " + writeToken + "]";
    }
}
