package app.ransam.login_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {


    Button logout;
	Button save;
	EditText name,age,email;
	String name1,age1,email1;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        logout=(Button)findViewById(R.id.logout);
        firebaseAuth=FirebaseAuth.getInstance();
		save=(Button)findViewById(R.id.ebutton);
		name=(Button)findViewById(R.id.ename);
		age=(Button)findViewById(R.id.eage);
		email=(Button)findViewById(R.id.email);
		
		save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

			name1=name.getText().toString();
			age1=name.getText().toString();
			email1=name.getText().toString();
                if(name1.equals("")||age1.equals("")||email1.equals(""))
				{
					sendUserValues();
					Toast.makeText(getApplicationContext, "Added Successfully", Toast.LENGTH_SHORT).show();
					name.setText("");
					age.setText("");
					email.setText("");
				}


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));


            }
        });




    }

 private void sendUserValues(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserValues userValues = new UserValues(name1,age1,email1);
        myRef.setValue(userProfile);
    }

}
