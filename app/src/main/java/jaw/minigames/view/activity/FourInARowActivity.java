package jaw.minigames.view.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jaw.minigames.R;
import jaw.minigames.eventbus.OnCreateEvent;
import jaw.minigames.eventbus.RequestPresenterEvent;
import jaw.minigames.eventbus.UpdateFourInARowActivityEvent;
import jaw.minigames.model.minigamemodule.fourinarow.IFourInARow;
import jaw.minigames.view.adapter.FourInARowAdapter;
import jaw.minigames.view.adapter.IFourInARowAdapter;

/**
 * Created by johan on 7/19/2017.
 */

public class FourInARowActivity extends AppCompatActivity implements IFourInARowView {
    private RecyclerView gridview;
    private Toolbar toolbar;
    private IFourInARow fourInARow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fourinarow_activity);

        gridview = (RecyclerView) findViewById(R.id.fourInARowGrid);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //    setSupportActionBar(toolbar);
        gridview.setLayoutManager(new GridLayoutManager(this, 7));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        EventBus.getDefault().post(new RequestPresenterEvent(this));
        EventBus.getDefault().post(new OnCreateEvent(this));
        EventBus.getDefault().register(this);
        System.out.println("Activity");
        /*gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(CarBingoActivity.this, "Nice work! Keep going!",
                        Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new TileCheckedEvent(position));
            }
        });*/
    }

    public RecyclerView getGridView(){
        return gridview;
    }

    @Override
    public AppCompatActivity getAppCompatActivity() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void refreshItems(UpdateFourInARowActivityEvent event) {
        IFourInARowAdapter adapter = (IFourInARowAdapter) gridview.getAdapter();
        adapter.refreshItems(fourInARow);

        // Stop refresh animation
        // refreshLayout.setRefreshing(false);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Car_Bingo) {
            // Handle the camera action
        } else if (id == R.id.Four_In_A_Row) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    @Override
    public void setFourInARowAdapter(FourInARowAdapter adapter) {
        System.out.println("Inne i set adapter");
        gridview.setAdapter(adapter);
    }


    @Override
    public void setToolbar() {

    }

    public void setFourInARow(IFourInARow fourInARow){
        this.fourInARow = fourInARow;
    }
}
