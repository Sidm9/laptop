package in.ac.sharda.mall;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Cart extends AppCompatActivity {
    ListView items;
    AsyncHttpClient client;
    RequestParams params;
    ArrayList data=new ArrayList();
    Button checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        items=(ListView)findViewById(R.id.cart_items);
        checkout=(Button)findViewById(R.id.check);

        client=new AsyncHttpClient();
        params=new RequestParams();

        client.get("", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String cdata=new String(responseBody);
                try {
                    JSONArray array=new JSONArray(cdata);
                    for(int i=0;i<array.length();i++)
                    {
                        JSONObject obj=array.getJSONObject(i);
                        int id=obj.getInt("id");
                        int price=obj.getInt("price");
                        String name=obj.getString("name");
                        String desc=obj.getString("desc");
                        data.add(id+"\n"+name+"\n"+desc+"\n"+price);
                        ArrayAdapter adapter=new ArrayAdapter(Cart.this,android.R.layout.simple_list_item_1,data);
                        items.setAdapter(adapter);

                    }

                }
                catch (JSONException j)
                {

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i=new Intent(Cart.this,checkOut.java);
                //startActivity(i);
            }
        });

    }
}
