package com.jbarseg.conquistadores;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.notes.NotesActivity;
import com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.notes.NotesFragment;
import com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.podcast.PodcastsActivity;
import com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.podcast.PodcastsFragment;
import com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.news.NewsActivity;
import com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.news.NewsFragment;
import com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.verses.VersesActivity;
import com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.verses.VersesFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    public DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new MainFragment();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_home:
                fragment = new MainFragment();
                break;
            case R.id.nav_about:
                fragment = new AboutUsFragment();
                break;
            case R.id.nav_news:
                fragment = new NewsFragment();
                break;
            case R.id.nav_podcasts:
                fragment = new PodcastsFragment();
                break;
            case R.id.nav_notes:
                fragment = new NotesFragment();
                break;
            case R.id.nav_verses:
                fragment = new VersesFragment();
                break;
            case R.id.nav_contact:
                fragment = new ContactFragment();
                break;
            case R.id.nav_logout:
                logout();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void facebookIntent(View view) {
        String urlFacebook = "https://www.facebook.com/conquistadoresvp/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(urlFacebook));
        startActivity(i);
    }

    public void instagramIntent(View view) {
        String urlInstagram = "https://www.instagram.com/conquistadoresvp/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(urlInstagram));
        startActivity(i);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    public void showNotes(View view) {
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        startActivity(intent);
    }

    public void showNews(View view) {
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        startActivity(intent);
    }

    public void showPodcasts(View view) {
        Intent intent = new Intent(MainActivity.this, PodcastsActivity.class);
        startActivity(intent);
    }

    public void showVerses(View view) {
        Intent intent = new Intent(MainActivity.this, VersesActivity.class);
        startActivity(intent);
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}



