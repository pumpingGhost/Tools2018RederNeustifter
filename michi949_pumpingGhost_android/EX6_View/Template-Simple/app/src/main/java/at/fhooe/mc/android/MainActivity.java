package at.fhooe.mc.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
        implements View.OnClickListener{

    public  static final String TAG = "SPref-Test";
    private static final String SP_Key = "ASharedPreferencesFileName";
    private static final String SP_VALUE_KEY = "MyKey";

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = null;
        b = (Button) findViewById(R.id.main_activity_button_load);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.main_activity_button_store);
        b.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        SharedPreferences sp =
                getSharedPreferences(SP_Key, Context.MODE_PRIVATE);switch(view.getId()) {
            case R.id.main_activity_button_load: {
                String data = sp.getString(SP_VALUE_KEY, "undefined at this point");
                TextView tv = (TextView) findViewById(R.id.main_activity_tv_output);
                tv.setText(data);
            }break;
            case R.id.main_activity_button_store: {
                EditText et = (EditText) findViewById(R.id.main_activity_input);
                String data = et.getText().toString();
                SharedPreferences.Editor edt = sp.edit();
                edt.putString(SP_VALUE_KEY, data);
                edt.commit();
            }break;
            default:
                Log.e(TAG, "unexpected ID encountered");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sp = getSharedPreferences(SP_Key, Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = sp.edit();
        edt.clear();
        edt.commit();
    }
}
