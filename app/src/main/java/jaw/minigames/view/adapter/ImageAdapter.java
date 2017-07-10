package jaw.minigames.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import jaw.minigames.R;
import jaw.minigames.graphicstest.SquareImageView;
import jaw.minigames.model.minigamemodule.carbingogame.CarBingo;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingo;

/**
 * Created by johan on 7/4/2017.
 */
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private ICarBingo carBingo;
        ImageView imageView;


    public ImageAdapter(Context c) {
            mContext = c;
            this.carBingo = new CarBingo();
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new SquareImageView(mContext);
                imageView.setScaleType(SquareImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (SquareImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[carBingo.getCarBingoTiles().get(position).getType()]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.ambulance,
                R.drawable.airplane,
                R.drawable.bike,
                R.drawable.boat,
                R.drawable.church,
                R.drawable.cow,
                R.drawable.flag,
                R.drawable.helicopter,
                R.drawable.horse,
                R.drawable.mcdonalds,
                R.drawable.police,
                R.drawable.sheep,
                R.drawable.tractor,
                R.drawable.train,
                R.drawable.windmiller,
                R.drawable.windturbine,
        };
    }