package com.google.cloud.hadoop.gcsio;

import static com.google.cloud.hadoop.gcsio.MockGoogleCloudStorageImplFactory.mockedGcsClientImpl;
import static com.google.cloud.hadoop.util.testing.MockHttpTransportHelper.mockTransport;

import com.google.api.client.testing.http.MockHttpTransport;
import com.google.cloud.hadoop.gcsio.testing.FakeServer;
import com.google.cloud.hadoop.gcsio.testing.MockStorage;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GoogleCloudStorageClientTest {

  private static final String BUCKET_NAME = "foo-bucket";

  private static final MockHttpTransport transport = mockTransport();

  FakeServer fakeServer;

  @Test
  public void createBucket_succeeds() throws IOException {
    MockStorage mockStorage = new MockStorage();
    mockStorage.addResponse(null);
    fakeServer = FakeServer.of(mockStorage);

    GoogleCloudStorage gcs = mockedGcsClientImpl(transport,
        fakeServer.getGrpcStorageOptions().getService());
    gcs.createBucket(BUCKET_NAME);
  }
}
