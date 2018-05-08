package app.ransam.login_firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {


    EditText mail;
    Button change;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        mail=(EditText)findViewById(R.id.mail);
        change=(Button)findViewById(R.id.change);
        firebaseAuth=FirebaseAuth.getInstance();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=mail.getText().toString();
                if(id.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter Your Mail ID",Toast.LENGTH_SHORT).show();
                }
                else {

                    firebaseAuth.sendPasswordResetEmail(id).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                firebaseAuth.signOut();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Toast.makeText(getApplicationContext(),"Password Reset Code sent to your mail",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                firebaseAuth.signOut();
                                Toast.makeText(getApplicationContext(),"Error to sent mail",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                }
            }
        });



    }
}
