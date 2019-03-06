package task3.components;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component("converter")
public class Converter {

    private RequestRate requestRate;
    private Currency currency;

    public enum Currency {
        RUB ("RUB"),
        EURO ("EUR"),
        USD ("USD");

        private final String name;

        Currency(String name) {
            this.name = name;
        }

        public String get() {
            return name;
        }

    }

    public Converter(){}
    public Converter(Currency currency){
        this.currency = currency;
    }

    public void print_currency(){
        System.out.println(this.currency.name);
    }

    @Autowired
    public Converter(RequestRate requestRate){
        this.requestRate = requestRate;
    }

    public double convert(Currency from ,Currency to, double sum) {
        HttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = null;
        URI uri = null;
        try {
            uriBuilder = new URIBuilder("http://apilayer.net/api/live");
            uri = uriBuilder.
                    addParameter("access_key", "b32a368ec6c99978f73a97b686c1b1a5").
                    addParameter("currencies", to.name).build();

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
            rate  = obj.getJSONObject("quotes").getDouble(from.name + to.name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum * rate;
    }


    public double convert_with_request(Currency from ,Currency to, double sum){
        double rate = this.requestRate.get_currency_rate(from,to);
        return sum*rate;
    }

    public static void main(String[] args) {
//        Converter dollarConverter = new Converter();
//        System.out.println( dollarConverter.convert(Currency.USD, Currency.EURO, 25) );
    }
}
