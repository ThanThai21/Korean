package com.esp.korean.UserInfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esp.korean.Login.LoginActivity;
import com.esp.korean.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_LOGIN = 3;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String userId;

    private TextView userName;
    private TextView userJob;
    private View follower;
    private View following;
    private TextView followersNum;
    private TextView followingNum;
    private TextView descriptionView;
    private KenBurnsView coverView;
    private CircleImageView avatarView;
    private FloatingActionButton editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        initView();
        initUser();
    }

    private void initView() {
        userName = (TextView) findViewById(R.id.user_name);
        userJob = (TextView) findViewById(R.id.user_work);
        follower = findViewById(R.id.followers);
        followersNum = (TextView) findViewById(R.id.followers_num);
        following = findViewById(R.id.following);
        followingNum = (TextView) findViewById(R.id.following_num);
        descriptionView = (TextView) findViewById(R.id.description);
        coverView = (KenBurnsView) findViewById(R.id.user_cover);
        avatarView = (CircleImageView) findViewById(R.id.avatar);
        editButton = (FloatingActionButton) findViewById(R.id.edit_button);

    }

    private void initUser() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        if (mUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, RC_LOGIN);
        } else {
            userId = mUser.getUid();
            updateUI();
        }
    }

    private void updateUI() {
        UserInfo userData = UserManager.userInfo;
        if (userData != null) {
            userName.setText(userData.getUserName());
            userJob.setText(userData.getUserJob());
            if (userData.getUserCoverUrl() == null) {
                Glide.with(this).load(userData.getAvatarUrl()).into(avatarView);
            }
            followersNum.setText(userData.getNumFollowers() + "");
            followingNum.setText(userData.getNumFollowing() + "");
            descriptionView.setText(userData.getDescription());
            if (userData.getUserCoverUrl() != null) {
                Glide.with(this).load(userData.getUserCoverUrl()).into(coverView);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_LOGIN) {

        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.edit_button) {
            //show dialog manual
        }
    }
}
