package task3.components;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component("requestRate")
public class RequestRateImpl implements RequestRate {
    @Override
    public double get_currency_rate(Converter.Currency from, Converter.Currency to) {
        HttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = null;
        URI uri = null;
        try {
            uriBuilder = new URIBuilder("http://apilayer.net/api/live");
            uri = uriBuilder.
                    addParameter("access_key", "b32a368ec6c99978f73a97b686c1b1a5").
                    addParameter("currencies", to.name()).build();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpGet getReq = new HttpGet(uri);
        HttpResponse response = null;
        double rate = 1;
        try {
            response = httpClient.execute(getReq);
            ResponseHandler<String> handler = new BasicResponseHandler();
            JSONObject obj = new JSONObject(handler.handleResponse(response));
            rate  = obj.getJSONObject("quotes").getDouble(from.name() + to.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rate;
    }
}
