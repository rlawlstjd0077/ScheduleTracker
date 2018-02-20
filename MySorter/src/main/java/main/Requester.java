package main;

import okhttp3.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Requester {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public String doRequest(String url, String pid) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> pair = new ArrayList<NameValuePair>();
        pair.add(new BasicNameValuePair("pid", pid));
        post.setEntity(new UrlEncodedFormEntity(pair));

        HttpResponse response = httpClient.execute(post);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
//        OkHttpClient client = new OkHttpClient();
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("pid", pid)
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().toString();
    }
}
