package com.esp.korean.Login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esp.korean.CustomView.CustomRelativeLayout;
import com.esp.korean.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import jp.wasabeef.blurry.Blurry;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 2;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private EditText emailEdt;
    private EditText passwordEdt;
    private EditText nameEdt;
    private View nameView;
    private View loginView;
    private View emailView;

    private Button submitButton;
    private TextView typeButton;

    private View facebookLogin;
    private View googleSignIn;

    private Type type = Type.LOGIN;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private GoogleApiClient mGoogleApiClient;

    private ProgressDialog progressDialog;

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public enum Type {
        LOGIN, SIGNUP;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initToolbar();
        initView();
        initEditText();
        initButton();
        initFirebase();
    }

    private void initFirebase() {
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Loading");
        loginWithFacebook();
        signInWithGoogle();
    }

    private void makeStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Login");
        toolbarTitle.setTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        loginView = findViewById(R.id.login_view);
        nameView = findViewById(R.id.login_name_view);
        emailView = findViewById(R.id.email_view);
    }

    private void initEditText() {
        emailEdt = (EditText) findViewById(R.id.email_edt);
        passwordEdt = (EditText) findViewById(R.id.password_edt);
        emailEdt.setFocusable(true);
        emailEdt.setFocusableInTouchMode(true);
        emailEdt.requestFocus();
        emailEdt.requestFocusFromTouch();
        nameEdt = (EditText) findViewById(R.id.login_name_edt);
    }

    private void initButton() {
        submitButton = (Button) findViewById(R.id.login_submit_button);
        typeButton = (TextView) findViewById(R.id.login_type);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String email = emailEdt.getText().toString();
                final String name = nameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                String error = "";
                if (type == Type.LOGIN) {
                    if (email.equals("") || password.equals("")) {
                        error = "Vui lòng điền đầy đủ thông tin";
                    } else if (!checkValidEmail(email)) {
                        error = "Email không hợp lệ";
                    }else {
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.cancel();
                                if (task.isSuccessful()) {
                                    user = mAuth.getCurrentUser();
                                    finish();
                                } else {
//                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setTitle("Có lỗi xảy ra");
                                    builder.setMessage("Email hoặc mật khẩu không đúng");
                                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    builder.create().show();
                                }
                            }
                        });
                    }
                } else if (type == Type.SIGNUP) {
                    if (email.equals("") || password.equals("") || name.equals("")) {
                        error = "Vui lòng điền đầy đủ thông tin";
                    } else if (!checkValidEmail(email)){
                        error = "Email không hợp lệ";
                    } else if (password.length() < 8) {
                        error = "Mật khẩu chứa ít nhất 8 kí tự";
                    } else {
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.cancel();
                                if (task.isSuccessful()) {
                                    user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(name)
                                            .build();
                                    user.updateProfile(profileChangeRequest);
                                    finish();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                    builder.setTitle("Có lỗi xảy ra");
                                    builder.setMessage("Vui lòng thử lại sau");
                                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                                    builder.create().show();
                                }
                            }
                        });
                    }

                }
                if (!error.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Có lỗi xảy ra");
                    builder.setMessage(error);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create().show();
                }
            }
        });
        typeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == Type.LOGIN) {
                    type = Type.SIGNUP;
                    typeButton.setText("I have already account");
                    nameView.setVisibility(View.VISIBLE);
                    float oldHeight = loginView.getHeight();
                    float newHeight = loginView.getHeight() + emailView.getHeight();
                    float x = oldHeight / newHeight;
                    ScaleAnimation scaleUp = new ScaleAnimation(1, 1, x, 1, 0, newHeight);
                    scaleUp.setFillAfter(false);
                    scaleUp.setDuration(50);
                    loginView.startAnimation(scaleUp);
                    submitButton.setText("Sign up");
                } else {
                    type = Type.LOGIN;
                    typeButton.setText("Create new account");
                    nameView.setVisibility(View.GONE);
                    float oldHeight = loginView.getHeight();
                    float newHeight = loginView.getHeight() - emailView.getHeight();
                    float x = oldHeight / newHeight;
                    ScaleAnimation scaleDown = new ScaleAnimation(1, 1, x, 1, 0, newHeight);
                    scaleDown.setFillAfter(false);
                    scaleDown.setDuration(50);
                    loginView.startAnimation(scaleDown);
                    submitButton.setText("Login");
                }
            }
        });
    }

    private void loginWithFacebook() {
        facebookLogin = findViewById(R.id.login_facebook);
        facebookLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void signInWithGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        googleSignIn = findViewById(R.id.login_google);
        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private boolean checkValidEmail(String email) {
        if (email == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            progressDialog.cancel();
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                Log.d(TAG, "onActivityResult: " + result.getStatus());
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            String e = task.getException().toString();
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
}
