package pro_search.nrgappdesign.de.prosearch;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ActivityStart extends AppCompatActivity {

    CallbackManager man;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        man.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

                PackageInfo info;
//        try {
//            info = getPackageManager().getPackageInfo("pro_search.nrgappdesign.de.prosearch", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md;
//                md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String something = new String(Base64.encode(md.digest(), 0));
//                //String something = new String(Base64.encodeBytes(md.digest()));
//                Log.e("hash key", something);
//            }
//        } catch (PackageManager.NameNotFoundException e1) {
//            Log.e("name not found", e1.toString());
//        } catch (NoSuchAlgorithmException e) {
//            Log.e("no such an algorithm", e.toString());
//        } catch (Exception e) {
//            Log.e("exception", e.toString());
//        }

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            if (!accessToken.isExpired())
                startActivity(new Intent(ActivityStart.this,ActivityMain.class));
            finish();
        }

        com.facebook.login.widget.LoginButton loginButton = (com.facebook.login.widget.LoginButton) this.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile","user_status","email","user_likes","read_custom_friendlists","user_friends","user_photos","user_relationships"));

        man = CallbackManager.Factory.create();
        // Callback registration
        loginButton.registerCallback(man, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(ActivityStart.this,"Successfully logged in to Facebook",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityStart.this,ActivityMain.class));
                finish();
            }

            @Override
            public void onCancel() {
                Toast.makeText(ActivityStart.this,"Facebook login aborted",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(ActivityStart.this,"Error connecting to Facebook",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
