package in.ac.sharda.mall;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    EditText user_name,user_password;
    Button submit;
    AsyncHttpClient client;
    RequestParams params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user_name=(EditText)findViewById(R.id.userId);
        user_password=(EditText)findViewById(R.id.userPassword);
        submit=(Button)findViewById(R.id.login);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(user_name.getText().toString()))
                {
                    user_name.setError("Enter User-Name");
                }
                if(TextUtils.isEmpty(user_password.getText().toString()))
                {
                    user_password.setError("Enter password");
                }
                else
                {
                    client=new AsyncHttpClient();
                    params=new RequestParams();
                    params.put("userName",user_name.getText().toString());
                    params.put("password",user_password.getText().toString());
                    client.post("http://10.0.2.2:8080/login", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


//                            String str=new String(responseBody);
//                            if(str.equals("true"))
//                            {
//                                Intent i=new Intent(MainActivity.this, Laptoplist.class);
//                                startActivity(i);
//                            }
//                            else
//                            {
//                                submit.setEnabled(false);
//                            }
                            Intent i=new Intent(MainActivity.this, Laptoplist.class);
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            Toast.makeText(MainActivity.this, "Unable to Connect", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
