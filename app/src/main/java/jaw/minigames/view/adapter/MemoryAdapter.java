package jaw.minigames.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import jaw.minigames.R;
import jaw.minigames.eventbus.MemoryTileTappedEvent;
import jaw.minigames.model.minigamemodule.memory.IMemory;
import jaw.minigames.model.minigamemodule.memory.IMemoryTile;
import jaw.minigames.model.minigamemodule.memory.MemoryTile;

public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder> implements IMemoryAdapter {
    private IMemory memory;

    public MemoryAdapter(IMemory memory) {
        this.memory = memory;
    }

    public int getItemCount() {
        return this.memory.getTiles().size();
    }

    @Override
    public MemoryAdapter.MemoryViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View memoryView;
        MemoryViewHolder memoryViewHolder;

        memoryView = inflater.inflate(R.layout.tile_res_simple, parent, false);
        memoryViewHolder = new MemoryViewHolder(memoryView);
        return memoryViewHolder;
    }

    public void onBindViewHolder(MemoryViewHolder holder, int position) {
        holder.memoryTile = memory.getTiles().get(position);
        holder.i = position;
        holder.setMemoryTiles();
    }

    @Override
    public void refreshItems() {

    }

    public static class MemoryViewHolder extends RecyclerView.ViewHolder {
        public IMemoryTile memoryTile;
        public int i;
        public Button button;

        public MemoryViewHolder(final View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    EventBus.getDefault().post(new MemoryTileTappedEvent(i));
                    if (memoryTile.getChecked()) {
                        button.setText("!!");
                    } else {
                        button.setText(tileText[memoryTile.getType()]);
                    }
                }
            });
            //tileImage = (ImageView) itemView.findViewById(R.id.imageButton);
        }

        public void setMemoryTiles() {
            button.setText(tileText[memoryTile.getType()]);

            if (this.memoryTile.getChecked()) {
                //tileImage.setImageResource(R.drawable.cross);
            }
        }

        private String[] tileText = {
                "Ambulance",
                "Airplane",
                "Bike",
                "Boat",
                "Church",
                "Cow",
                "Flag",
                "Helicopter",
                "Horse",
                "McDonalds",
                "Police Car",
                "Sheep",
                "Tractor",
                "Train",
                "Windmiller",
                "Wind turbine",
        };

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
}