package app.ransam.login_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_Up extends AppCompatActivity {

    private EditText name,username,password;
    private Button sigup;
    private TextView signin;
    FirebaseAuth firebaseAuth;
    String name1,user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        sigup = (Button)findViewById(R.id.sigup);
        name = (EditText) findViewById(R.id.name);
        signin = (TextView)findViewById(R.id.signin);


        firebaseAuth=FirebaseAuth.getInstance();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name1=name.getText().toString();
                user=username.getText().toString();
                pass=password.getText().toString();
                if(valid())
                {

                    firebaseAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                /*finish();
                                firebaseAuth.signOut();
                                Toast.makeText(getApplicationContext(),"Signup Successfull",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);*/
                                //calling send email method
                                sendEmail();
                            }
                            else
                            {
                                firebaseAuth.signOut();
                                Toast.makeText(getApplicationContext(),"Error in Sing-Up",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }

    private Boolean valid()
    {
        Boolean result=false;

        if(name.equals("")||user.equals("")||pass.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Fill All The Fields",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }


        return  result;
    }


    private  void  sendEmail()
    {

        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null)
        {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful())
                    {
                        finish();
                        firebaseAuth.signOut();
                        Toast.makeText(getApplicationContext(),"Sign Up Successfull. verification send to your email!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Sign Up failed. verification not send to your email!",Toast.LENGTH_SHORT).show();

                    }



                }
            });


        }

    }


}
