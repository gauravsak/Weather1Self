package com.equalexperts.weather1self.service;

import com.equalexperts.weather1self.model.Event;
import com.equalexperts.weather1self.response.lib1Self.Stream;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

public interface Lib1SelfClient {

    String API_BASE_URL = "http://sandbox.1self.co/v1";
    String APP_ID = "app-id-36205833a524ce5df5b4fe3773efe7d6";
    String APP_SECRET = "app-secret-f06c0d756376e27bf24c980e540441e7672b1186" +
            "fbfaeb943cd30ae01d10e3c1";

    @Headers({"Authorization: " + APP_ID + ":" + APP_SECRET})
    @POST("/streams")
    public Stream createStream();

    @Headers({"Content-Type: application/json"})
    @POST("/streams/{streamId}/events/batch")
    public Void sendEventBatch(@Body List<Event> events, @Path("streamId") String streamId,
                          @Header("Authorization") String writeToken);
}
