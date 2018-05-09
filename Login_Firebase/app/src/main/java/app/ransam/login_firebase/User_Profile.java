package app.ransam.login_firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_Profile extends AppCompatActivity {

    TextView name,age,email;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
        name=(TextView)findViewById(R.id.name);
        age=(TextView)findViewById(R.id.age);
        email=(TextView)findViewById(R.id.email);


        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserValues userValues= dataSnapshot.getValue(UserValues.class);

                name.setText("Name : "+userValues.getName());
                age.setText("Age : "+userValues.getAge());
                email.setText("Email : "+userValues.getEmail());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
