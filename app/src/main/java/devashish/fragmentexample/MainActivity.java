package devashish.fragmentexample;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText e1,e2;
    TextView t31;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragmentContainer) != null) {

            // Create a new Fragment to be placed in the activity layout
            MyFragment2 firstFragment = new MyFragment2();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, firstFragment).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        e1 = (EditText) findViewById(R.id.editText21);
        e2 = (EditText) findViewById(R.id.editText22);
        Button btn_Login = (Button) findViewById(R.id.button21);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_login();
            }
        });
    }

    private void my_login()
    {
        String name = e1.getText().toString();
        String password = e2.getText().toString();

        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
        inflate2();
    }

    private void inflate2()
    {
        // Create fragment and give it an argument specifying the article it should show
        MyFragment2 oldFragment = new MyFragment2();
        MyFragment3 newFragment = new MyFragment3();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragmentContainer, newFragment);
        transaction.hide(oldFragment);
        transaction.detach(oldFragment);
        transaction.remove(oldFragment);

        // It doesn't let the android to destroy the old fragment
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        Handler h1 = new Handler();
        h1.postDelayed(new Runnable() {
            @Override
            public void run() {
                my_message();
            }
        },2000);
    }

    private void my_message()
    {
        t31 = (TextView) findViewById(R.id.textView31);

        t31.setText("Hi");
    }
}
