package aidl.adesa.priteshpatel;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aidl.adesa.priteshpatel.plugin.IRemoteProductService;
import aidl.adesa.priteshpatel.plugin.Product;


public class ProductActivity extends AppCompatActivity {

    private IRemoteProductService service;
    private RemoteServiceConnection serviceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        connectService();

        Button addProduct = (Button)findViewById(R.id.btnAdd);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (service != null) {
                        String name = ((EditText) findViewById(R.id.edtName)).getText().toString();
                        int quatity = Integer.parseInt(((EditText) findViewById(R.id.edtQuantity)).getText().toString());
                        float cost = Float.parseFloat(((EditText) findViewById(R.id.edtCost)).getText().toString());

                        service.addProduct(name, quatity, cost);
                        Toast.makeText(ProductActivity.this, "Product added.", Toast.LENGTH_LONG)
                                .show();
                    } else {
                        Toast.makeText(ProductActivity.this, "Service is not connected", Toast.LENGTH_LONG)
                                .show();
                    }
                } catch (Exception e) {

                }
            }
        });

        Button searchProduct = (Button)findViewById(R.id.btnSearch);
        searchProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (service != null) {
                        String name = ((EditText) findViewById(R.id.edtSearchProduct)).getText().toString();
                        Product product = service.getProduct(name);
                        if(product != null) {
                            ((TextView) findViewById(R.id.txtSearchResult)).setText(product.toString());
                        } else {
                            Toast.makeText(ProductActivity.this, "No product found with this name", Toast.LENGTH_LONG)
                                    .show();
                        }

                    } else {
                        Toast.makeText(ProductActivity.this, "Service is not connected", Toast.LENGTH_LONG)
                                .show();
                    }
                } catch(Exception e) {

                }
            }
        });
    }

    private void connectService() {
        serviceConnection = new RemoteServiceConnection();
        Intent i = new Intent("androidsrc.intent.action.PICK_PLUGIN");
        i.addCategory("androidsrc.intent.category.PRODUCT_PLUGIN");
        i.setPackage("aidl.adesa.priteshpatel.plugin");
        boolean ret = bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    class RemoteServiceConnection implements ServiceConnection {

        public void onServiceConnected(ComponentName name, IBinder boundService) {
            service = IRemoteProductService.Stub.asInterface((IBinder) boundService);
            Toast.makeText(ProductActivity.this, "Service connected", Toast.LENGTH_LONG)
                    .show();
        }

        public void onServiceDisconnected(ComponentName name) {
            service = null;
            Toast.makeText(ProductActivity.this, "Service disconnected", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
