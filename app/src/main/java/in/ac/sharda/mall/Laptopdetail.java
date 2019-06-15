package in.ac.sharda.mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Laptopdetail extends AppCompatActivity {
    TextView name,desc,price;
    Button add,next,back;
    AsyncHttpClient client;
    RequestParams params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptopdetail);


        name=(TextView)findViewById(R.id.LaptopName);
        desc=(TextView)findViewById(R.id.LaptopDescription);
        price=(TextView)findViewById(R.id.LaptopPrice);
        add=(Button)findViewById(R.id.btnAddToCart);
        next=(Button)findViewById(R.id.btnnext);
        back=(Button)findViewById(R.id.btnback);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client=new AsyncHttpClient();
                params=new RequestParams();

                client.post("",params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                        Toast.makeText(Laptopdetail.this, "Added to Cart Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        Toast.makeText(Laptopdetail.this, "Not added to the Cart", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}
